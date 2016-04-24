package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.dto.TLDTransBatchDTO;
import com.vietsci.cms.frontend.etopup.dto.TLDTransDTO;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.dto.UserDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.Centre;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.model.TransExt;
import com.vietsci.cms.frontend.etopup.primefaces.TLDTransactionLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.AgentManagementService;
import com.vietsci.cms.frontend.etopup.service.CentreService;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;
import com.vietsci.cms.frontend.etopup.service.UserManagementEtopupService;
import com.vietsci.cms.frontend.util.FacesUtil;

/** 
 * Controller class for TLD Transfer.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value="session")
public class TldTransferController extends EtopupBaseController implements Serializable {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -1363703556201461069L;

  /**
  * logger.
  */
  private static final Log logger = LogFactory.getLog(TldTransferController.class);
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init TLDTransferController members");
    transactionManagementDTO = new TransactionManagementDTO();
    tldTransDTO = new TLDTransDTO();
    tldTransBatchDTO = new TLDTransBatchDTO();
    transProcessStatusMap = new HashMap<Integer, String>();
    transProcessStatusMap.put(Trans.ProcessStatus.TRANS_PENDING.getValue(), Trans.ProcessStatus.TRANS_PENDING.getName());
    transProcessStatusMap.put(Trans.ProcessStatus.TRANS_COMPLETE.getValue(), Trans.ProcessStatus.TRANS_COMPLETE.getName());
    transProcessStatusMap.put(Trans.ProcessStatus.TRANS_REJECT.getValue(), Trans.ProcessStatus.TRANS_REJECT.getName());
    
    processStatusSelectItems = new ArrayList<>();
    processStatusSelectItems.add(new SelectItem(Trans.ProcessStatus.TRANS_PENDING.getValue(), Trans.ProcessStatus.TRANS_PENDING.getName()));
    processStatusSelectItems.add(new SelectItem(Trans.ProcessStatus.TRANS_COMPLETE.getValue(), Trans.ProcessStatus.TRANS_COMPLETE.getName()));
    processStatusSelectItems.add(new SelectItem(Trans.ProcessStatus.TRANS_REJECT.getValue(), Trans.ProcessStatus.TRANS_REJECT.getName()));
    
    logger.debug("Init TLDTransferController members successfully");
  }
  
  public void findTLDTrans() {
    logger.debug("find TLD Transaction");
    tldTransactionLazyDataModel = new TLDTransactionLazyDataModel(transactionManagementService, transactionManagementDTO);
  }
  
  public void doFindAgent() {
    try {
      processingAgent = agentManagementService.getAgentByMSISDN(msisdn);
      if (processingAgent == null || processingAgent.getAgentId() == null) {
        FacesUtil.addErrorTitleMessage("Không tìm thấy đại lý", "");
        enableToCreateTLDTrans = false;
        return;
      }
      
      Integer status = processingAgent.getStatus();
      String agentStatus = processingAgent.getAgentStatus();
      String mpinStatus = processingAgent.getMpinStatus();
      
      if (status != null && status.intValue() == 4) {
        FacesUtil.addErrorMsg(null, "Đại lý đã bị hủy", "");
        enableToCreateTLDTrans = false;
        return;
      }
      boolean isActiveAgent = "5".equals(agentStatus) && "1".equals(mpinStatus) 
                                  && (status != null && status.intValue() == 1); 
      if ( !isActiveAgent ) {
        FacesUtil.addErrorMsg(null, "Đại lý chưa được active", "");
        enableToCreateTLDTrans = false;
        return;
      }
      
      enableToCreateTLDTrans = true;
    } catch (Exception e) {
      handleExceptionMessage(e, TldTransferController.class);
    }
  }  
  
  public String showTransProcessStatus(Trans trans) {
    if (trans == null) {
      return "";
    }
    Integer processStatus = trans.getProcessStatus();
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
  
  public String getCreator(Trans transaction) {
    if (transaction == null) {
      return "";
    }
    Set<TransExt> transExtSet = transaction.getTransExts();
    if (transExtSet == null) {
      return "";
    }
    Long userId = 0L;
    for (TransExt tl : transExtSet) {
      if ("Creator_ID".equals(tl.getKeyName())) {
        userId = new Long(tl.getKeyValue());
        break;
      }
    }
    UserDTO userDTO = userManagementEtopupService.findUserProfileByUserId(userId);
    if (userDTO != null) {
      return userDTO.getFullName();
    }
    return "";
  }
  
  public String getCommission(Trans transaction) {
    if (transaction == null) {
      return "";
    }
    Set<TransExt> transExtSet = transaction.getTransExts();
    if (transExtSet == null) {
      return "";
    }
    for (TransExt te : transExtSet) {
      if ("Commission".equals(te.getKeyName())) {
        return te.getKeyValue();
      }
    }
    return "";
  }
  
  public String getOrderNumber(Trans transaction) {
    if (transaction == null) {
      return "";
    }
    Set<TransExt> transExtSet = transaction.getTransExts();
    if (transExtSet == null) {
      return "";
    }
    for (TransExt te : transExtSet) {
      if ("LOP".equals(te.getKeyName())) {
        String keyValue = te.getKeyValue();
        String[] values = StringUtils.split(keyValue, "#");
        if (values.length != 0) {
          return values[0];
        }
        return "";
      }
    }
    return "";
  }
  
  public String getAgentName(Trans transaction) {
    if (transaction == null) {
      return "";
    }
    String targetMsisdn = transaction.getTargetMsisdn();
    if (StringUtils.isBlank(targetMsisdn)) {
      return "";
    }
    Agent agent = agentManagementService.getAgentByMSISDN(targetMsisdn);
    if (agent == null) {
      return "";
    }
    return agent.getTradeName();
  }
  
  public String getAgentTradeName(Trans transaction) {
    if (transaction == null) {
      return "";
    }
    Set<TransExt> transExtSet = transaction.getTransExts();
    if (transExtSet == null) {
      return "";
    }
    for (TransExt te : transExtSet) {
      if ("TargetAgent_TradeName".equals(te.getKeyName())) {
        return te.getKeyValue();
      }
    }
    return "";
  }
 
  public void initForOpeningPage() {
    logger.debug("Start initForOpeningPage method");
    initCentreSelectItems();
    logger.debug("End initForOpeningPage method");
  }
  
  private void initCentreSelectItems() {
    this.centreSelectItems = new ArrayList<>();
    try {
      List<Centre> centreObjs = centreService.findAllActiveCentre();
      for (Centre centreObj : centreObjs) {
        this.centreSelectItems.add(new SelectItem(centreObj.getCentreId(), centreObj.getName()));
      }
    } catch (Exception e) {
      handleExceptionMessage(e, TldTransferController.class);
    }
  }
  
  public void createTLDTrans() {
    logger.debug("START to create TLD Trans");
    if (processingAgent == null || processingAgent.getAgentId() == null) {
      FacesUtil.addWarningMessage("Không thể tạo giao dịch vì không có thông tin đại lý", "");
      return;
    }
    tldTransDTO.setCreatorId(getCurrentUserId());
    tldTransDTO.setTargetMsisdn(msisdn);
    tldTransDTO.setTargetAgentTradeName(processingAgent.getTradeName());
    try {
      Boolean rs = transactionManagementService.createTLDTrans(tldTransDTO);
      if (rs) {
        FacesUtil.addSuccessMessage("Tạo giao dịch thành công", "");
        findTLDTrans();
      } else {
        FacesUtil.addErrorTitleMessage("Tạo giao dịch thất bại", "");
      }
    } catch (Exception e) {
      handleExceptionMessage(e, TldTransferController.class);
    }
  }
  
  public void completeTLDTrans() {
    logger.debug("Start to complete TLD Trans");
    if (selectedTrans == null || selectedTrans.size() <= 0) {
      FacesUtil.addWarningMessage("Hãy chọn giao dịch để duyệt", "");
      return;
    }
    tldTransBatchDTO = new TLDTransBatchDTO();
    List<Long> transIdList = new ArrayList<Long>();
    for (Trans t : selectedTrans) {
      Integer processStatus = t.getProcessStatus();
      if (1 != processStatus) {
        FacesUtil.addWarningMessage("Hãy chọn giao dịch có trạng thái chưa duyệt để duyệt", "");
        return;
      }
      transIdList.add(t.getTransId());
    }
    tldTransBatchDTO.setTransIdList(transIdList);
    tldTransBatchDTO.setUserId(getCurrentUserId());
    try {
      Boolean rs = transactionManagementService.completeTLDTransBatch(tldTransBatchDTO);
      if (rs) {
        FacesUtil.addSuccessMessage("Duyệt giao dịch thành công", "");
        findTLDTrans();
      } else {
        FacesUtil.addErrorTitleMessage("Duyệt giao dịch thất bại", "");
      }
    } catch (Exception e) {
      handleExceptionMessage(e, TldTransferController.class);
    }
  }
  
  public void rejectTLDTrans() {
    logger.debug("Start to reject TLD Trans");
    if (selectedTrans == null || selectedTrans.size() <= 0) {
      FacesUtil.addWarningMessage("Hãy chọn giao dịch để từ chối", "");
      return;
    }
    tldTransBatchDTO = new TLDTransBatchDTO();
    List<Long> transIdList = new ArrayList<Long>();
    for (Trans t : selectedTrans) {
      Integer processStatus = t.getProcessStatus();
      if (1 != processStatus) {
        FacesUtil.addWarningMessage("Hãy chọn giao dịch có trạng thái chưa duyệt để từ chối", "");
        return;
      }
      transIdList.add(t.getTransId());
    }
    tldTransBatchDTO.setTransIdList(transIdList);
    tldTransBatchDTO.setUserId(getCurrentUserId());
    try {
      Boolean rs = transactionManagementService.rejectTLDTransBatch(tldTransBatchDTO);
      if (rs) {
        FacesUtil.addSuccessMessage("Từ chối giao dịch thành công", "");
        findTLDTrans();
      } else {
        FacesUtil.addErrorTitleMessage("Từ chối giao dịch thất bại", "");
      }
    } catch (Exception e) {
      handleExceptionMessage(e, TldTransferController.class);
    }
  }

  /* **** GETTERS & SETTERS **** */
  @Inject
  private TransactionManagementService transactionManagementService;
  
  @Inject
  private CentreService centreService;
  
  @Inject
  private AgentManagementService agentManagementService;
  
  @Inject
  private UserManagementEtopupService userManagementEtopupService;
  
  private TLDTransactionLazyDataModel tldTransactionLazyDataModel;
  
  private String msisdn;
  
  private Agent processingAgent;
  
  private boolean enableToCreateTLDTrans = false;
  
  private List<SelectItem> centreSelectItems;
  
  private List<SelectItem> processStatusSelectItems;
  
  private Map<Integer, String> transProcessStatusMap;
  
  private List<Trans> selectedTrans;
  
  private TLDTransDTO tldTransDTO;
  
  private TLDTransBatchDTO tldTransBatchDTO;
  
  private TransactionManagementDTO transactionManagementDTO;

  public TLDTransDTO getTldTransDTO() {
    return tldTransDTO;
  }

  public void setTldTransDTO(TLDTransDTO tldTransDTO) {
    this.tldTransDTO = tldTransDTO;
  }

  public TLDTransBatchDTO getTldTransBatchDTO() {
    return tldTransBatchDTO;
  }

  public void setTldTransBatchDTO(TLDTransBatchDTO tldTransBatchDTO) {
    this.tldTransBatchDTO = tldTransBatchDTO;
  }

  public TransactionManagementDTO getTransactionManagementDTO() {
    return transactionManagementDTO;
  }

  public void setTransactionManagementDTO(TransactionManagementDTO transactionManagementDTO) {
    this.transactionManagementDTO = transactionManagementDTO;
  }
  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public Agent getProcessingAgent() {
    return processingAgent;
  }

  public void setProcessingAgent(Agent processingAgent) {
    this.processingAgent = processingAgent;
  }

  public List<SelectItem> getCentreSelectItems() {
    return centreSelectItems;
  }

  public void setCentreSelectItems(List<SelectItem> centreSelectItems) {
    this.centreSelectItems = centreSelectItems;
  }

  public Map<Integer, String> getTransProcessStatusMap() {
    return transProcessStatusMap;
  }

  public void setTransProcessStatusMap(Map<Integer, String> transProcessStatusMap) {
    this.transProcessStatusMap = transProcessStatusMap;
  }

  public TLDTransactionLazyDataModel getTldTransactionLazyDataModel() {
    return tldTransactionLazyDataModel;
  }

  public void setTldTransactionLazyDataModel(TLDTransactionLazyDataModel tldTransactionLazyDataModel) {
    this.tldTransactionLazyDataModel = tldTransactionLazyDataModel;
  }

  public boolean isEnableToCreateTLDTrans() {
    return enableToCreateTLDTrans;
  }

  public void setEnableToCreateTLDTrans(boolean enableToCreateTLDTrans) {
    this.enableToCreateTLDTrans = enableToCreateTLDTrans;
  }

  public List<SelectItem> getProcessStatusSelectItems() {
    return processStatusSelectItems;
  }

  public void setProcessStatusSelectItems(List<SelectItem> processStatusSelectItems) {
    this.processStatusSelectItems = processStatusSelectItems;
  }

  public List<Trans> getSelectedTrans() {
    return selectedTrans;
  }

  public void setSelectedTrans(List<Trans> selectedTrans) {
    this.selectedTrans = selectedTrans;
  }
}
