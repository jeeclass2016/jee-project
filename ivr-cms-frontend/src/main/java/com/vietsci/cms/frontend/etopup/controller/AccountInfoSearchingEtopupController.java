package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.dto.AccountInfoSearchingDTO;
import com.vietsci.cms.frontend.etopup.model.AgentAccount;
import com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

/**
 * Controller class for Account Info Searching
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
public class AccountInfoSearchingEtopupController extends EtopupBaseController implements Serializable {

  /* logger */
  private static final Log logger = LogFactory.getLog(AccountInfoSearchingEtopupController.class);

  @Inject
  BalanceManagementEtopupService balanceManagementService;

  private AccountInfoSearchingDTO accountInfoSearchingDTO;
  private AgentAccount agentAccount;

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init AccountInfoSearchingEtopupController members");

    accountInfoSearchingDTO = new AccountInfoSearchingDTO();
    agentAccount = new AgentAccount();

    logger.debug("Init AccountInfoSearchingEtopupController members successfully");
  }

  /**
   * Action is invoked when users click on "Lấy thông tin" button
   */
  public void findAccountInfo() {
    logger.debug("Start findAccountInfo");

    try {
      agentAccount = balanceManagementService.findAccountInfo(accountInfoSearchingDTO);
    } catch(Exception e) {
      handleExceptionMessage(e, AccountInfoSearchingEtopupController.class);
    }

    logger.debug("Finish findAccountInfo");
  }

  /* ============= Getter and Setter methods ============= */

  public AccountInfoSearchingDTO getAccountInfoSearchingDTO() {
    return accountInfoSearchingDTO;
  }

  public void setAccountInfoSearchingDTO(AccountInfoSearchingDTO accountInfoSearchingDTO) {
    this.accountInfoSearchingDTO = accountInfoSearchingDTO;
  }

  public AgentAccount getAgentAccount() {
    return agentAccount;
  }

  public void setAgentAccount(AgentAccount agentAccount) {
    this.agentAccount = agentAccount;
  }
}
