package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.dto.AgentDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

@Named
@Scope(value = "session")
public class PasswordResetController extends EtopupBaseController implements Serializable {
  private static final long serialVersionUID = 3921675823369555844L;

  @Inject
  AgentManagementService agentManagementService;

  private AgentDTO agentDTO = new AgentDTO();
  private Agent agent = new Agent();
  private String validateMsisdn;
  private boolean enableChangingPassword = false;
  private boolean enableConfirmationForResetAgain = false;

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

  public String getValidateMsisdn() {
    return validateMsisdn;
  }

  public void setValidateMsisdn(String validateMsisdn) {
    this.validateMsisdn = validateMsisdn;
  }

  public boolean isEnableChangingPassword() {
    return enableChangingPassword;
  }

  public void setEnableChangingPassword(boolean enableChangingPassword) {
    this.enableChangingPassword = enableChangingPassword;
  }

  public boolean isEnableConfirmationForResetAgain() {
    return enableConfirmationForResetAgain;
  }

  public void setEnableConfirmationForResetAgain(boolean enableConfirmationForResetAgain) {
    this.enableConfirmationForResetAgain = enableConfirmationForResetAgain;
  }

  public void doFindAgent() {

    try {
      reformatMsisdnBeforeSearching();

      agent = agentManagementService.getAgentByMSISDN(agentDTO.getMsisdn());

      if (agent != null && agent.getAgentId() != null) {
        enableChangingPassword = true;
      } else {
        FacesUtil.addErrorMsg(null, "Không tìm thấy kết quả nào", "");
        enableChangingPassword = false;
      }
      validateMsisdn = "";
      updateConfirmationFlags();

    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  private void updateConfirmationFlags() {
    enableConfirmationForResetAgain = false;
    if (agent != null && agent.getAgentId() != null && agent.getMpinStatus().equals("0") && !agent.getMpin().equals("123456")) {
      enableConfirmationForResetAgain = true;
    }
  }

  private void reformatMsisdnBeforeSearching() {
    String msisdn = agentDTO.getMsisdn();
    if (msisdn != null) {
      agentDTO.setMsisdn(msisdn.trim());
    }
  }

  public void doChangePassword() {
    try {
      if (validateMsisdn()) {
        long userId = getCurrentUserId();
        String userName = getCurrentUserName();
        agentManagementService.doResetAgentPassword(agent, userId, userName);
        FacesUtil.addSuccessMessage(getMessage(MessageConstants.ETOPUP_RESET_PASSWORD), "");
        enableChangingPassword = false;
      }
    } catch (Exception e) {
      handleExceptionMessage(e, AgentManagementController.class);
    }
  }

  private boolean validateMsisdn() {
    if (!agent.getMsisdn().equals(this.validateMsisdn)) {
      FacesUtil.addErrorMsg(null, getErrorMessage(ErrorConstants.PASSWORD_RESET_MSISDN_NOT_MATCHED), "");
      return false;
    } else {
      return true;

    }
  }
}
