package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.AccountInfoSearchingDTO;
import com.vietsci.cms.frontend.etopup.dto.AccountLockingDTO;
import com.vietsci.cms.frontend.etopup.model.AgentAccount;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.primefaces.AccountLockingEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService;
import com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Controller class for Account Locking (Khoa tai khoan)
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
public class AccountLockingEtopupController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -2221301034227733304L;

  /* logger */
  private static final Log logger = LogFactory.getLog(AccountLockingEtopupController.class);

  @Inject
  private TransManagementEtopupService transManagementService;

  @Inject
  private BalanceManagementEtopupService balanceManagementService;

  private AccountLockingDTO accountLockingDTO;
  private AccountLockingEtopupLazyDataModel accountLockingLazyDataModel;

  private Trans selectedTrans;
  private AgentAccount agentAccount;
  private String agentAccountStatus;

  private boolean isLockBtnDisable;
  private boolean isUnlockBtnDisable;
  private boolean isRecoverBtnDisable;

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init AccountLockingEtopupController members");

    accountLockingDTO = new AccountLockingDTO();

    isUnlockBtnDisable = true;
    isRecoverBtnDisable = true;

    logger.debug("Init AccountLockingEtopupController members successfully");
  }

  /**
   * Action is invoked when users click on "Tìm" button
   *
   * @author hong.nguyenmai
   */
  public void findTrans() {
    logger.debug("Start findTrans");

    String message = null;

    if(!EtopupUtil.validateTimeRange(accountLockingDTO.getStartDate(), accountLockingDTO.getEndDate())) {
      message = getErrorMessage(ErrorConstants.ETOPUP_COMMON_TIME_RANGE);
      FacesUtil.addErrorMsg(null, message, "");
      return;
    }

    accountLockingLazyDataModel = new AccountLockingEtopupLazyDataModel(transManagementService, accountLockingDTO);

    logger.debug("Finish findTrans");
  }

  /**
   * Action is invoked when users select a row
   *
   * @author hong.nguyenmai
   */
  public void onSelectRowListener() {
    AccountInfoSearchingDTO accountInfoSearchingDTO = new AccountInfoSearchingDTO();
    accountInfoSearchingDTO.setMsisdn(selectedTrans.getTargetMsisdn());

    try {
      agentAccount = balanceManagementService.findAccountInfo(accountInfoSearchingDTO);

      if(agentAccount.getThreshold().compareTo(BigDecimal.ZERO) > 0) {
        isLockBtnDisable = true;
        isUnlockBtnDisable = false;
        isRecoverBtnDisable = false;
      } else {
        isLockBtnDisable = false;
        isUnlockBtnDisable = true;
        isRecoverBtnDisable = true;
      }

    } catch(Exception e) {
      agentAccount = new AgentAccount();
      agentAccountStatus = "N/A";
      return;
    }

    if(AgentAccount.Status.ACTIVE.getValue() == agentAccount.getStatus()) {
      agentAccountStatus = AgentAccount.Status.ACTIVE.getName();
    } else {
      agentAccountStatus = AgentAccount.Status.TEMPORAL_LOCKED.getName();
    }
  }

  /**
   * Action is invoked when users click on "Khóa TK" button
   *
   * @author hong.nguyenmai
   */
  public void lockBalance() {
    accountLockingDTO.setSendNumber(selectedTrans.getSourceMsisdn());
    accountLockingDTO.setReceivedNumber(selectedTrans.getTargetMsisdn());
    accountLockingDTO.setTransAmount(selectedTrans.getTransAmount());
    accountLockingDTO.setUserId(getCurrentUserId());

    String message = null;
    try {
      agentAccount = balanceManagementService.lockBalance(accountLockingDTO);
      message = getMessage(MessageConstants.ETOPUP_BALANCE_MANAGEMENT_LOCK);
      FacesUtil.addSuccessMessage(message);
    } catch(Exception e) {
      handleExceptionMessage(e, AccountLockingEtopupController.class);
      return;
    }

    isLockBtnDisable = true;
    isUnlockBtnDisable = false;
    isRecoverBtnDisable = false;
  }

  /**
   * Action is invoked when users click on "Mở khóa TK" button
   *
   * @author hong.nguyenmai
   */
  public void unlockBalance() {
    accountLockingDTO.setSendNumber(selectedTrans.getSourceMsisdn());
    accountLockingDTO.setReceivedNumber(selectedTrans.getTargetMsisdn());
    accountLockingDTO.setTransAmount(selectedTrans.getTransAmount());
    accountLockingDTO.setUserId(getCurrentUserId());

    String message = null;
    try {
      agentAccount = balanceManagementService.unlockBalance(accountLockingDTO);
      message = getMessage(MessageConstants.ETOPUP_BALANCE_MANAGEMENT_UNLOCK);
      FacesUtil.addSuccessMessage(message);
    } catch(Exception e) {
      handleExceptionMessage(e, AccountLockingEtopupController.class);
      return;
    }

    isLockBtnDisable = false;
    isUnlockBtnDisable = true;
    isRecoverBtnDisable = true;
  }

  /**
   * Action is invoked when users click on "Điều chỉnh TK" button
   *
   * @author hong.nguyenmai
   */
  public void recoverBalance() {
    accountLockingDTO.setSendNumber(selectedTrans.getSourceMsisdn());
    accountLockingDTO.setReceivedNumber(selectedTrans.getTargetMsisdn());
    accountLockingDTO.setTransAmount(selectedTrans.getTransAmount());
    accountLockingDTO.setUserId(getCurrentUserId());

    String message = null;
    try {
      agentAccount = balanceManagementService.recoverBalance(accountLockingDTO);
      message = getMessage(MessageConstants.ETOPUP_BALANCE_MANAGEMENT_ADJUST);
      FacesUtil.addSuccessMessage(message);
    } catch(Exception e) {
      handleExceptionMessage(e, AccountLockingEtopupController.class);
      return;
    }

    isLockBtnDisable = false;
    isUnlockBtnDisable = true;
    isRecoverBtnDisable = true;
  }

  /* ============= Getter and Setter methods ============= */
  public AccountLockingDTO getAccountLockingDTO() {
    return accountLockingDTO;
  }

  public void setAccountLockingDTO(AccountLockingDTO accountLockingDTO) {
    this.accountLockingDTO = accountLockingDTO;
  }

  public AccountLockingEtopupLazyDataModel getAccountLockingLazyDataModel() {
    return accountLockingLazyDataModel;
  }

  public void setAccountLockingLazyDataModel(AccountLockingEtopupLazyDataModel accountLockingLazyDataModel) {
    this.accountLockingLazyDataModel = accountLockingLazyDataModel;
  }

  public Trans getSelectedTrans() {
    return selectedTrans;
  }

  public void setSelectedTrans(Trans selectedTrans) {
    this.selectedTrans = selectedTrans;
  }

  public AgentAccount getAgentAccount() {
    return agentAccount;
  }

  public void setAgentAccount(AgentAccount agentAccount) {
    this.agentAccount = agentAccount;
  }

  public String getAgentAccountStatus() {
    return agentAccountStatus;
  }

  public void setAgentAccountStatus(String agentAccountStatus) {
    this.agentAccountStatus = agentAccountStatus;
  }

  public boolean isLockBtnDisable() {
    return isLockBtnDisable;
  }

  public void setLockBtnDisable(boolean lockBtnDisable) {
    isLockBtnDisable = lockBtnDisable;
  }

  public boolean isUnlockBtnDisable() {
    return isUnlockBtnDisable;
  }

  public void setUnlockBtnDisable(boolean unlockBtnDisable) {
    isUnlockBtnDisable = unlockBtnDisable;
  }

  public boolean isRecoverBtnDisable() {
    return isRecoverBtnDisable;
  }

  public void setRecoverBtnDisable(boolean recoverBtnDisable) {
    isRecoverBtnDisable = recoverBtnDisable;
  }
}
