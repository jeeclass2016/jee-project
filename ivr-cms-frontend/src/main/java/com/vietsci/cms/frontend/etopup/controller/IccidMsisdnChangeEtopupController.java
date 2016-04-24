package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.dto.AgentDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/** 
 * Controller class for ICCID and MSISDN change.
 * 
 * @author lam.lethanh
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value="session")
public class IccidMsisdnChangeEtopupController extends EtopupBaseController implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 7339728533204669819L;
  
  /**
   * logger.
   */
  private static final Log logger = LogFactory.getLog(IccidMsisdnChangeEtopupController.class);
  
  @Inject
  AgentManagementService agentManagementService;
  
  private AgentDTO agentDTO;
  private Agent agent;
  private boolean enableEditing;
  private String newICCID;
  private String newMSISDN;

  /* ============= Getter and setter methods ============= */
  public AgentDTO getAgentDTO() {
    return agentDTO;
  }

  public void setAgentDTO(AgentDTO agentDTO) {
    this.agentDTO = agentDTO;
  }
  
  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }
  
  public boolean isEnableEditing() {
    return enableEditing;
  }

  public void setEnableEditing(boolean enableEditing) {
    this.enableEditing = enableEditing;
  }
  
  public String getNewICCID() {
    return newICCID;
  }

  public void setNewICCID(String newICCID) {
    this.newICCID = newICCID;
  }

  public String getNewMSISDN() {
    return newMSISDN;
  }

  public void setNewMSISDN(String newMSISDN) {
    this.newMSISDN = newMSISDN;
  }
  
  /* ============= Business methods ============= */
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init IccidMsisdnChangeEtopupController members");
    agentDTO = new AgentDTO();
    enableEditing = false;
    newICCID = null;
    newMSISDN = null;
    logger.debug("Init IccidMsisdnChangeEtopupController members successfully");
  }

  /**
   * Tìm kiếm đại lý theo số thuê bao
   */
  public void  doFindAgentByMSISDN () {
    
    logger.debug("Start finding Agent by MSISDN");
    newICCID = null;
    newMSISDN = null;
    enableEditing = false;
    
    agent = agentManagementService.getAgentByMSISDN(agentDTO.getMsisdn());
    
    if (agent == null || agent.getMsisdn() == null) {
      logger.debug("Không tìm thấy đại lý nào có số thuê bao là: " + agentDTO.getMsisdn());
      String message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_SUBSCRIBER_NOT_EXIST);
      FacesUtil.addErrorMessage(null, message);
      enableEditing = false;
    } else {
      enableEditing = true;
    }
    
    logger.debug("End finding Agent by MSISDN");
  }
  
  /**
   * Cập nhật thông tin thuê bao của đại lý
   */
  public void doChangeICCIDAndMSISDN() {
    logger.debug("Change pair ICCID and MSISDN for agent with ID: " + agent.getAgentId());
    logger.debug("Old values: ICCID = " + agent.getIccid() + " MSISDN = " + agent.getMsisdn());
    logger.debug("New values: ICCID = " + newICCID + " MSISDN = " + newMSISDN);
    
    agent.setIccid(newICCID);
    agent.setMsisdn(newMSISDN);
    
    boolean rs;
    try {
      rs = agentManagementService.changeIccidAndMsisdnOfAgent(agent);
    } catch (Exception e) {
      logger.error("Đổi cặp ICCID-MSISDN: (" + agent.getIccid()
        + "," + agent.getMsisdn() + ") thất bại vì: " + e.getMessage());

      handleExceptionMessage(e, PromotionsEtopupController.class);
      return;
    }
    
    if (rs) {
      enableEditing = false;
    }
    
    displayResultMessage(rs, MessageConstants.ETOPUP_ICCID_MSISDN_CHANGE, ErrorConstants.ETOPUP_ICCID_MSISDN_CHANGE);
    
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
    } else {
      message = getErrorMessage(failedKey);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }

}
