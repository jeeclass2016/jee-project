package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.dto.AgentDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.ICCIDManagentService;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

@Named(value = "changeICCIDInformationController")
@Scope("session")
public class ChangeICCIDInformationController extends EtopupBaseController implements Serializable {
  private static final long serialVersionUID = 5954508771669485395L;
  private static final Log logger = LogFactory.getLog(ChangeICCIDInformationController.class);

  @Inject
  AgentManagementService agentManagementService;

  @Inject
  ICCIDManagentService iccidManagentService;

  private AgentDTO agentDTO = new AgentDTO();
  private Agent agent = new Agent();

  private String newICCID;

  private boolean disableEditing = false;

  /**
   * Listeners
   */
  public void doFindICCID() {
    logger.debug("Search agent with msisnd: " + agent.getMsisdn());
    try {
      agent = agentManagementService.getAgentByMSISDN(agentDTO.getMsisdn());
      if (agent == null || agent.getAgentId() == null) {
        FacesUtil.addSuccessMessage("Không tìm thấy đại lý");
      }
      if (agent.getStatus() != Agent.Status.LOST_SIM.getValue()) {
        FacesUtil.addSuccessMessage("Đại lý phải ở trạng thái mất SIM để thực hiện tính năng này");
        disableEditing = true;
      } else {
        disableEditing = false;
      }
      newICCID = "";
    } catch (Exception e) {
      handleExceptionMessage(e, ChangeICCIDInformationController.class);
    }
  }

  public void doChangeICCIDInfor() {
    logger.debug("Change ICCID for agent with ID: " + agent.getAgentId());
    logger.debug("Old values: ICCID = " + agent.getIccid());
    logger.debug("New values: ICCID = " + newICCID);

    try {
      if (validateIccid()) {
        //update Agent iccid
        agent.setIccid(newICCID);
        boolean success = iccidManagentService.changeIccidOfAgent(agent);

          FacesUtil.addSuccessMessage(getMessage(MessageConstants.ETOPUP_AGENT_MANAGEMENT_UPDATE), "");

        //reset ui
        newICCID = "";
        disableEditing = false;
        if (success)
          doFindICCID();
      }

    } catch (Exception e) {
      handleExceptionMessage(e, ChangeICCIDInformationController.class);
    }
  }

  private boolean validateIccid() {
    if (newICCID.length() != 20) {
      FacesUtil.addErrorMsg(":changeIccidInforForm:messageForMsisdn", "ICCID có độ dài là 20 ký tự", "ICCID có độ dài là 20 ký tự");
      return false;
    }

    if (newICCID.equals(agent.getIccid())) {
      FacesUtil.addErrorMsg(":changeIccidInforForm:messageForMsisdn", "ICCID mới phải khác ICCID cũ", "ICCID mới phải khác ICCID cũ");
      return false;
    }

    //TODO check MC_SUBSCRIBER
    return true;
  }

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

  public boolean isDisableEditing() {
    return disableEditing;
  }

  public void setDisableEditing(boolean disableEditing) {
    this.disableEditing = disableEditing;
  }

  public String getNewICCID() {
    return newICCID;
  }

  public void setNewICCID(String newICCID) {
    this.newICCID = newICCID;
  }
}
