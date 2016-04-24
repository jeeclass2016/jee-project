package com.vietsci.cms.frontend.etopup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.vietsci.cms.frontend.etopup.dto.BatchPromotionDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.PromotionEtopup;
import com.vietsci.cms.frontend.etopup.service.PromotionEtopupService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/** 
 * Controller class for Promotion Batch management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value="session")
public class PromotionBatchEtopupController extends EtopupBaseController implements Serializable {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 4046842921378597430L;

  /**
  * logger.
  */
  private static final Log logger = LogFactory.getLog(PromotionBatchEtopupController.class);
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init PromotionBatchEtopupController members");
    
    logger.debug("Init PromotionBatchEtopupController members successfully");
  }
  
  public void onLoadPage() {
    logger.debug("On load 'Khuyen Mai Theo Lo' page");
    promotionList = promotionEtopupService.findActivePromotions();
    promotionMap = new HashMap<>();
    for (PromotionEtopup p : promotionList) {
      promotionMap.put(p.getCode(), p.getDescription());
    }
  }
  
  public void doUploadBatch() {
    boolean rs = false;
    uploadedFileError = false;
    errorFileContent.clear();
    targetMsisdnList = new ArrayList<String>();
    targetAgentList = new ArrayList<Agent>();
    transferedAmountList = new ArrayList<Double>();
    
    batchPromotionDTO = new BatchPromotionDTO();
    
    if (uploadedFile == null) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_NOT_SELECTED, "");
      return;
    }
    
    try {
      rs = validateUploadedFileContent();
      if (rs) {
        batchPromotionDTO.setPromotionCode(promotionCode);
        batchPromotionDTO.setStaffId(getCurrentUserId());
        batchPromotionDTO.setTargetAgentList(targetAgentList);
        batchPromotionDTO.setTargetMsisdnList(targetMsisdnList);
        batchPromotionDTO.setTransferedAmountList(transferedAmountList);
        
        rs = promotionEtopupService.processPromotionBatch(batchPromotionDTO);
      } 
    } catch (Exception e) {
      logger.error("Failed: " + e.getMessage());
      if (e instanceof CmsRestException) {
        String bodyMessage = e.getMessage();
        Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
        if (bodyMessageMap != null && bodyMessageMap.size() > 0) {
          //String messageCode = bodyMessageMap.get(Constants.MESSAGE_CODE);
          String message = bodyMessageMap.get(Constants.MESSAGE);
            //showErrorMsgForUploadedFileContent(messageCode, message);
          FacesUtil.addErrorMsg(":frm:fileContentError", message, message);
          return;
          //}
        }
      }
      handleExceptionMessage(e, AgentAddressManagementController.class);
      return;
    }

    displayResultMessage(rs, MessageConstants.ETOPUP_COMMON_UPLOAD_FILE_SUCCESS, ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_FAILED);
  }
  
  /**
   * Handle uploaded file
   * @param event
   */
  public void handleFileUpload(FileUploadEvent event) {
    logger.debug("File name: " + event.getFile().getFileName());
    logger.debug("File size: " + event.getFile().getSize());
    uploadedFile = event.getFile();
    uploadedFileError = false;
    errorFileContent.clear();
    errorFile = null;
  }
  
  private boolean validateUploadedFileContent() throws IOException {
    if (!uploadedFile.getFileName().endsWith(FileManagement.TXT_EXTENSION)) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_FILE_EXTENSION_TXT);
      return false;
    }
    
    if (uploadedFile.getSize() == 0) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_FILE_EMPTY, "");
      return false;
    }
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name()))
    {
      // Skip first line
      if (scanner.hasNextLine()) {
        errorFileContent.add(scanner.nextLine());
      }
      
      // No content in file to process
      if (!scanner.hasNextLine()) {
        showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_PROMOTION_BATCH_UPLOADING_NO_CONTENT, "");
        return false;
      }

      while (scanner.hasNextLine()){
        processLine(scanner.nextLine());
      }
    }
    
    // Create error file to return if there is error
    if (uploadedFileError) {
      errorFile = createErrorFileForUploadedFileTXT(uploadedFile.getFileName(), errorFileContent, AgentAddressBatchManagementController.class);
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
    } else if (StringUtils.countMatches(aLine, FileManagement.COMMA_DELIMITER) < 1){  
      rs = false;
      errorLine = buildErrorLine(rs, aLine);
    } else {
      String msisdn = scanner.next();
      String transAmount = scanner.next();
      
      
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
      
      // Validate length      
      rs = EtopupUtil.validatePhoneNumberLength(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Validate duplicate
      rs = validateDuplicate(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Validate transAmount
      rs = isValidTransAmount(transAmount);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Check whether the MSISDN existed in the DB
      rs = isValidMSISDN(msisdn);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
    }
    
    errorFileContent.add(errorLine);
  }
  
  @SuppressWarnings("resource")
  private boolean validateDuplicate(String msisdnData) throws IOException {
    int countDuplicateItems = 0;
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name()))
    {
      String aLine;
      String msisdn;
   
      while (scanner.hasNextLine()){
        aLine = scanner.nextLine();
        Scanner lineScanner = new Scanner(aLine);
        lineScanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
        if (!lineScanner.hasNext()) {
          continue;
        }
        
        if (StringUtils.countMatches(aLine, FileManagement.COMMA_DELIMITER) < 1){
          continue;
        }
        
        msisdn = lineScanner.next();
      
        if (msisdn.trim().equals(msisdnData.trim())) {
          countDuplicateItems++;
          if (countDuplicateItems > 1) {
            return false;
          }
        }
        
      }
    }
    
    return true;
  }
  
  private boolean isValidMSISDN(String msisdn) {
    if (StringUtils.isBlank(msisdn)) {
      return false;
    }
    
    Agent agent = promotionEtopupService.getOperatingActiveAgentByMSISDN(msisdn.trim());
    if (agent == null || !"1".equals(agent.getMpinStatus())) {
      return false;
    }
    targetAgentList.add(agent);
    targetMsisdnList.add(msisdn.trim());
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
      transAmnt = Math.floor(transAmnt * 10000)/10000;
      transferedAmountList.add(transAmnt);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
  
  private String buildErrorLine(boolean check, String originLine) {
    
    String errorLine = buildErrorLineContent(check, originLine);
    if (!check) {
      uploadedFileError = true;
    }
    
    return errorLine.toString();
  }
  
  private void showErrorMsgForUploadedFileContent(String messageCode, String ... messageContents) {
    String message = getErrorMessage(messageCode);
    FacesUtil.addErrorMsg(":frm:fileContentError", message, message);
    
    String[] errors = StringUtils.split(messageContents[0], FileManagement.COMMA_DELIMITER);
    if (errors.length == 0) {
      return;
    }
    
    int i = 0;
    for (;i < errors.length; i++) {
      if ((i%2)==1) {
        message = errors[i-1] + FileManagement.COMMA_DELIMITER + errors[i];
        FacesUtil.addErrorMsg(":frm:fileContentError", message,message);
      }
    }
    
    message = errors[i-1]; // For last element if error.length is odd
    FacesUtil.addErrorMsg(":frm:fileContentError", message,message);
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

  /* **** GETTERS & SETTERS **** */
  @Inject
  private PromotionEtopupService promotionEtopupService;
  
  private List<PromotionEtopup> promotionList;
  
  private Map<String, String> promotionMap;
  
  private BatchPromotionDTO batchPromotionDTO;
  private String promotionCode; 
  
  private List<String> targetMsisdnList = new ArrayList<String>();
  private List<Agent> targetAgentList = new ArrayList<Agent>();
  private List<Double> transferedAmountList = new ArrayList<Double>();
  
  private UploadedFile uploadedFile;
  private StreamedContent errorFile;
  private boolean uploadedFileError;
  private final List<String> errorFileContent = new ArrayList<String>();
  private String fileContentErrorMsg;

  public List<PromotionEtopup> getPromotionList() {
    return promotionList;
  }

  public void setPromotionList(List<PromotionEtopup> promotionList) {
    this.promotionList = promotionList;
  }

  public Map<String, String> getPromotionMap() {
    return promotionMap;
  }

  public void setPromotionMap(Map<String, String> promotionMap) {
    this.promotionMap = promotionMap;
  }

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
  }

  public StreamedContent getErrorFile() {
    errorFile = checkToReopenErrorFile(errorFile, PromotionBatchEtopupController.class);
    return errorFile;
  }

  public void setErrorFile(StreamedContent errorFile) {
    this.errorFile = errorFile;
  }

  public boolean isUploadedFileError() {
    return uploadedFileError;
  }

  public void setUploadedFileError(boolean uploadedFileError) {
    this.uploadedFileError = uploadedFileError;
  }

  public String getFileContentErrorMsg() {
    return fileContentErrorMsg;
  }

  public void setFileContentErrorMsg(String fileContentErrorMsg) {
    this.fileContentErrorMsg = fileContentErrorMsg;
  }

  public List<String> getErrorFileContent() {
    return errorFileContent;
  }

  public BatchPromotionDTO getBatchPromotionDTO() {
    return batchPromotionDTO;
  }

  public void setBatchPromotionDTO(BatchPromotionDTO batchPromotionDTO) {
    this.batchPromotionDTO = batchPromotionDTO;
  }

  public String getPromotionCode() {
    return promotionCode;
  }

  public void setPromotionCode(String promotionCode) {
    this.promotionCode = promotionCode;
  }
}
