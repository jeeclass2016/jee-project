package com.vietsci.cms.frontend.etopup.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.vietsci.cms.frontend.etopup.common.util.EtopupStringUtil;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.AgentBatchUploadingDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.primefaces.AgentBatchUploadingEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/** 
 * Controller class for AgentBatchUploading
 * 
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

@Named
@Scope(value = "session")
public class AgentBatchUploadingEtopupController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 834366175095175084L;

  /* logger */
  private static final Log logger = LogFactory.getLog(AgentBatchUploadingEtopupController.class);

  @Inject
  AgentManagementService agentManagementService;

  private AgentBatchUploadingDTO agentBatchUploadingDTO;
  private AgentBatchUploadingEtopupLazyDataModel agentBatchUploadingLazyDataModel;

  private UploadedFile uploadedFile;
  List<String> uploadedFileContent = new ArrayList<String>();
  private StreamedContent errorFile;
  private boolean uploadedFileError;
  private List<String> errorFileContent = new ArrayList<String>();

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init AgentBatchUploadingEtopupController members");

    agentBatchUploadingDTO = new AgentBatchUploadingDTO();
    agentBatchUploadingLazyDataModel = new AgentBatchUploadingEtopupLazyDataModel(agentManagementService,
      agentBatchUploadingDTO);

    logger.debug("Init AgentBatchUploadingEtopupController members successfully");
  }

  public void doFindAgents() {
    logger.debug("Start doFind agents");

    if (validateTimeForFinding()) {
      agentBatchUploadingLazyDataModel = new AgentBatchUploadingEtopupLazyDataModel(agentManagementService,
        agentBatchUploadingDTO);
    }

    logger.debug("End doFind agents");
  }

  /**
   * Handle file uploading event
   * @param event
   *
   * @author hong.nguyenmai
   */
  public void handleFileUpload(FileUploadEvent event) {
    uploadedFile = event.getFile();
    agentBatchUploadingDTO.setAgentUploadedFileName(uploadedFile.getFileName());
    uploadedFileError = false;
  }

  public void doUploadAgentBatch() {
    logger.debug("Start doUploadAgentBatch");

    String fileName = agentBatchUploadingDTO.getAgentUploadedFileName();
    String message = null;

    if(EtopupStringUtil.isNullOrBlank(fileName)) {
      message = getMessage(MessageConstants.ETOPUP_COMMON_UPLOAD_FILE_INFO);
      FacesUtil.addSuccessMessage(message);
      return;
    }

    boolean isValid = false;
    try {
      isValid = isFileValid(fileName);
      agentBatchUploadingDTO.setAgentUploadedFileName(StringUtils.EMPTY);
      if(!isValid) {
        uploadedFileError = true;
        return;
      }
    } catch(IOException e) {
      handleExceptionMessage(e, AgentBatchUploadingEtopupController.class);
      return;
    }

    Boolean isSuccess = Boolean.FALSE;
    try {
      isSuccess = agentManagementService.addAgentBatch(uploadedFileContent);
    } catch (Exception e) {
      handleExceptionMessage(e, AgentBatchUploadingEtopupController.class);
      logger.debug("Finish doUploadAgentBatch");
      return;
    }
    if(isSuccess) {
      message = getMessage(MessageConstants.ETOPUP_COMMON_UPLOAD_FILE_SUCCESS);
      FacesUtil.addSuccessMessage(message);
    } else {
      message = getMessage(ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_FAILED);
      FacesUtil.addErrorMsg(null, message, "");
    }

    logger.debug("Finish doUploadAgentBatch");
  }

  private boolean validateTimeForFinding() {
    if (agentBatchUploadingDTO.getStartDate() != null
      && agentBatchUploadingDTO.getEndDate() != null
      && agentBatchUploadingDTO.getStartDate().after(agentBatchUploadingDTO.getEndDate())) {
      String message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_TIME_RANGE);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }

    return true;
  }

  private boolean isFileValid(String fileName) throws IOException {
    String message = null;
    if(!fileName.endsWith(Constants.FileManagement.TXT_EXTENSION)) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_FILE_EXTENSION_TXT);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }

    uploadedFileContent.clear();
    uploadedFileContent= readFile(uploadedFile);

    if(uploadedFileContent.size() == 0) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_FILE_EMPTY);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }

    if(!isFileContentValid(uploadedFileContent)) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_FAILED);
      FacesUtil.addErrorMsg(null, message, "");

      errorFile = createErrorFileForUploadedFileTXT(fileName, errorFileContent,
        AgentBatchUploadingEtopupController.class);
      return false;
    }
    return true;
  }

  private List<String> readFile(UploadedFile uploadedFile) throws IOException {
    InputStream is = null;
    BufferedReader reader = null;

    String line = "";
    List<String> content = new ArrayList<String>();

    is = uploadedFile.getInputstream();
    if(is == null) return content;

    reader = new BufferedReader(new InputStreamReader(is));

    while((line = reader.readLine()) != null) {
      if(StringUtils.isBlank(line)) {
        continue;
      }
      if ( line.startsWith("/*") || line.endsWith("*/") ) {
        continue;
      }
      content.add(line);
    }
    return content;
  }

  private boolean isFileContentValid(List<String> content) {
    boolean isContentValid = true;

    errorFileContent.clear();
    errorFileContent.add(content.get(0));

    List<String> msisdnList = getMSISDNList(content);

    String line = null;
    boolean isValid = false;

    if(msisdnList.size() == 0) {
      isContentValid = false;
      for(int i = 1; i < content.size(); i++) {
        line = content.get(i);
        errorFileContent.add(buildErrorLineContent(false, line));
      }
    } else {
      for(int i = 1; i < content.size(); i++) {
        line = content.get(i);
        isValid = validateLine(line, msisdnList);
        if(!isValid) isContentValid = false;
        errorFileContent.add(buildErrorLineContent(isValid, line));
      }
    }

    return isContentValid;
  }

  private List<String> getMSISDNList(List<String> content) {
    List<String> msisdnList = new ArrayList<String>();
    String line =  null;
    String[] tokens = null;

    for(int i = 0; i < content.size(); i++) {
      line = content.get(i);
      tokens = line.split(Constants.FileManagement.COMMA_DELIMITER);
      if(tokens.length == Constants.AgentManagement.AGENT_BATCH_UPLOADING_NUMBER_OF_TOKENS
        || tokens.length == Constants.AgentManagement.AGENT_BATCH_UPLOADING_NUMBER_OF_TOKENS - 1) {
        msisdnList.add(tokens[Constants.AgentManagement.AGENT_BATCH_MSISDN_INDEX]);
      }
    }
    return msisdnList;
  }

  private boolean validateLine(String line, List<String> msisdnList) {
    String[] tokens = line.split(Constants.FileManagement.COMMA_DELIMITER);

    // Check if data is enough
    if(tokens.length != Constants.AgentManagement.AGENT_BATCH_UPLOADING_NUMBER_OF_TOKENS
      && tokens.length != Constants.AgentManagement.AGENT_BATCH_UPLOADING_NUMBER_OF_TOKENS - 1) {
      return false;
    }

    String msisdn = tokens[Constants.AgentManagement.AGENT_BATCH_MSISDN_INDEX];
    String iccid = tokens[Constants.AgentManagement.AGENT_BATCH_ICCID_INDEX];

    // Check if MSISID start with O
    if(!EtopupUtil.validateStartWithZero(msisdn)) return false;

    // Check if MSISDN and ICCID contain only digits (0-9)
    if(!EtopupUtil.validateNumberCharacterOnly(msisdn)) return false;
    if(!EtopupUtil.validateNumberCharacterOnly(iccid)) return false;

    // Check if MSISDN.length = {10, 15}
    if(! EtopupUtil.validatePhoneNumberLength(msisdn)) return false;
    // Check if ICCID.length = 20
    if(!EtopupUtil.validateICCIDLength(iccid)) return false;

    // Check if MSISDN is duplicated
    if(Collections.frequency(msisdnList, msisdn) > 1) {
      return false;
    }

    // Check if MSISDN exists
    Agent agent = agentManagementService.getAgentByMSISDN(msisdn);
    if(msisdn.equals(agent.getMsisdn())) return false;

    return true;
  }


  public AgentBatchUploadingDTO getAgentBatchUploadingDTO() {
    return agentBatchUploadingDTO;
  }

  public void setAgentBatchUploadingDTO(AgentBatchUploadingDTO agentBatchUploadingDTO) {
    this.agentBatchUploadingDTO = agentBatchUploadingDTO;
  }

  public AgentBatchUploadingEtopupLazyDataModel getAgentBatchUploadingLazyDataModel() {
    return agentBatchUploadingLazyDataModel;
  }

  public void setAgentBatchUploadingLazyDataModel(AgentBatchUploadingEtopupLazyDataModel agentBatchUploadingLazyDataModel) {
    this.agentBatchUploadingLazyDataModel = agentBatchUploadingLazyDataModel;
  }

  public StreamedContent getErrorFile() {
    errorFile = checkToReopenErrorFile(errorFile, AgentBatchUploadingEtopupController.class);
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
}