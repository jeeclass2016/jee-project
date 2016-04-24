package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.ReasonDTO;
import com.vietsci.cms.frontend.etopup.model.Reason;
import com.vietsci.cms.frontend.etopup.primefaces.ReasonDataModel;
import com.vietsci.cms.frontend.etopup.primefaces.ReasonLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.EtopupReasonService;
import com.vietsci.cms.frontend.exception.CmsRestException;

/** 
 * Controller class for Reason List management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class ReasonListController extends EtopupBaseController implements Serializable {
  
  /**
  *  Serial Version UID. 
  */
  private static final long serialVersionUID = -528220314119071660L;
  
  /**
  * logger.
  */
  private static final Log logger = LogFactory.getLog(ReasonListController.class);
 
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init ReasonListController members");
    
    reasonStatusMap = new HashMap<String, String>();
    reasonStatusMap.put(Constants.ReasonManagement.ACTIVE_STATUS_VALUE, Constants.ReasonManagement.ACTIVE_STATUS_LABEL);
    reasonStatusMap.put(Constants.ReasonManagement.INACTIVE_STATUS_VALUE, Constants.ReasonManagement.INACTIVE_STATUS_LABEL);
    reasonDTO = new ReasonDTO();
    reasonDTO.setStatus(Boolean.TRUE);
    reasonDTO.setStatusNumberValue(Constants.ReasonManagement.ACTIVE_STATUS_VALUE);
    
    // by default: search all active reason
    /*List<Reason> reasonList = reasonService.findReasons(reasonDTO);
    reasonsDataModel = new ReasonDataModel(reasonList);*/
    reasonsDataModel = new ReasonDataModel(new ArrayList<Reason>());
    
    reasonsLazyDataModel = new ReasonLazyDataModel(etopupReasonService, reasonDTO);
    
    reason = new Reason();
    selectedReason = new Reason();
    logger.debug("Init ReasonListController members successfully");
  }
  
  /**
   * Tim kiem thong tin ly do.
   */
  public void findReasons() {
    /*List<Reason> reasonList = reasonService.findReasons(reasonDTO);
    reasonsDataModel = new ReasonDataModel(reasonList);*/
    reasonsDataModel = new ReasonDataModel(new ArrayList<Reason>());
    
    reasonsLazyDataModel = new ReasonLazyDataModel(etopupReasonService, reasonDTO);
    
    selectedReason = new Reason();
  }
  
  /**
   * Xoa thong tin ly do.
   * @param reason
   * @return
   */
  public String deleteReason(Reason reason) {
    FacesContext context = FacesContext.getCurrentInstance();
    RequestContext requestContext = RequestContext.getCurrentInstance();
    if (reason == null) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, Constants.ReasonManagement.CANNOT_DELETE_NULL_REASON_MESSAGE));
      requestContext.addCallbackParam("canDelete", false);
      return null;
    }
    if (reason.getStatus() != null && reason.getStatus() == true) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, Constants.ReasonManagement.CANNOT_DELETE_ACTIVE_REASON_MESSAGE));
      requestContext.addCallbackParam("canDelete", false);
      return null;
    }
    requestContext.addCallbackParam("canDelete", true);
    Long reasonId = reason.getReasonId();
    try {
      etopupReasonService.deleteReason(reasonId);
    } catch (CmsRestException e) {
      /*logger.error("Delete Reason with id(" + reasonId + ") failed because of Exception: " + e.getMessage());
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.DELETE_REASON_FAILED_MESSAGE, 
    		  e.getMessage()));*/
      handleExceptionMessage(context, null, e);
      return null;
    }
    context.addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.ReasonManagement.DELETE_REASON_SUCCESS_MESSAGE, null));
    findReasons();
    return null;
  }
  
  /**
  * Handle su kien nguoi dung click vao button them moi ly do.
  * @return  null
  */
  public String onNewReason() {
    clearMessages();
    reason = new Reason();
    return null;
  }
  
  /**
   * Handle su kien nguoi dung click vao button chinh sua ly do.
   * @param   reason    doi tuong ly do ma nguoi dung chon tu 'danh sach ly do' datatable
   * @return  null
   */
  public String onEditReason(Reason reason) {
    clearMessages();
    selectedReason = reason;
    if (reason.getStatus() != null && reason.getStatus()) {
      selectedReason.setStatusNumberValue(Constants.ReasonManagement.ACTIVE_STATUS_VALUE);
    } else if (reason.getStatus() != null && !reason.getStatus()) {
      selectedReason.setStatusNumberValue(Constants.ReasonManagement.INACTIVE_STATUS_VALUE);
    }
    return null;
  }
  
  /**
   * Handle su kien nguoi dung click vao button "Luu ly do" trong dialog "Them moi ly do".
   * @return  null
   */
  public String doCreateReason() {
    FacesContext context = FacesContext.getCurrentInstance();
    RequestContext requestContext = RequestContext.getCurrentInstance();
    if ( StringUtils.isBlank(reason.getReasonDescribe()) ) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, 
                            Constants.ReasonManagement.CANNOT_CREATE_REASON_WITH_BLANK_DESC_MESSAGE));
      return null;
    }
    String reasonDesc = reason.getReasonDescribe();
    for (String chr : Constants.SPECIAL_CHARACTERS) {
      if (reasonDesc.contains(chr)) {
        context.addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, 
                              Constants.ReasonManagement.CANNOT_CREATE_REASON_WITH_INVALID_DESC_MESSAGE));
        return null;
      }
    }
    reason.setReasonDescribe(reasonDesc.trim());
    String code = reason.getCode();
    code = code.replace(" ", "");
    reason.setCode(code);
    if (Constants.ReasonManagement.ACTIVE_STATUS_VALUE.equals(reason.getStatusNumberValue())) {
      reason.setStatus(Boolean.TRUE);
    } else {
      reason.setStatus(Boolean.FALSE);
    }
    boolean rs = false;
    try {
      rs = etopupReasonService.createReason(reason);
    } catch (CmsRestException e) {
      logger.error("Create reason with code(" + reason.getCode() + "," + reason.getReasonDescribe()
          + ") failed because of Exception: " + e.getMessage());
      /*context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.CREATE_REASON_FAILED_MESSAGE,
    		  e.getMessage()));
      requestContext.addCallbackParam("updateStatus", false);*/
      handleExceptionMessage(context, requestContext, e);
      return null;
    }
    if (rs) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.ReasonManagement.CREATE_REASON_SUCCESS_MESSAGE, 
        		  "Mã lý do: " + reason.getCode()));
      findReasons();
      requestContext.addCallbackParam("updateStatus", true);
      reason = new Reason();
    } else {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.CREATE_REASON_FAILED_MESSAGE, 
        		  "Mã lý do: " + reason.getCode()));
      requestContext.addCallbackParam("updateStatus", false);
    }
    return null;
  }
  
  /**
   * Handle su kien nguoi dung click vao button "Luu ly do" trong dialog "Chinh sua ly do".
   * @return
   */
  public String doUpdateReason() {
    FacesContext context = FacesContext.getCurrentInstance();
    RequestContext requestContext = RequestContext.getCurrentInstance();
    if ( StringUtils.isBlank(selectedReason.getReasonDescribe()) ) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, 
                            Constants.ReasonManagement.CANNOT_CREATE_REASON_WITH_BLANK_DESC_MESSAGE));
      return null;
    }
    String reasonDesc = selectedReason.getReasonDescribe();
    for (String chr : Constants.SPECIAL_CHARACTERS) {
      if (reasonDesc.contains(chr)) {
        context.addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, 
                              Constants.ReasonManagement.CANNOT_CREATE_REASON_WITH_INVALID_DESC_MESSAGE));
        return null;
      }
    }
    selectedReason.setReasonDescribe(reasonDesc.trim());
    String code = selectedReason.getCode();
    code = code.replace(" ", "");
    selectedReason.setCode(code);
    if (Constants.ReasonManagement.ACTIVE_STATUS_VALUE.equals(selectedReason.getStatusNumberValue())) {
      selectedReason.setStatus(Boolean.TRUE);
    } else {
      selectedReason.setStatus(Boolean.FALSE);
    }
    
    boolean rs = false;
    try {
      rs = etopupReasonService.updateReason(selectedReason);
    } catch (CmsRestException e) {
      logger.error("Update reason with id/code/desc: (" + selectedReason.getReasonId() + "," + reason.getCode()
          + "," + selectedReason.getReasonDescribe() + ") failed because of Exception: " + e.getMessage());
      /*context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.EDIT_REASON_FAILED_MESSAGE,
    		  e.getMessage()));
      requestContext.addCallbackParam("updateStatus", false);*/
      handleExceptionMessage(context, requestContext, e);
      return null;
    }
    if (rs) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.ReasonManagement.EDIT_REASON_SUCCESS_MESSAGE, 
        		  "Mã lý do: " + selectedReason.getCode()));
      requestContext.addCallbackParam("updateStatus", true);
      findReasons();
    } else {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.EDIT_REASON_FAILED_MESSAGE, 
        		  "Mã lý do: " + selectedReason.getCode()));
      requestContext.addCallbackParam("updateStatus", false);
    }
    return null;
  }
  
  private void clearMessages() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Iterator<FacesMessage> messagesIterator = facesContext.getMessages();
    while (messagesIterator.hasNext()) {
      messagesIterator.remove();
    }
  }
  
  private void handleExceptionMessage(FacesContext context, RequestContext requestContext, CmsRestException e) {
    String bodyMessage = e.getMessage();
    Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
    if (bodyMessageMap == null || bodyMessageMap.size() <=0 ) {
      context.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, null));
      if (requestContext != null) {
        requestContext.addCallbackParam("updateStatus", false);
      }
      return;
    }
    String messageCode = bodyMessageMap.get(Constants.MESSAGE_CODE);
    String message = bodyMessageMap.get(Constants.MESSAGE);
    logger.error("messageCode:" + messageCode);
    logger.error("message:" + message);
    context.addMessage(null, 
        new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.ReasonManagement.OPERATION_FAILED_MESSAGE, message));
    if (requestContext != null) {
      requestContext.addCallbackParam("updateStatus", false);
    }
  }

  /* **** GETTERS & SETTERS **** */
  @Inject
  private EtopupReasonService etopupReasonService;
  
  private ReasonDTO reasonDTO;
  
  private Reason selectedReason;
  
  private Reason reason;
  
  private ReasonDataModel reasonsDataModel;
  private ReasonLazyDataModel reasonsLazyDataModel;
  
  private boolean displayPopup = true;
  private Map<String, String> reasonStatusMap;

  /**
   * @return the reasonDTO
   */
  public ReasonDTO getReasonDTO() {
    return reasonDTO;
  }

  /**
   * @param reasonDTO the reasonDTO to set
   */
  public void setReasonDTO(ReasonDTO reasonDTO) {
    this.reasonDTO = reasonDTO;
  }

  /**
   * @return the selectedReason
   */
  public Reason getSelectedReason() {
    return selectedReason;
  }

  /**
   * @param selectedReason the selectedReason to set
   */
  public void setSelectedReason(Reason selectedReason) {
    this.selectedReason = selectedReason;
  }

  /**
   * @return the reason
   */
  public Reason getReason() {
    return reason;
  }

  /**
   * @param reason the reason to set
   */
  public void setReason(Reason reason) {
    this.reason = reason;
  }

  /**
   * @return the reasonsDataModel
   */
  public ReasonDataModel getReasonsDataModel() {
    return reasonsDataModel;
  }

  /**
   * @param reasonsDataModel the reasonsDataModel to set
   */
  public void setReasonsDataModel(ReasonDataModel reasonsDataModel) {
    this.reasonsDataModel = reasonsDataModel;
  }

  /**
   * @return the displayPopup
   */
  public Boolean getDisplayPopup() {
    return displayPopup;
  }

  /**
   * @param displayPopup the displayPopup to set
   */
  public void setDisplayPopup(Boolean displayPopup) {
    this.displayPopup = displayPopup;
  }

  /**
   * @return the reasonStatusList
   */
  public Map<String, String> getReasonStatusMap() {
    return reasonStatusMap;
  }

  /**
   * @param reasonStatusList the reasonStatusList to set
   */
  public void setReasonStatusMap(Map<String, String> reasonStatusMap) {
    this.reasonStatusMap = reasonStatusMap;
  }

  public ReasonLazyDataModel getReasonsLazyDataModel() {
    return reasonsLazyDataModel;
  }

  public void setReasonsLazyDataModel(ReasonLazyDataModel reasonsLazyDataModel) {
    this.reasonsLazyDataModel = reasonsLazyDataModel;
  }

}
