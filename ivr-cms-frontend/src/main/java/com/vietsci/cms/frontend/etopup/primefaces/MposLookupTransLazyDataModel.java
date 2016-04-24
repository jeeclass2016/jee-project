/**
 * 
 */
package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.main.controller.LanguageController;

/**
 * @author NguyenPV
 *
 */
public class MposLookupTransLazyDataModel extends LazyDataModel<Trans> {

  
  /**
   * 
   */
  private static final long serialVersionUID = -2879874581899588943L;
  private List<Trans> dataSource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private TransactionManagementService transactionManagementService;
  private TransactionManagementDTO transactionManagementDTO;
  
  @Inject 
  @Named
  LanguageController languageController;
  
  final Logger logger = LoggerFactory.getLogger(getClass());

  public MposLookupTransLazyDataModel(TransactionManagementService transactionManagementService, TransactionManagementDTO transactionManagementDTO) {
    this.transactionManagementService = transactionManagementService;
    this.transactionManagementDTO = transactionManagementDTO;
  }

  @Override
  public List<Trans> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    try {
      int pageIndex = (first / pageSize) + 1;
      transactionManagementDTO.setPageIndex(pageIndex);
      transactionManagementDTO.setPageSize(pageSize);    
      transactionManagementDTO.setSortField(sortField);
      
      if(sortField != null && sortOrder.equals(SortOrder.ASCENDING)){
        transactionManagementDTO.setSortOrder(Constants.ASCENDING_SORT);
      } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)){
        transactionManagementDTO.setSortOrder(Constants.DESCENDING_SORT);
      }
      dataSource = transactionManagementService.findTransForMposLookup(transactionManagementDTO);
      setRowCount(transactionManagementService.countTransForMposLookup(transactionManagementDTO).intValue());
    } catch (CmsRestException e) {
      Map<String, String> eMessage = EtopupUtil.read(e.getMessage());
      logger.error("Loading MPOS Transaction data failed, because of " 
                  + getErrorMessage(eMessage.get(Constants.MESSAGE_CODE)) + ", errorCode=" + eMessage.get(Constants.MESSAGE_CODE));
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  getErrorMessage(eMessage.get(Constants.MESSAGE_CODE))); 
      FacesContext.getCurrentInstance().addMessage(null, message);  
     
    } catch (ResourceAccessException e){
      logger.error("Loading MPOS Transaction data failed, because of " + Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER);
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
          Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER); 
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    return dataSource;
  }
  
  /**
   * Lấy nội dung của error message dựa vào key
   * 
   * @param key trong file *.properties
   * @return nội dung lỗi
   */
  private String getErrorMessage(String key) {
    return getMessage(Constants.ERROR_MESSAGE, key);
  }
  
  /**
   * Lấy nội dung của message
   * 
   * @param messageType kiểu message ("Error", "Msg")
   * @param key trong file *.properties
   * @return nội dung message, trường hợp không tồn tại message sẽ trả lại key 
   */
  private String getMessage(String messageType, String key) {
    String result = "";
    if (Constants.ERROR_MESSAGE.equals(messageType)) {
      result = languageController.getErrorMap().get(key);
    } else if (Constants.MSG_MESSAGE.equals(messageType)) {
      result = languageController.getMsgMap().get(key);
    }
    
    if (StringUtils.isEmpty(result)) {
      result = key;
    }
    return result;
  }

  @Override
  public boolean isRowAvailable() {
    if (dataSource == null)
      return false;
    int index = rowIndex % pageSize;
    return index >= 0 && index < dataSource.size();
  }

  @Override
  public Object getRowKey(Trans trans) {
    return trans.getTransId().toString();
  }

  @Override
  public Trans getRowData() {
    if (dataSource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > dataSource.size()) {
      return null;
    }
    return dataSource.get(index);
  }

  @Override
  public Trans getRowData(String rowKey) {
    if (dataSource == null)
      return null;
    for (Trans reason : dataSource) {
      if (reason.getTransId().toString().equals(rowKey))
        return reason;
    }
    return null;
  }

  @Override
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public int getRowIndex() {
    return this.rowIndex;
  }

  @Override
  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }

  @Override
  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  @Override
  public int getRowCount() {
    return this.rowCount;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setWrappedData(Object list) {
    this.dataSource = (List<Trans>) list;
  }

  @Override
  public Object getWrappedData() {
    return dataSource;
  }

}
