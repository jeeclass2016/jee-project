package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.WebApplicationContext;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.TransactionTypeDTO;
import com.vietsci.cms.frontend.etopup.model.TransactionType;
import com.vietsci.cms.frontend.etopup.primefaces.TransactionTypeDataModel;
import com.vietsci.cms.frontend.etopup.primefaces.TransactionTypeLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.TransactionTypeService;
import com.vietsci.cms.frontend.exception.CmsRestException;

@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class TransactionTypeController extends EtopupBaseController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	* logger.
	*/
	private static final Log logger = LogFactory.getLog(TransactionTypeController.class);
	
	/**-- Property --**/
	private long id;	
  // trạng thái GD
  private String status = Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_ACTIVE;
  // mã GD
  private String code;
  // mô tả
  private String description;
  //trạng thái GD mới
  private String newStatus;
  // mã GD mới
  private String newCode;
  // mô tả mới
  private String newDescription;
  //id 
  private long deleteId;
  //tổng số trang hiện tại
  private int totalPage;
  //trang hiện tại  
  private int currentPage;
  //danh sách GD
  private List<TransactionType> transactionTypeList;
  private TransactionTypeDataModel transactionTypesDataModel;
  private TransactionTypeLazyDataModel transactionTypesLazyDataModel;
  private TransactionTypeDTO transactionTypeDTO;
  @Inject
  private TransactionTypeService transactionTypeService;
  /**-- End Property --**/

  @PostConstruct
  public void init() {
    logger.debug("Prepare to init TransactionTypeController members");
    onFindTransType();
    logger.debug("Init TransactionTypeController successfully.");
  }
  
  /**
   * Get tên trạng thái trong danh sách GD
   * 
   * @author 
   * @param statusStock
   * @return
   */
  public List<SelectItem> getListStatus() {
    List<SelectItem> result = new ArrayList<SelectItem>();
    //result.add(new SelectItem("", "-- Chọn một trạng thái --"));
    result.add(new SelectItem(String.valueOf(Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_ACTIVE), "Hiệu lực"));
    result.add(new SelectItem(String.valueOf(Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_INACTIVE), "Không hiệu lực"));
    return result;
  }
   
  /**
   *Sự kiên phân trang 
   */
  public void paginate(PageEvent event){
    currentPage = (int)event.getPage();
  }
  
  /**
   * Tìm kiếm phiếu xuất
   * 
   * @author 
   */
  public void onFindTransType() {
    RequestContext requestContext = RequestContext.getCurrentInstance();
    FacesContext faceContext = FacesContext.getCurrentInstance();
    // khởi tạo lại danh sách GD
    TransactionTypeDTO searchCriteriaDto = new TransactionTypeDTO();
    searchCriteriaDto.setCode(code);
    searchCriteriaDto.setDescription(description);
    searchCriteriaDto.setStatus(status);
    //searchCriteriaDto.setPageIndex(currentPage+1);
    try {
      //transactionTypeList = transactionTypeService.getTransactionTypeList(searchCriteriaDto);
      transactionTypesDataModel = new TransactionTypeDataModel(new ArrayList<TransactionType>());
      transactionTypesLazyDataModel = new TransactionTypeLazyDataModel(transactionTypeService, searchCriteriaDto);
    } catch (ResourceAccessException e){
  	  requestContext.addCallbackParam("updateStatus", false);
  	  faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi kết nối với server", e.getMessage()));
    }
        //totalPage = transactionTypeList.size() / searchCriteriaDto.getPageIndex();
  }
  
  public void deleteTransType() {
    FacesContext faceContext = FacesContext.getCurrentInstance();
    
    TransactionType transTypeObjToDelete = transactionTypeService.getTransactionType(deleteId);
    if (transTypeObjToDelete != null 
        && Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_ACTIVE.equals(transTypeObjToDelete.getStatus())) {
      faceContext.addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.TransactionTypeManagement.DELETE_TRANSACTION_TYPE_FAILED_MESSAGE, Constants.TransactionTypeManagement.CANNOT_DELETE_ACTIVE_TRANSACTION_TYPE_FAILED_MESSAGE));
      return;
    }
    
    transactionTypeService.deleteTransactionType(deleteId);
    faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.TransactionTypeManagement.DELETE_TRANSACTION_TYPE_SUCCESS_MESSAGE, null));
    
    onFindTransType();
  }
  
  public void saveUpdateTransType() {
    RequestContext requestContext = RequestContext.getCurrentInstance();
    FacesContext faceContext = FacesContext.getCurrentInstance();

  	try{
  	  newCode = newCode.replace(" ", "");
  	  TransactionType transType = new TransactionType(id, newCode, newDescription.trim(), newStatus);
  	  transactionTypeService.saveUpdateTransactionType(transType);
  	  requestContext.addCallbackParam("updateStatus", true);
  	  onFindTransType();
  	} catch (ResourceAccessException e){
  	  requestContext.addCallbackParam("updateStatus", false);
  	  faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi kết nối với server", e.getMessage()));
  	  return;
  	} catch (CmsRestException e){
  	  /*requestContext.addCallbackParam("updateStatus", false);
  	  faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));*/
  	  handleExceptionMessage(faceContext, requestContext, e);
  	  return;
    } 
  	if (id == 0){
  		faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.TransactionTypeManagement.CREATE_TRANSACTION_TYPE_SUCCESS_MESSAGE, null));
  		openAddTransType();
  	} else{
  		faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.TransactionTypeManagement.EDIT_TRANSACTION_TYPE_SUCCESS_MESSAGE, null));
  	}
  }
  
  public void editTransType(long id) {
	  TransactionType transType = transactionTypeService.getTransactionType(id);
	  this.id = transType.getId();
	  newCode = transType.getCode();
	  newDescription = transType.getDescription();
	  newStatus = transType.getStatus();
	  clearMessages();
  }
	   
  public void openAddTransType() {
	  id = 0;
	  newCode = null;
	  newDescription = null;
	  newStatus = Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_ACTIVE;
	  clearMessages();
  }
  
  public void openDeleteConfirmDialog(long id) {
	  this.setDeleteId(id);
  }
  
  private void clearMessages() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Iterator<FacesMessage> messagesIterator = facesContext.getMessages();
    while (messagesIterator.hasNext()) {
      messagesIterator.remove();
    }
  }
  /**
   * Get tên trạng thái trong danh sách GD
   * 
   * @author 
   * @param status
   * @return
   */
  public String getStatusLabel(String status) {
    String statusLabel = null;
    String active = String.valueOf(Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_ACTIVE);
    String inactive = String.valueOf(Constants.TransactionTypeManagement.TRANSACTION_TYPE_STATUS_INACTIVE);

    if (status.equals(active)) {
      statusLabel = "Hiệu lực";
    } else if (status.equals(inactive)) {
      statusLabel = "Không hiệu lực";
    } else {
      statusLabel = "Không xác định";
    }

    return statusLabel;
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
  
  /**--End event--**/
  
  /**-- Get/ Set --**/
  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }
  /**
   * 
   * @return 
   */
  public String getStatus() {
    return status;
  }

  /**
   * 
   * @param status
   */
  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * Mã GD
   * @return String 
   */
  public String getCode() {
    return code;
  }

  /**
   * mã GD
   * @param code 
   */
  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getTotalPage() {
    totalPage = Constants.RECORDS_PER_PAGE;
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public List<TransactionType> getTransactionTypeList() {
    return transactionTypeList;
  }

  public String getNewStatus() {
    return newStatus;
  }

  public void setNewStatus(String newStatus) {
    this.newStatus = newStatus;
  }

  public String getNewCode() {
    return newCode;
  }

  public void setNewCode(String newCode) {
    this.newCode = newCode;
  }

  public String getNewDescription() {
    return newDescription;
  }

  public void setNewDescription(String newDescription) {
    this.newDescription = newDescription;
  }
  /**-- End Get/ Set --**/

  public long getDeleteId() {
    return deleteId;
  }

  public void setDeleteId(long deleteId) {
    this.deleteId = deleteId;
  }

  public TransactionTypeDataModel getTransactionTypesDataModel() {
    return transactionTypesDataModel;
  }

  public void setTransactionTypesDataModel(TransactionTypeDataModel transactionTypesDataModel) {
    this.transactionTypesDataModel = transactionTypesDataModel;
  }

  public TransactionTypeLazyDataModel getTransactionTypesLazyDataModel() {
    return transactionTypesLazyDataModel;
  }

  public void setTransactionTypesLazyDataModel(TransactionTypeLazyDataModel transactionTypesLazyDataModel) {
    this.transactionTypesLazyDataModel = transactionTypesLazyDataModel;
  }
}

