package com.vietsci.cms.frontend.etopup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
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
import com.vietsci.cms.frontend.etopup.common.util.Constants.FileManagement;
import com.vietsci.cms.frontend.etopup.dto.SIMBatchDTO;
import com.vietsci.cms.frontend.etopup.model.SimBatch;
import com.vietsci.cms.frontend.etopup.primefaces.SIMBatchEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.SIMBatchEtopupService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/** 
 * Controller class for SimBatch Uploading management.
 * 
 * @author lam.lethanh
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */

@Named("simBatchEtopupController")
@Scope(value="session")
public class SIMBatchEtopupController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -962093107422915240L;

  /**
   * logger.
   */
  private static final Log logger = LogFactory.getLog(SIMBatchEtopupController.class);

  @Inject
  SIMBatchEtopupService simBatchService;

  private SIMBatchDTO simBatchDTO = new SIMBatchDTO();
  private SIMBatchEtopupLazyDataModel simBatchEtopupLazyDataModel;
  private SimBatch simBatch;
  private UploadedFile uploadedFile;
  private StreamedContent errorFile;
  private boolean uploadedFileError;
  private final List<String> errorFileContent = new ArrayList<String>();
  private String fileContentErrorMsg;

  /* ============= Getter and setter methods ============= */

  public SIMBatchEtopupService getSimBatchService() {
    return simBatchService;
  }

  public void setSimBatchService(SIMBatchEtopupService simBatchService) {
    this.simBatchService = simBatchService;
  }

  public SIMBatchDTO getSimBatchDTO() {
    return simBatchDTO;
  }

  public void setSimBatchDTO(SIMBatchDTO simBatchDTO) {
    this.simBatchDTO = simBatchDTO;
  }

  public SIMBatchEtopupLazyDataModel getSimBatchEtopupLazyDataModel() {
    return simBatchEtopupLazyDataModel;
  }

  public void setSimBatchEtopupLazyDataModel(SIMBatchEtopupLazyDataModel simBatchEtopupLazyDataModel) {
    this.simBatchEtopupLazyDataModel = simBatchEtopupLazyDataModel;
  }

  public SimBatch getSimBatch() {
    return simBatch;
  }

  public void setSimBatch(SimBatch simBatch) {
    this.simBatch = simBatch;
  }

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
  }
  
  public StreamedContent getErrorFile() {
    errorFile = checkToReopenErrorFile(errorFile, SIMBatchEtopupController.class);
    return errorFile;
  }

  public void setErrorFile(StreamedContent errorFile) {
    this.errorFile = errorFile;
  }
  
  public boolean isUploadedFileError() {
    return uploadedFileError;
  }

  public void setUploadedFileError(boolean isUploadedFileError) {
    this.uploadedFileError = isUploadedFileError;
  }
  
  public String getFileContentErrorMsg() {
    return fileContentErrorMsg;
  }

  public void setFileContentErrorMsg(String fileContentErrorMsg) {
    this.fileContentErrorMsg = fileContentErrorMsg;
  }

  /* ============= Business methods ============= */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init SIMBatchEtopupController members");

    simBatchDTO = new SIMBatchDTO();
    simBatch = new SimBatch();
    uploadedFile = null;
    uploadedFileError = false;
    errorFileContent.clear();
    logger.debug("Init SIMBatchEtopupController members successfully");
  }

  /**
   * Tìm kiếm các SIM Bacth
   */
  public void doFindSIMBatchs() {
    logger.debug("Start doFindSIMBatchs");

    if (validateSIMBundleUploadingTimeForFinding()) {
      simBatchEtopupLazyDataModel = new SIMBatchEtopupLazyDataModel(simBatchService, simBatchDTO);
    }
    
    uploadedFile = null;
    uploadedFileError = false;
    errorFileContent.clear();
    
    logger.debug("End doFindSIMBatchs");
  }

  /**
   * Thêm mới SimBatch
   */
  public void doUploadSIMBatch() {
    boolean rs = false;
    errorFileContent.clear();

    if (uploadedFile == null) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_NOT_SELECTED, "");
      return;
    }
    
    try {
      rs = validateUploadedFileContent();
      if (rs) {
        rs = simBatchService.createSIMBatch(uploadedFile);
      } 
    } catch (Exception e) {
      logger.error("Thêm mới SIM Batch thất bại vì: " + e.getMessage());
      if (e instanceof CmsRestException) {
        String bodyMessage = e.getMessage();
        Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
        if (bodyMessageMap != null && bodyMessageMap.size() > 0) {
          String messageCode = bodyMessageMap.get(Constants.MESSAGE_CODE);
          String message = bodyMessageMap.get(Constants.MESSAGE);
          if (ErrorConstants.ETOPUP_SIM_BATCH_UPLOADING_SUBCRIBLE_EXITING.equals(messageCode) ||
              ErrorConstants.ETOPUP_SIM_BATCH_UPLOADING_EXISTED.equals(messageCode)) {
            showErrorMsgForUploadedFileContent(messageCode, message);
            createErrorFile(message);
            return;
          }
        }
      }
      handleExceptionMessage(e, SIMBatchEtopupController.class);
      return;
    }

    displayResultMessage(rs, MessageConstants.ETOPUP_COMMON_UPLOAD_FILE_SUCCESS, ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_FAILED);
  }

  /**
   * Handle uploaded file
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

  /**
   * Kiểm tra điều kiện tìm kiếm trước khi dùng.
   * @return valid or invalid
   */
  private boolean validateSIMBundleUploadingTimeForFinding() {
    if (simBatchDTO.getFromDate() != null && simBatchDTO.getToDate() != null
        && simBatchDTO.getFromDate().after(simBatchDTO.getToDate())) {
      String message = getErrorMessage(ErrorConstants.ETOPUP_SIM_BATCH_SEARCH_TIME);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }
    return true;
  }
  
  /**
   * Kiểm tra nội dung file trước khi gửi đi để xử lí
   * @return
   * @throws IOException
   */
  private boolean validateUploadedFileContent() throws IOException {
    if (!uploadedFile.getFileName().endsWith(FileManagement.TXT_EXTENSION)) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_FILE_EXTENSION_TXT,"");
      return false;
    }
    
    if (uploadedFile.getSize() == 0) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_FILE_EMPTY, "");
      return false;
    }
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name()))
    {
      // Bỏ qua dòng đầu tiên vì nó có nội dung là tiêu đề của các dòng tiếp theo
      if (scanner.hasNextLine()) {
        errorFileContent.add(scanner.nextLine());
      }
      
      // Không có thông tin về bất kì SIM nào trong file
      if (!scanner.hasNextLine()) {
        showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_SIM_BATCH_UPLOADING_NO_SIM_CONTENT, "");
        return false;
      }

      while (scanner.hasNextLine()){
        processLine(scanner.nextLine());
      }
    }
    
    // Create error file to return if there is error
    if (uploadedFileError) {
      errorFile = createErrorFileForUploadedFileTXT(uploadedFile.getFileName(), errorFileContent, SIMBatchEtopupController.class);
      return false;
    }
    
    return true;
  }
  
  @SuppressWarnings("resource")
  private void processLine(String aLine) throws IOException{
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(FileManagement.COMMA_DELIMITER);
    String errorLine = aLine;
    boolean rs;
    if (!scanner.hasNext()){
      rs = false;
      errorLine = buildErrorLine(rs, aLine);
    } else if (!aLine.contains(FileManagement.COMMA_DELIMITER)){
      rs = false;
      errorLine = buildErrorLine(rs, aLine);
    } else {
      String iccid = scanner.next();
      String msisdn = scanner.next();
      
      // Validate start with 0 character
      rs = EtopupUtil.validateStartWithZero(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Validate iccid and msisdn contain only 0-9 characters
      rs = EtopupUtil.validateNumberCharacterOnly(iccid);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      rs = EtopupUtil.validateNumberCharacterOnly(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Validate length
      rs = EtopupUtil.validateICCIDLength(iccid);
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
      
      // Validate duplicate
      rs = validateDuplicate(iccid);
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
    }
    
    errorFileContent.add(errorLine);
  }
  
  @SuppressWarnings("resource")
  private boolean validateDuplicate(String data) throws IOException {
    int countIccid = 0;
    int countMsisdn = 0;
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name()))
    {
      String aLine;
      String iccid;
      String msisdn;
   
      while (scanner.hasNextLine()){
        aLine = scanner.nextLine();
        Scanner lineScanner = new Scanner(aLine);
        lineScanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
        if (!lineScanner.hasNext()) {
          continue;
        }
        
        if (!aLine.contains(Constants.FileManagement.COMMA_DELIMITER)){
          continue;
        }
        
        iccid = lineScanner.next();
        msisdn = lineScanner.next();
      
        if (iccid.equals(data)) {
          countIccid++;
          if (countIccid > 1) {
            return false;
          }
        }
        
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
  
  /**
   * Xây dựng nội dung dòng lỗi từ dòng ban đầu cộng thêm đánh dấu lỗi: "--> Error"
   * và chuyển trạng thái uploadedFileError = true
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
  
  /**
   * Hiển thị thông báo thực hiện thao tác thành công hay thất bại lên màn hình
   * @param rs
   * @param successKey
   * @param failedKey
   */
  private void displayResultMessage(boolean rs, String successKey, String failedKey) {
    String message = null;
    if (rs) {
      message = getMessage(successKey);
      FacesUtil.addSuccessMessage(message);
      doFindSIMBatchs();
    } else {
      message = getErrorMessage(failedKey);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }
  
//  private void showErrorMsgForUploadedFileContent(String messageCode, String ... messageContents) {
//    
//    String message = getErrorMessage(messageCode);
//    FacesUtil.addErrorMsg(":simBatchForm:fileContentError", message, "");
//    String msisdnErrorListAsStr = messageContents[0];
//    if (StringUtils.isBlank(msisdnErrorListAsStr)) {
//      return;
//    }
//    
//    String[] errorFileContentArr = new String[errorFileContent.size()];
//    errorFileContent.toArray(errorFileContentArr);
//    
//    String msisdn;
//    String errorLine;
//    for (int i = 1; i < errorFileContentArr.length; i++) {
//      msisdn = StringUtils.split(errorFileContentArr[i], FileManagement.COMMA_DELIMITER)[1];
//      if (msisdnErrorListAsStr.contains(msisdn)) {
//        errorLine = buildErrorLine(false, errorFileContentArr[i]);
//        errorFileContentArr[i] = errorLine;
//      }
//    }
//    
//    errorFileContent.clear();
//    for (String line: errorFileContentArr) {
//      errorFileContent.add(line);
//    }
//
//    try {
//      errorFile = createErrorFileForUploadedFileTXT(uploadedFile.getFileName(), errorFileContent, SIMBatchEtopupController.class);
//    } catch (IOException e) {
//      logger.error("Error in creating error file");
//    }
//  }
  
  private void showErrorMsgForUploadedFileContent(String messageCode, String messageContent) {
    String message = getErrorMessage(messageCode);
    FacesUtil.addErrorMsg(":simBatchForm:fileContentError", message, "");
  }
  
  private void createErrorFile(String errorContent) {
    if (StringUtils.isBlank(errorContent)) {
      return;
    }
    
    String[] errorFileContentArr = new String[errorFileContent.size()];
    errorFileContent.toArray(errorFileContentArr);
    
    String msisdn;
    String errorLine;
    for (int i = 1; i < errorFileContentArr.length; i++) {
      msisdn = StringUtils.split(errorFileContentArr[i], FileManagement.COMMA_DELIMITER)[1];
      if (errorContent.contains(msisdn)) {
        errorLine = buildErrorLine(false, errorFileContentArr[i]);
        errorFileContentArr[i] = errorLine;
      }
    }
    
    errorFileContent.clear();
    for (String line: errorFileContentArr) {
      errorFileContent.add(line);
    }

    try {
      errorFile = createErrorFileForUploadedFileTXT(uploadedFile.getFileName(), errorFileContent, SIMBatchEtopupController.class);
    } catch (IOException e) {
      logger.error("Error in creating error file");
    }
  }
  
}
