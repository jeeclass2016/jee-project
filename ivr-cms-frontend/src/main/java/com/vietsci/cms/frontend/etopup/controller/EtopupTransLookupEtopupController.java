package com.vietsci.cms.frontend.etopup.controller;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.AccountInfoSearchingDTO;
import com.vietsci.cms.frontend.etopup.dto.EtopupTransLookupDTO;
import com.vietsci.cms.frontend.etopup.dto.TransactionTypeDTO;
import com.vietsci.cms.frontend.etopup.model.AgentAccount;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.model.TransactionType;
import com.vietsci.cms.frontend.etopup.primefaces.EtopupTransLookupEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService;
import com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService;
import com.vietsci.cms.frontend.etopup.service.TransactionTypeService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for Etopup transaction look up (Tra cuu giao dich ETOPUP)
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class EtopupTransLookupEtopupController extends EtopupBaseController implements Serializable {

  private static final long serialVersionUID = 3487361739044871398L;

  /* logger */
  private static final Log logger = LogFactory.getLog(EtopupTransLookupEtopupController.class);

  @Inject
  private TransManagementEtopupService transService;

  @Inject
  private TransactionTypeService transTypeService;

  @Inject
  private BalanceManagementEtopupService balanceService;

  private EtopupTransLookupDTO etopupTransLookupDTO;
  private EtopupTransLookupEtopupLazyDataModel transLazyDataModel;
  private Trans selectedTrans;

  private List<SelectItem> transTypeSelectItems;

  private AgentAccount sourceAccount;
  private AgentAccount targetAccount;

  /* ============= Business methods ============= */

  /**
   * Initialize instance variables
   *
   * @author hong.nguyenmai
   */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init EtopupTransLookupEtopupController members");

    etopupTransLookupDTO = new EtopupTransLookupDTO();
    //transLazyDataModel = new EtopupTransLookupEtopupLazyDataModel(transService, etopupTransLookupDTO);

    transTypeSelectItems = new ArrayList<>();

    logger.debug("Init EtopupTransLookupEtopupController members successfully");
  }

  /**
   * Initialize "Loại giao dịch" selection box
   */
  public void initBeforeLoadingPage() {
    logger. debug("Start init before loading page");

    TransactionTypeDTO transTypeDTO = new TransactionTypeDTO();

    transTypeDTO.setSortField("description");
    transTypeDTO.setStatus(Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_ACTIVE);
    transTypeDTO.setSortOrder(Constants.ASCENDING_SORT);

    List<TransactionType> transTypeList = transTypeService.getTransactionTypeList(transTypeDTO);

    transTypeSelectItems.clear();
    for(TransactionType transType : transTypeList) {
      transTypeSelectItems.add(new SelectItem(transType.getCode(), transType.getDescription()));
    }

    logger. debug("Finish init before loading page");
  }

  /**
   * Action is invoked when users click on "Tìm" button
   */
  public void findTrans() {
    logger.debug("Start findTrans");
    transLazyDataModel = new EtopupTransLookupEtopupLazyDataModel(transService, etopupTransLookupDTO);
    logger.debug("Finish findTrans");
  }

  /**
   * Action is invoked when users click on a row
   */
  public void findTransDetail() {
    logger.debug("Start findTransDetail");

    AccountInfoSearchingDTO sourceAccountInfo = new AccountInfoSearchingDTO();
    sourceAccountInfo.setMsisdn(selectedTrans.getSourceMsisdn());

    AccountInfoSearchingDTO targetAccountInfo = new AccountInfoSearchingDTO();
    targetAccountInfo.setMsisdn(selectedTrans.getTargetMsisdn());

    try {

      sourceAccount = balanceService.findAccountInfo(sourceAccountInfo);
      targetAccount = balanceService.findAccountInfo(targetAccountInfo);

    } catch(Exception e) {
      return;
    }

    logger.debug("Finish findTransDetail");
  }

  /* ============= Getter and Setter methods ============= */

  public EtopupTransLookupDTO getEtopupTransLookupDTO() {
    return etopupTransLookupDTO;
  }

  public void setEtopupTransLookupDTO(EtopupTransLookupDTO etopupTransLookupDTO) {
    this.etopupTransLookupDTO = etopupTransLookupDTO;
  }

  public EtopupTransLookupEtopupLazyDataModel getTransLazyDataModel() {
    return transLazyDataModel;
  }

  public void setTransLazyDataModel(EtopupTransLookupEtopupLazyDataModel transLazyDataModel) {
    this.transLazyDataModel = transLazyDataModel;
  }

  public Trans getSelectedTrans() {
    return selectedTrans;
  }

  public void setSelectedTrans(Trans selectedTrans) {
    this.selectedTrans = selectedTrans;
  }

  public List<SelectItem> getTransTypeSelectItems() {
    return transTypeSelectItems;
  }

  public void setTransTypeSelectItems(List<SelectItem> transTypeSelectItems) {
    this.transTypeSelectItems = transTypeSelectItems;
  }

  public AgentAccount getSourceAccount() {
    return sourceAccount;
  }

  public void setSourceAccount(AgentAccount sourceAccount) {
    this.sourceAccount = sourceAccount;
  }

  public AgentAccount getTargetAccount() {
    return targetAccount;
  }

  public void setTargetAccount(AgentAccount targetAccount) {
    this.targetAccount = targetAccount;
  }
}
