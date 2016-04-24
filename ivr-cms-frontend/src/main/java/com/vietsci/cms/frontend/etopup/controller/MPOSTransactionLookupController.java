package com.vietsci.cms.frontend.etopup.controller;


import com.vietsci.cms.frontend.etopup.common.util.EtopupStringUtil;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.McSubscriber;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.model.Trans.TransType;
import com.vietsci.cms.frontend.etopup.primefaces.MposLookupTransLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.EtopupMcSubscriberService;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class MPOSTransactionLookupController extends EtopupBaseController implements Serializable {

  private static final long serialVersionUID = -2655031699377781135L;

  @Inject
  private TransactionManagementService transactionManagementService;

  @Inject
  private EtopupMcSubscriberService etopupMcSubscriberService;

  private TransactionManagementDTO transDTO = new TransactionManagementDTO();
  private MposLookupTransLazyDataModel mposTransLazyDataModel;
  private Trans selectedTrans = new Trans();
  private List<SelectItem> transTypeSelectItems;
  private String stkNumber;
  private String selectedCosCode;

  public TransactionManagementDTO getTransDTO() {
    return transDTO;
  }

  public void setTransDTO(TransactionManagementDTO transDTO) {
    this.transDTO = transDTO;
  }

  public MposLookupTransLazyDataModel getMposTransLazyDataModel() {
    return mposTransLazyDataModel;
  }

  public void setMposTransLazyDataModel(MposLookupTransLazyDataModel mposTransLazyDataModel) {
    this.mposTransLazyDataModel = mposTransLazyDataModel;
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

  public String getStkNumber() {
    return stkNumber;
  }

  public void setStkNumber(String stkNumber) {
    this.stkNumber = stkNumber;
  }

  public String getSelectedCosCode() {
    return selectedCosCode;
  }

  public void setSelectedCosCode(String selectedCosCode) {
    this.selectedCosCode = selectedCosCode;
  }

  @PostConstruct
  public void init() {
    initTransTypeSelectItems();
  }

  private void initTransTypeSelectItems() {
    this.transTypeSelectItems = new ArrayList<>();
    transTypeSelectItems.add(new SelectItem(TransType.ADM.getValue(), TransType.ADM.getName()));
    transTypeSelectItems.add(new SelectItem(TransType.COMM.getValue(), TransType.COMM.getName()));
    transTypeSelectItems.add(new SelectItem(TransType.RSMPIN.getValue(), TransType.RSMPIN.getName()));
    transTypeSelectItems.add(new SelectItem(TransType.TRANL.getValue(), TransType.TRANL.getName()));
  }

  public void doFindTrans() {
    standardizeStkNumber();
    mposTransLazyDataModel = new MposLookupTransLazyDataModel(transactionManagementService, transDTO);
    selectedTrans = new Trans();
  }

  private void standardizeStkNumber() {
    if (EtopupStringUtil.isNullOrBlank(stkNumber)) {
      transDTO.setSourceAccountId(null);
    } else {
      transDTO.setSourceAccountId(Long.valueOf(stkNumber));
    }
  }

  public void onSelectRowListener() {
    try {
      McSubscriber selectedMcSubscriber = etopupMcSubscriberService.findActiveMcSubscriberByMDN(selectedTrans.getSourceMsisdn());
      selectedCosCode = selectedMcSubscriber != null ? selectedMcSubscriber.getCosCode() : "";
    } catch (Exception e) {
      handleExceptionMessage(e, MPOSTransactionLookupController.class);
      selectedCosCode = "";
    }
  }
}
