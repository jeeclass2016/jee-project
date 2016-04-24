package com.vietsci.cms.frontend.etopup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentBatchDTO;
import com.vietsci.cms.frontend.etopup.dto.ReasonDTO;
import com.vietsci.cms.frontend.etopup.model.BalanceAdjustmentType;
import com.vietsci.cms.frontend.etopup.model.Reason;
import com.vietsci.cms.frontend.etopup.primefaces.BalanceAdjustmentBatchEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService;
import com.vietsci.cms.frontend.etopup.service.EtopupReasonService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

@Named(value = "balanceAdjustmentBatchEtopupController")
@Scope("session")
public class BalanceAdjustmentBatchEtopupController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7469225927148310938L;

  /**
   * logger.
   */
  private static final Log logger = LogFactory.getLog(BalanceAdjustmentBatchEtopupController.class);

  @Inject
  BalanceManagementEtopupService balanceManagementService;

  @Inject
  EtopupReasonService reasonService;

  @Inject
  private AgentManagementService agentService;

  private BalanceAdjustmentBatchEtopupLazyDataModel dataModel;

  private List<SelectItem> adjustmentTypeSelectItems;
  private List<SelectItem> reasonSelectItems;

  private UploadedFile uploadedFile;
  private StreamedContent errorFile;
  private boolean uploadedFileError;
  private final List<String> errorFileContent = new ArrayList<String>();
  private String fileContentErrorMsg;
  private BalanceAdjustmentBatchDTO dto;

  @PostConstruct
  private void init() {
    dto = new BalanceAdjustmentBatchDTO();
    initAdjustmentTypeSelectItems();
    //initReasonSelectItems();
  }

  /*
   * Initialize the types of adjustment
   */
  private void initAdjustmentTypeSelectItems() {
    this.adjustmentTypeSelectItems = new ArrayList<>();

    this.adjustmentTypeSelectItems.add(new SelectItem(BalanceAdjustmentType.POSITIVE.getValue(),
            BalanceAdjustmentType.POSITIVE.getName()));
    this.adjustmentTypeSelectItems.add(new SelectItem(BalanceAdjustmentType.NEGATIVE.getValue(),
            BalanceAdjustmentType.NEGATIVE.getName()));
  }

  /*
    Initialize the list of reasons, get from database
   */
  public void initReasonSelectItems() {
    this.reasonSelectItems = new ArrayList<>();

    ReasonDTO reasonDTO = new ReasonDTO();
    reasonDTO.setSortField("reasonDescribe");
    reasonDTO.setStatusNumberValue(Constants.ReasonManagement.ACTIVE_STATUS_VALUE); // get active reasons
    reasonDTO.setSortOrder(Constants.ASCENDING_SORT);

    List<Reason> reasonList = reasonService.findReasons(reasonDTO);
    for (Reason reason : reasonList) {
      this.reasonSelectItems.add(new SelectItem(reason.getCode(), reason.getReasonDescribe()));
    }
  }

  /**
   * Handle uploaded file
   *
   * @param event
   */
  public void handleFileUpload(FileUploadEvent event) {
    logger.debug("Tên file được chọn: " + event.getFile().getFileName());
    logger.debug("Kích thước file được chọn: " + event.getFile().getSize());
    uploadedFile = event.getFile();
    uploadedFileError = false;
    errorFileContent.clear();
    errorFile = null;
  }

  public void doUploadBalanceAdjustmentBatch() {
    boolean rs = false;
    errorFileContent.clear();
    uploadedFileError = false;
    if(validateForm()) {
      try {
        rs = validateUploadedFileContent();
        if (rs) {
          dto.setStaffId(getCurrentUserId());
          dto.setUserName(getCurrentUserName());
          dto.setAgentBalance(readFileToExtractData(uploadedFile));
          boolean result = balanceManagementService.createBalanceAdjustmentBatch(dto);

          if(!result) {
            FacesUtil.addErrorMessage(":balanceAdjustmentBatchForm:balanceBatchMessage", "Điều chỉnh tài khoản thất bại");
          }

          dataModel = new BalanceAdjustmentBatchEtopupLazyDataModel(agentService, dto);
        }

      } catch (Exception e) {
        logger.error("Điều chỉnh tài khoản thất bại vì: " + e.getMessage());
        handleExceptionMessage(e, BalanceAdjustmentBatchEtopupController.class);
        return;
      }
      displayResultMessage(rs, MessageConstants.ETOPUP_COMMON_UPLOAD_FILE_SUCCESS, ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_FAILED);
    }
  }
  
  private void displayResultMessage(boolean rs, String successKey, String failedKey) {
    String message = null;
    if (rs) {
      message = getMessage(successKey);
      FacesUtil.addSuccessMessage(message);
    } else {
      message = getErrorMessage(failedKey);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }

  private Map<String, BigDecimal> readFileToExtractData(UploadedFile uploadedFile) throws IOException {
    Map<String, BigDecimal> balanceAmount = new HashMap<>();

    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name())) {

      // Bỏ qua dòng đầu tiên vì có nội dung là tiêu đề của các dòng tiếp theo
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Scanner scanLine = new Scanner(line);
        scanLine.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
        String msisdn = scanLine.next().trim();
        String amountStr = scanLine.next().trim();
        BigDecimal amount = new BigDecimal(amountStr);
        amount = amount.setScale(4, RoundingMode.FLOOR);
        balanceAmount.put(msisdn, amount);
        scanLine.close();
      }
      scanner.close();
    }
    return balanceAmount;
  }

  private boolean validateForm() {
    if (uploadedFile == null || uploadedFile.getSize() == 0) {
      String message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_NOT_SELECTED);
      FacesUtil.addErrorMsg(":balanceAdjustmentBatchForm:balanceBatchMessage", message, message);
      return false;
    }

    if (dto.getAdjustmentType() == null) {
      FacesUtil.addErrorMessage(":balanceAdjustmentBatchForm:balanceBatchMessage", "Phải chọn loại điều chỉnh");
      return false;
    }

    if (dto.getReason() == null || dto.getReason().trim().equals("")) {
      FacesUtil.addErrorMessage(":balanceAdjustmentBatchForm:balanceBatchMessage", "Phải chọn một lý do");
      return false;
    }
    return true;
  }

  // Ki?m tra n?i dung file tru?c khi g?i di d? x? lí
  private boolean validateUploadedFileContent() throws IOException, NumberFormatException {
    String message = "";
    if (!uploadedFile.getFileName().endsWith(Constants.FileManagement.TXT_EXTENSION)) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_FILE_EXTENSION_TXT);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }

    if (uploadedFile.getSize() == 0) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_FILE_EMPTY);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }

    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name())) {
      // B? qua dòng d?u tiên vì có n?i dung là tiêu d? c?a các dòng ti?p theo
      if (scanner.hasNextLine()) {
        errorFileContent.add(scanner.nextLine());
      }

      // Không có thông tin nào v? b?t kì SHT nào trong file
      if (!scanner.hasNextLine()) {
        showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_BALANCE_BATCH_UPLOADING_NO_CONTENT, "");
        return false;
      }

      while (scanner.hasNextLine()) {
        processLine(scanner.nextLine().trim());
      }
    }
    // Create error file to return if there is error
    if (uploadedFileError) {
      errorFile = createErrorFileForUploadedFileTXT(uploadedFile.getFileName(), errorFileContent, BalanceAdjustmentBatchEtopupController.class);
      return false;
    }
    return true;
  }

  @SuppressWarnings("resource")
  private void processLine(String aLine) throws IOException, NumberFormatException {
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
    String errorLine = aLine;
    boolean rs;
    if (!scanner.hasNext()) {
      rs = false;
      errorLine = buildErrorLine(rs, aLine);
    } else if (!aLine.contains(Constants.FileManagement.COMMA_DELIMITER)) {
      rs = false;
      errorLine = buildErrorLine(rs, aLine);
    } else {
      String msisdn = scanner.next().trim();
      String amountOfMoney = scanner.next().trim();
      // Validate start with 0 character
      rs = EtopupUtil.validateStartWithZero(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }

      // Validate msisdn contain only 0-9 characters
      rs = EtopupUtil.validateNumberCharacterOnly(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }

      rs = EtopupUtil.validatePhoneNumberLength(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }

      rs = validateDuplicate(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }

      // Validate amountMoney contain only 0-9 characters
      rs = isValidTransAmount(amountOfMoney);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
    }

    errorFileContent.add(errorLine);
  }

  @SuppressWarnings("resource")
  private boolean validateDuplicate(String data) throws IOException {
    int countMsisdn = 0;

    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name())) {
      String aLine;
      String msisdn;

      while (scanner.hasNextLine()) {
        aLine = scanner.nextLine();
        Scanner lineScanner = new Scanner(aLine);
        lineScanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
        if (!lineScanner.hasNext()) {
          continue;
        }

        if (!aLine.contains(Constants.FileManagement.COMMA_DELIMITER)) {
          continue;
        }

        msisdn = lineScanner.next();

        if (msisdn.equals(data)) {
          countMsisdn++;
          if (countMsisdn > 1) {
            return false;
          }
        }
      }
    }

    return true;
  }
  
  private boolean isValidTransAmount(String transAmount) {
    if (StringUtils.isBlank(transAmount)) {
      return false;
    }
    try {
      double transAmnt = Double.parseDouble(transAmount.trim());
      if (transAmnt <= 0 || transAmnt >= new Double(100000000000000d)) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Xây dựng nội dung dòng lỗi từ dòng ban đầu cộng thêm đánh dấu lỗi: "--> Error"
   * và chuyển trạng thái uploadedFileError = true
   *
   * @param check
   * @param originLine
   * @return
   */
  private String buildErrorLine(boolean check, String originLine) {

    String errorLine = buildErrorLineContent(check, originLine);
    if (!check) {
      uploadedFileError = true;
    }

    return errorLine.toString();
  }

  private void showErrorMsgForUploadedFileContent(String messageCode, String... messageContents) {

  }

  public BalanceAdjustmentBatchEtopupLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(BalanceAdjustmentBatchEtopupLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
  }

  public StreamedContent getErrorFile() throws IOException {
    errorFile = checkToReopenErrorFile(errorFile, BalanceAdjustmentBatchEtopupController.class);
    return errorFile;
  }

  public void setErrorFile(StreamedContent errorFile) {
    this.errorFile = errorFile;
  }

  public BalanceAdjustmentBatchDTO getDto() {
    return dto;
  }

  public void setDto(BalanceAdjustmentBatchDTO dto) {
    this.dto = dto;
  }

  public List<SelectItem> getReasonSelectItems() {
    return reasonSelectItems;
  }

  public void setReasonSelectItems(List<SelectItem> reasonSelectItems) {
    this.reasonSelectItems = reasonSelectItems;
  }

  public List<SelectItem> getAdjustmentTypeSelectItems() {
    return adjustmentTypeSelectItems;
  }

  public void setAdjustmentTypeSelectItems(List<SelectItem> adjustmentTypeSelectItems) {
    this.adjustmentTypeSelectItems = adjustmentTypeSelectItems;
  }

  public boolean isUploadedFileError() {
    return uploadedFileError;
  }

  public void setUploadedFileError(boolean uploadedFileError) {
    this.uploadedFileError = uploadedFileError;
  }

  public List<String> getErrorFileContent() {
    return errorFileContent;
  }

  public String getFileContentErrorMsg() {
    return fileContentErrorMsg;
  }

  public void setFileContentErrorMsg(String fileContentErrorMsg) {
    this.fileContentErrorMsg = fileContentErrorMsg;
  }
}
