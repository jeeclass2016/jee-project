package com.vietsci.cms.frontend.etopup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import com.vietsci.cms.frontend.etopup.common.util.Region;
import com.vietsci.cms.frontend.etopup.common.util.Constants.FileManagement;
import com.vietsci.cms.frontend.etopup.dto.AgentAddressMgmtDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.AgentAddressManagement;
import com.vietsci.cms.frontend.etopup.primefaces.AgentAddressDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentAddressManagementService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/** 
 * Controller class for Agent Address management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value="session")
public class AgentAddressBatchManagementController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7591408420817083750L;

  /**
  * logger.
  */
  private static final Log logger = LogFactory.getLog(AgentAddressBatchManagementController.class);
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init AgentAddressBatchManagementController members");
    agentAddressMgmtDTO = new AgentAddressMgmtDTO();
    
    statusMap = new HashMap<String, String>();
    statusMap.put(Constants.AgentAddressManagement.ACTIVE_STATUS_VALUE, Constants.AgentAddressManagement.ACTIVE_STATUS_LABEL);
    statusMap.put(Constants.AgentAddressManagement.INACTIVE_STATUS_VALUE, Constants.AgentAddressManagement.INACTIVE_STATUS_LABEL);
    initRegionMap();
    
    agentAddressDataModel = new AgentAddressDataModel(new ArrayList<AgentAddressManagement>());
    
    uploadedFile = null;
    uploadedFileError = false;
    errorFileContent.clear();
    
    logger.debug("Init AgentAddressBatchManagementController members successfully");
  }
  

  public void findAgentAddressList() {
    doFindAgentAddressList();
    logger.debug("Finished finding Agent Address with msisds: " + agentAddressMgmtDTO.getMsisdn());
  }
  
  private void doFindAgentAddressList() {
    String msisdn = agentAddressMgmtDTO.getMsisdn();
    logger.debug("Start finding Agent Address with msisdn: " + msisdn);
    FacesContext context = FacesContext.getCurrentInstance();
    if (msisdn == null || msisdn.trim().equals("")) {
      /*context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.AgentAddressManagement.OPERATION_FAILED_MESSAGE, "Bạn phải nhập số thuê bao"));*/
      FacesUtil.addErrorMsg("frm2:messageForMsisdn", "Bạn phải nhập số thuê bao", "");
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bạn phải nhập số thuê bao", ""));
      agentAddressDataModel = new AgentAddressDataModel(new ArrayList<AgentAddressManagement>());
      return;
    }
    List<Agent> agentList = agentAddressManagementService.getAgentByMSISDN(msisdn);

    if (agentList == null || agentList.size() <= 0) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.AgentAddressManagement.OPERATION_FAILED_MESSAGE, "Số thuê bao không tồn tại"));
      return;
    }
    processingAgent = agentList.get(0);
    List<AgentAddressManagement> agentAddressList = new ArrayList<AgentAddressManagement>();
    Set<AgentAddressManagement> agentAddressSet = new HashSet<AgentAddressManagement>();
    for (Agent agent : agentList) {
      Set<AgentAddressManagement> agentAddressManagements = agent.getAgentAddressManagements();
      if (agentAddressManagements == null || agentAddressManagements.size() <= 0) {
        continue;
      }
      for (AgentAddressManagement agentAddress : agentAddressManagements) {
        agentAddress.setAgent(agent);
        agentAddressSet.add(agentAddress);
      }      
    }
    
    agentAddressList.addAll(agentAddressSet);
    agentAddressDataModel = new AgentAddressDataModel(agentAddressList);
  }
  
  public void doUploadBatch() {
    boolean rs = false;
    uploadedFileError = false;
    errorFileContent.clear();

    if (uploadedFile == null) {
      showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_NOT_SELECTED, "");
      return;
    }
    
    try {
      rs = validateUploadedFileContent();
      if (rs) {
        rs = agentAddressManagementService.createAgentAddressBatch(uploadedFile);
      } 
    } catch (Exception e) {
      logger.error("Failed: " + e.getMessage());
      if (e instanceof CmsRestException) {
        String bodyMessage = e.getMessage();
        Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
        if (bodyMessageMap != null && bodyMessageMap.size() > 0) {
          String messageCode = bodyMessageMap.get(Constants.MESSAGE_CODE);
          String message = bodyMessageMap.get(Constants.MESSAGE);
            //showErrorMsgForUploadedFileContent(messageCode, message);
          FacesUtil.addErrorMsg(":frm2:fileContentError", message, message);
          return;
          //}
        }
      }
      handleExceptionMessage(e, AgentAddressManagementController.class);
      return;
    }

    displayResultMessage(rs, MessageConstants.ETOPUP_COMMON_UPLOAD_FILE_SUCCESS, ErrorConstants.ETOPUP_COMMON_UPLOAD_FILE_FAILED);
  }

  
  private void initRegionMap() {
    this.regionMap = new HashMap<>();
    this.regionMap.put(Region.NONE.getValue(), Region.NONE.getName());
    this.regionMap.put(Region.NORTHERN.getValue(), Region.NORTHERN.getName());
    this.regionMap.put(Region.SOUTHERN.getValue(), Region.SOUTHERN.getName());
    this.regionMap.put(Region.CENTRAL.getValue(), Region.CENTRAL.getName());
  }
  
  public String showStatusInfo(String status) {
    return statusMap.get(status);
  }
  
  public String showRegionInfo(String region) {
    if (region != null && !"".equals(region.trim())) {
      try {
        int regionId = Integer.parseInt(region.trim());
        return regionMap.get(regionId);
      } catch (NumberFormatException e) {
        logger.error("Region in DB is not a ID. Real value is " + region);
        return region;
      }
    }
    return "";
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
        showErrorMsgForUploadedFileContent(ErrorConstants.ETOPUP_AGENT_ADDRESS_BATCH_UPLOADING_NO_CONTENT, "");
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
    } else if (StringUtils.countMatches(aLine, FileManagement.COMMA_DELIMITER) < 2){  
      rs = false;
      errorLine = buildErrorLine(rs, aLine);
    } else {
      String msisdn = scanner.next();
      String province = scanner.next();
      String district = scanner.next();
      
      String map = "0";// set default value of status: "0" = Khong hoat dong
      if (scanner.hasNext()) {
        map = scanner.next();
      }
      
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
      rs = validateDuplicate(province, district);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Validate province & district
      rs = StringUtils.isNotBlank(province) && EtopupUtil.validateAlphabetAndNumberCharacterOnly(province.replace(" ", ""));
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      rs = StringUtils.isNotBlank(district) && EtopupUtil.validateAlphabetAndNumberCharacterOnly(district.replace(" ", ""));
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
      
      // Check whether the province / district is valid or not
      rs = isValidAddressData(province, district);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
      
      // Check whether the province / district has been added for this MSISDN
      rs = isValidAgentAddressData(msisdn, province, district);
      errorLine = buildErrorLine(rs, aLine);
      if (!rs) {
        errorFileContent.add(errorLine);
        return;
      }
    }
    
    errorFileContent.add(errorLine);
  }
  
  @SuppressWarnings("resource")
  private boolean validateDuplicate(String provinceData, String districtData) throws IOException {
    int countDuplicateItems = 0;
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name()))
    {
      String aLine;
      String msisdn;
      String province;
      String district;
   
      while (scanner.hasNextLine()){
        aLine = scanner.nextLine();
        Scanner lineScanner = new Scanner(aLine);
        lineScanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
        if (!lineScanner.hasNext()) {
          continue;
        }
        
        if (StringUtils.countMatches(aLine, FileManagement.COMMA_DELIMITER) < 2){
          continue;
        }
        
        msisdn = lineScanner.next();
        province = lineScanner.next();
        district = lineScanner.next();
      
        if (province.equals(provinceData)
            && district.equals(districtData)) {
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
    AgentAddressMgmtDTO agentAddressMgmtDTO = new AgentAddressMgmtDTO();
    agentAddressMgmtDTO.setMsisdn(msisdn.trim());
    
    int numberOfAgentAddressWithGivenCondition = agentAddressManagementService.countAgentByMSISDN(msisdn);
    if (numberOfAgentAddressWithGivenCondition <= 0) {
      return false;
    }
    return true;
  }
  
  private boolean isValidAgentAddressData(String msisdn, String province, String district) {
    if (StringUtils.isBlank(msisdn) || StringUtils.isBlank(province) 
        || StringUtils.isBlank(district)) {
      return false;
    }
    AgentAddressMgmtDTO agentAddressMgmtDTO = new AgentAddressMgmtDTO();
    agentAddressMgmtDTO.setMsisdn(msisdn.trim());
    agentAddressMgmtDTO.setProvince(province.trim());
    agentAddressMgmtDTO.setDistrict(district.trim());
    
    int numberOfAgentAddressWithGivenCondition = agentAddressManagementService.countTotalRecord(agentAddressMgmtDTO);
    if (numberOfAgentAddressWithGivenCondition > 0) {
      return false;
    }
    return true;
  }
  
  private boolean isValidAddressData(String province, String district) {
    if (StringUtils.isBlank(province) || StringUtils.isBlank(district)) {
      return false;
    }
    
    int numberOfAddressDataWithGivenCondition = agentAddressManagementService.countAddressData(province, district);
    if (numberOfAddressDataWithGivenCondition <= 0) {
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
    FacesUtil.addErrorMsg(":frm2:fileContentError", message, message);
    
    String[] errors = StringUtils.split(messageContents[0], FileManagement.COMMA_DELIMITER);
    if (errors.length == 0) {
      return;
    }
    
    int i = 0;
    for (;i < errors.length; i++) {
      if ((i%2)==1) {
        message = errors[i-1] + FileManagement.COMMA_DELIMITER + errors[i];
        FacesUtil.addErrorMsg(":frm2:fileContentError", message,message);
      }
    }
    
    message = errors[i-1]; // For last element if error.length is odd
    FacesUtil.addErrorMsg(":frm2:fileContentError", message,message);
  }
  
  private void displayResultMessage(boolean rs, String successKey, String failedKey) {
    String message = null;
    if (rs) {
      message = getMessage(successKey);
      FacesUtil.addSuccessMessage(message);
      //doFindSIMBatchs();
    } else {
      message = getErrorMessage(failedKey);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }
  /* **** GETTERS & SETTERS **** */
  @Inject
  private AgentAddressManagementService agentAddressManagementService;
  
  // so thue bao
  private AgentAddressMgmtDTO agentAddressMgmtDTO;
  private AgentAddressMgmtDTO newAgentAddressMgmtDTO;
  private AgentAddressMgmtDTO editedAgentAddressMgmtDTO;
  private AgentAddressManagement newAgentAddressManagement;
  private AgentAddressManagement editedAgentAddressManagement;
  private Agent processingAgent;
  private Map<String, String> statusMap;
  
  private List<AgentAddressMgmtDTO> agentAddressList;
  private AgentAddressDataModel agentAddressDataModel;

  private HashMap<Integer, String> regionMap;
  
  private UploadedFile uploadedFile;
  private StreamedContent errorFile;
  private boolean uploadedFileError;
  private final List<String> errorFileContent = new ArrayList<String>();
  private String fileContentErrorMsg;
  
  

  public AgentAddressMgmtDTO getAgentAddressMgmtDTO() {
    return agentAddressMgmtDTO;
  }

  public void setAgentAddressMgmtDTO(AgentAddressMgmtDTO agentAddressMgmtDTO) {
    this.agentAddressMgmtDTO = agentAddressMgmtDTO;
  }

  public AgentAddressMgmtDTO getNewAgentAddressMgmtDTO() {
    return newAgentAddressMgmtDTO;
  }

  public void setNewAgentAddressMgmtDTO(AgentAddressMgmtDTO newAgentAddressMgmtDTO) {
    this.newAgentAddressMgmtDTO = newAgentAddressMgmtDTO;
  }

  public AgentAddressMgmtDTO getEditedAgentAddressMgmtDTO() {
    return editedAgentAddressMgmtDTO;
  }


  public void setEditedAgentAddressMgmtDTO(AgentAddressMgmtDTO editedAgentAddressMgmtDTO) {
    this.editedAgentAddressMgmtDTO = editedAgentAddressMgmtDTO;
  }


  public List<AgentAddressMgmtDTO> getAgentAddressList() {
    return agentAddressList;
  }


  public void setAgentAddressList(List<AgentAddressMgmtDTO> agentAddressList) {
    this.agentAddressList = agentAddressList;
  }

  public Agent getProcessingAgent() {
    return processingAgent;
  }


  public void setProcessingAgent(Agent processingAgent) {
    this.processingAgent = processingAgent;
  }


  public Map<String, String> getStatusMap() {
    return statusMap;
  }


  public AgentAddressManagement getNewAgentAddressManagement() {
    return newAgentAddressManagement;
  }


  public void setNewAgentAddressManagement(AgentAddressManagement newAgentAddressManagement) {
    this.newAgentAddressManagement = newAgentAddressManagement;
  }


  public AgentAddressManagement getEditedAgentAddressManagement() {
    return editedAgentAddressManagement;
  }

  public void setEditedAgentAddressManagement(AgentAddressManagement editedAgentAddressManagement) {
    this.editedAgentAddressManagement = editedAgentAddressManagement;
  }


  public void setStatusMap(Map<String, String> statusMap) {
    this.statusMap = statusMap;
  }

  public AgentAddressDataModel getAgentAddressDataModel() {
    return agentAddressDataModel;
  }

  public void setAgentAddressDataModel(AgentAddressDataModel agentAddressDataModel) {
    this.agentAddressDataModel = agentAddressDataModel;
  }


  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }


  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
  }


  public StreamedContent getErrorFile() {
    errorFile = checkToReopenErrorFile(errorFile, AgentAddressBatchManagementController.class);
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

}
