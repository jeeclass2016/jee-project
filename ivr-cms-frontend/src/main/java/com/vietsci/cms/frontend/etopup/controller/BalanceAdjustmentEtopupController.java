package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentDTO;
import com.vietsci.cms.frontend.etopup.dto.ReasonDTO;
import com.vietsci.cms.frontend.etopup.model.BalanceAdjustmentType;
import com.vietsci.cms.frontend.etopup.model.Reason;
import com.vietsci.cms.frontend.etopup.service.AgentService;
import com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService;
import com.vietsci.cms.frontend.etopup.service.EtopupReasonService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

/**
 * Controller class for BalanceAdjustment
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
public class BalanceAdjustmentEtopupController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8022371707825859809L;

  /* logger */
  private static final Log logger = LogFactory.getLog(BalanceAdjustmentEtopupController.class);

  @Inject
  BalanceManagementEtopupService balanceManagementService;

  @Inject
  EtopupReasonService reasonService;

  @Inject
  AgentService agentService;

  private BalanceAdjustmentDTO balanceAdjustmentDTO;

  private List<SelectItem> adjustmentTypeSelectItems;
  private List<SelectItem> reasonSelectItems;

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init BalanceAdjustmentEtopupController members");

    balanceAdjustmentDTO = new BalanceAdjustmentDTO();

    initAdjustmentTypeSelectItems();
    //initReasonSelectItems();

    logger.debug("Init BalanceAdjustmentEtopupController members successfully");
  }

  /**
   * Action is invoked when users click on "Thực hiện" button
   */
  public void adjustBalance() {
    logger.debug("Start adjustBalance");

    balanceAdjustmentDTO.setStaffId(getCurrentUserId());
    balanceAdjustmentDTO.setUserName(getCurrentUserName());

    String message = "";
    try {
      boolean isSuccess = balanceManagementService.adjustBalance(balanceAdjustmentDTO);
      if(isSuccess) {
        message = getMessage(MessageConstants.ETOPUP_BALANCE_MANAGEMENT_ADJUST);
        FacesUtil.addSuccessMessage(message);

        reset();

      } else {
        message = getErrorMessage(ErrorConstants.ETOPUP_BALANCE_MANAGEMENT_ADJUST);
        FacesUtil.addErrorMsg(null, message, "");
      }
    } catch(Exception e) {
      handleExceptionMessage(e, BalanceAdjustmentEtopupController.class);
    }

    logger.debug("Finish adjustBalance");
  }

  /*
    Initialize the types of adjustment
   */
  private void initAdjustmentTypeSelectItems() {
    this.adjustmentTypeSelectItems = new ArrayList<>();

    this.adjustmentTypeSelectItems.add(new SelectItem(BalanceAdjustmentType.POSITIVE.getValue(),
        BalanceAdjustmentType.POSITIVE.getName()));
    this.adjustmentTypeSelectItems.add(new SelectItem(BalanceAdjustmentType.NEGATIVE.getValue(),
        BalanceAdjustmentType.NEGATIVE.getName()));
  }

  /*
    Initialize the list of reasons, get from database
   */
  public void initReasonSelectItems() {
    this.reasonSelectItems = new ArrayList<>();

    ReasonDTO reasonDTO = new ReasonDTO();
    reasonDTO.setStatusNumberValue(Constants.ReasonManagement.ACTIVE_STATUS_VALUE); // get active reasons
    reasonDTO.setSortField("reasonDescribe");
    reasonDTO.setSortOrder(Constants.ASCENDING_SORT);

    List<Reason> reasonList = reasonService.findReasons(reasonDTO);
    for(Reason reason : reasonList) {
      this.reasonSelectItems.add(new SelectItem(reason.getCode(), reason.getReasonDescribe()));
    }
  }

  /*
   * Reset items when adjustment is success
   */
  private void reset() {
    balanceAdjustmentDTO.setReceivedAccount(StringUtils.EMPTY);
    balanceAdjustmentDTO.setAmountOfMoney(BigDecimal.ZERO);
    balanceAdjustmentDTO.setAdjustmentType(1);
    balanceAdjustmentDTO.setReferenceNumber(StringUtils.EMPTY);
    balanceAdjustmentDTO.setNote(StringUtils.EMPTY);
  }

  /* ============= Getter and Setter methods ============= */

  public BalanceAdjustmentDTO getBalanceAdjustmentDTO() {
    return balanceAdjustmentDTO;
  }

  public void setBalanceAdjustmentDTO(BalanceAdjustmentDTO balanceAdjustmentDTO) {
    this.balanceAdjustmentDTO = balanceAdjustmentDTO;
  }

  public List<SelectItem> getAdjustmentTypeSelectItems() {
    return adjustmentTypeSelectItems;
  }

  public void setAdjustmentTypeSelectItems(List<SelectItem> adjustmentTypeSelectItems) {
    this.adjustmentTypeSelectItems = adjustmentTypeSelectItems;
  }

  public List<SelectItem> getReasonSelectItems() {
    return reasonSelectItems;
  }

  public void setReasonSelectItems(List<SelectItem> reasonSelectItems) {
    this.reasonSelectItems = reasonSelectItems;
  }
}
