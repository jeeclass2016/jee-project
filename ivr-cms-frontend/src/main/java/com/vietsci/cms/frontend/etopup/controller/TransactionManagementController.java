package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.model.TransLog;
import com.vietsci.cms.frontend.etopup.primefaces.TransactionDataModel;
import com.vietsci.cms.frontend.etopup.primefaces.TransactionLazyDataModel;
import com.vietsci.cms.frontend.etopup.primefaces.TransactionLogDataModel;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;

/** 
 * Controller class for Transaction management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class TransactionManagementController extends EtopupBaseController implements Serializable {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 4046842921378597430L;

  /**
  * logger.
  */
  private static final Log logger = LogFactory.getLog(TransactionManagementController.class);
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init TransactionManagementController members");
    transactionManagementDTO = new TransactionManagementDTO();
    logger.debug("Init TransactionManagementController members successfully");
  }
  
  public void findTransactionByCode() {
    logger.debug("Start finding transaction by code");
    FacesContext context = FacesContext.getCurrentInstance();
    TransactionManagementDTO managementDTO = new TransactionManagementDTO();
    managementDTO.setTransId(transId);
    List<Trans> transList = transactionManagementService.getTransactionList(managementDTO);
    transactionDataModel = new TransactionDataModel(transList);
    if (transList != null && transList.size() > 0) {
      selectedTransaction = transList.get(0);
    } else {
      selectedTransaction = new Trans();
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã giao dịch không tồn tại.", ""));
    }
    onSelectRowListener();
    logger.debug("Finished finding transaction by code");
  }
  
  public void findTransactionByMSISDNs() {
    logger.debug("Start finding transaction by MSISDNs");
    transactionLazyDataModel = new TransactionLazyDataModel(transactionManagementService, transactionManagementDTO);
    transactionLogDataModel2 = new TransactionLogDataModel(new ArrayList<TransLog>());
    logger.debug("Finished finding transaction by MSISDNs");
  }
 
  public void onSelectRowListener() {
    System.out.println("selected trans: " + selectedTransaction);
    Set<TransLog> transLogSet = selectedTransaction.getTransLogs();
    List<TransLog> transLogList = new ArrayList<TransLog>();
    if (transLogSet != null) {
      transLogList.addAll(transLogSet);
    }
    transactionLogDataModel = new TransactionLogDataModel(transLogList);
  }
  
  public void onSelectRowListener2() {
    System.out.println("selected trans: " + selectedTransaction2);
    if (selectedTransaction2 == null) {
      selectedTransaction2 = new Trans();
    }
    Set<TransLog> transLogSet = selectedTransaction2.getTransLogs();
    List<TransLog> transLogList = new ArrayList<TransLog>();
    if (transLogSet != null) {
      transLogList.addAll(transLogSet);
    }
    transactionLogDataModel2 = new TransactionLogDataModel(transLogList);
  }
  
  public String showTransProcessStatus() {
    if (selectedTransaction == null) {
      return "";
    }
    Integer processStatus = selectedTransaction.getProcessStatus();
    if (processStatus == null || processStatus == 0) {
      return "";
    } else if (processStatus == 1) {
      return Trans.ProcessStatus.TRANS_PENDING.getName();
    } else if (processStatus == 2) {
      return Trans.ProcessStatus.TRANS_INPROGRESS.getName();
    } else if (processStatus == 3) {
      return Trans.ProcessStatus.TRANS_COMPLETE.getName();
    } else if (processStatus == 9) {
      return Trans.ProcessStatus.TRANS_REJECT.getName();
    }
    return "";
  }
  
  public String showTransChannel() {
    if (selectedTransaction == null) {
      return "";
    }
    Long channel = selectedTransaction.getChannel();
    if (channel == null || channel == 0L) {
      return "";
    }
    String channelName = Constants.ChannelType.getNameByValue(channel.intValue());
    return channelName;
  }
  
  /* **** GETTERS & SETTERS **** */
  @Inject
  private TransactionManagementService transactionManagementService;
  
  private Long transId;
  
  private TransactionManagementDTO transactionManagementDTO;
  
  private Trans selectedTransaction;
  
  private Trans selectedTransaction2;

  private TransactionDataModel transactionDataModel;
  
  private TransactionLogDataModel transactionLogDataModel;
  
  private TransactionLazyDataModel transactionLazyDataModel;
  
  private TransactionLogDataModel transactionLogDataModel2;
  

  public TransactionManagementDTO getTransactionManagementDTO() {
    return transactionManagementDTO;
  }

  public void setTransactionManagementDTO(TransactionManagementDTO transactionManagementDTO) {
    this.transactionManagementDTO = transactionManagementDTO;
  }

  public TransactionLazyDataModel getTransactionLazyDataModel() {
    return transactionLazyDataModel;
  }

  public void setTransactionLazyDataModel(TransactionLazyDataModel transactionLazyDataModel) {
    this.transactionLazyDataModel = transactionLazyDataModel;
  }

  public Trans getSelectedTransaction() {
    return selectedTransaction;
  }

  public void setSelectedTransaction(Trans selectedTransaction) {
    this.selectedTransaction = selectedTransaction;
  }

  public Long getTransId() {
    return transId;
  }

  public void setTransId(Long transId) {
    this.transId = transId;
  }

  public TransactionDataModel getTransactionDataModel() {
    return transactionDataModel;
  }

  public void setTransactionDataModel(TransactionDataModel transactionDataModel) {
    this.transactionDataModel = transactionDataModel;
  }

  public TransactionLogDataModel getTransactionLogDataModel() {
    return transactionLogDataModel;
  }

  public void setTransactionLogDataModel(TransactionLogDataModel transactionLogDataModel) {
    this.transactionLogDataModel = transactionLogDataModel;
  }

  public Trans getSelectedTransaction2() {
    return selectedTransaction2;
  }

  public void setSelectedTransaction2(Trans selectedTransaction2) {
    this.selectedTransaction2 = selectedTransaction2;
  }

  public TransactionLogDataModel getTransactionLogDataModel2() {
    return transactionLogDataModel2;
  }

  public void setTransactionLogDataModel2(TransactionLogDataModel transactionLogDataModel2) {
    this.transactionLogDataModel2 = transactionLogDataModel2;
  }
  
}
