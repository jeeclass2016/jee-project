package com.vietsci.cms.frontend.etopup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.common.util.EtopupFileUtil;
import com.vietsci.cms.frontend.etopup.dto.AgentBatchTraversalDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/**
 * Controller class for Agent Batch Traversal (Duyet dai ly theo lo)
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
public class AgentBatchTraversalController extends EtopupBaseController implements Serializable {

  private static final long serialVersionUID = 5810079431411781767L;

  /* logger */
  private static final Log logger = LogFactory.getLog(AgentBatchTraversalController.class);

  @Inject
  AgentManagementService agentManagementService;

  private String fileName;
  private List<AgentBatchTraversalDTO> agentList;

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init AgentBatchTraversalController members");

    agentList = new ArrayList<>();

    logger.debug("Init AgentBatchTraversalController members successfully");
  }

  /**
   * Action is invoked when uses click on "Upload file" button
   * @param event
   */
  public void handleFileUpload(FileUploadEvent event) {
    UploadedFile file = event.getFile();
    fileName = file.getFileName();

    String message = null;
    if(!EtopupFileUtil.isTxtFile(fileName)) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_FILE_EXTENSION_TXT);
      FacesUtil.addErrorMsg(null, message, "");
      return;
    }

    List<String> msisdnList = new ArrayList();

    try {
      msisdnList = EtopupFileUtil.readFile(file);

      if(msisdnList.isEmpty()) {
        message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_FILE_EMPTY);
        FacesUtil.addErrorMsg(null, message, "");
        return;
      }

      agentList = agentManagementService.findAgentsForBatchTraversal(msisdnList);

    } catch(IOException io) {

    }
  }

  /**
   * Action is invoked when uses click on "Duyệt đại lý" button
   */
  public void traverseAgents() {
    List<String> agentListToTraverse = new ArrayList<>();

    String message = null;
    if(agentList.isEmpty()) {
      message = getErrorMessage(ErrorConstants.ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH_UPLOAD_FILE);
      FacesUtil.addErrorMsg(null, message, "");
      return;
    }

    for(AgentBatchTraversalDTO agent: agentList) {
      if(agent.isValid()) {
        agentListToTraverse.add(agent.getMsisdn());
      }
    }

    if(agentListToTraverse.isEmpty()) {
      message = getErrorMessage(ErrorConstants.ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH_NO_AGENT_VALID);
      FacesUtil.addErrorMsg(null, message, "");
      return;
    }

    long stockStaffId = getCurrentStockStaffId();

    boolean isSuccess = false;
    try {
      isSuccess = agentManagementService.traverseAgents(agentListToTraverse, stockStaffId);

    } catch(Exception e) {
      handleExceptionMessage(e, AgentBatchTraversalController.class);
      return;
    }

    if(isSuccess) {
      refreshAgentList();

      message = getMessage(MessageConstants.ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH);
      FacesUtil.addSuccessMessage(message);
    } else {
      message = getErrorMessage(ErrorConstants.ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH_FAIL);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }

  private void refreshAgentList() {
    for(AgentBatchTraversalDTO agent : agentList) {
      if(agent.isValid()) {
        agent.setAgentStatus(Agent.AgentStatus.PASS_AUDIT.getName());
      }
    }
  }

  /* ============= Getter and Setter methods ============= */

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public List<AgentBatchTraversalDTO> getAgentList() {
    return agentList;
  }

  public void setAgentList(List<AgentBatchTraversalDTO> agentList) {
    this.agentList = agentList;
  }
}
