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
import com.vietsci.cms.frontend.etopup.dto.StockAgentMapDTO;
import com.vietsci.cms.frontend.etopup.model.StockAgentMap;
import com.vietsci.cms.frontend.etopup.service.EtopupStockAgentMapService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.main.controller.LanguageController;

public class StockAgentMapLazyDataModel extends LazyDataModel<StockAgentMap>{

  /**
   * 
   */
  private static final long serialVersionUID = 5339007210291321560L;
  
  private List<StockAgentMap> dataSource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private EtopupStockAgentMapService stockAgentMapService;
  private StockAgentMapDTO stockAgentMapDTO;
  
  @Inject 
  @Named
  LanguageController languageController;
  
  final Logger logger = LoggerFactory.getLogger(getClass());
  
  
  public StockAgentMapLazyDataModel(EtopupStockAgentMapService stockAgentMapService, StockAgentMapDTO stockAgentMapDTO){
    this.stockAgentMapService = stockAgentMapService;
    this.stockAgentMapDTO = stockAgentMapDTO;
  }
  
  @Override
  public List<StockAgentMap> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
    try {
      int pageIndex = (first / pageSize) + 1;
      stockAgentMapDTO.setPageIndex(pageIndex);
      stockAgentMapDTO.setPageSize(pageSize);    
      stockAgentMapDTO.setSortField(sortField);
      
      if(sortField != null && sortOrder.equals(SortOrder.ASCENDING)){
        stockAgentMapDTO.setSortOrder(Constants.ASCENDING_SORT);
      } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)){
        stockAgentMapDTO.setSortOrder(Constants.DESCENDING_SORT);
      }
      
      dataSource = stockAgentMapService.getAllStockAgentMapByAgentId(stockAgentMapDTO);
      setRowCount(stockAgentMapService.countAllStockAgentMapByAgentId(stockAgentMapDTO).intValue());
    } catch (CmsRestException e) {
      Map<String, String> eMessage = EtopupUtil.read(e.getMessage());
      logger.error("Loading StockAgentMap  data failed, because of " 
                  + getErrorMessage(eMessage.get(Constants.MESSAGE_CODE)) + ", errorCode=" + eMessage.get(Constants.MESSAGE_CODE));
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  getErrorMessage(eMessage.get(Constants.MESSAGE_CODE))); 
      FacesContext.getCurrentInstance().addMessage(null, message);  
     
    } catch (ResourceAccessException e){
      logger.error("Loading StockAgentMap data failed, because of " + Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER);
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
  public boolean isRowAvailable(){
    if (dataSource == null){
      return false;
    }
    int index = rowIndex % pageSize;
    return index >= 0 && index < dataSource.size();
  }
  
  @Override
  public Object getRowKey(StockAgentMap stockAgentMap){
    return stockAgentMap.getId();
  }
  
  @Override
  public StockAgentMap getRowData(){
    if (dataSource == null) return null;
    int index = rowIndex % pageSize;
    if(index > dataSource.size()) return null;
    return dataSource.get(index);
  }
  
  @Override
  public StockAgentMap getRowData(String rowKey){
    if (dataSource == null) return null;
    for(StockAgentMap stockAgentMap : dataSource){
      if (stockAgentMap.getId().toString().equals(rowKey)){
        return stockAgentMap;
      }
    }
    return null;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void setWrappedData(Object list){
    this.dataSource = (List<StockAgentMap>) list;
  }
  
  @Override
  public Object getWrappedData(){
    return dataSource;
  }

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public int getRowIndex() {
    return rowIndex;
  }

  @Override
  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }
  
  @Override
  public int getRowCount() {
    return rowCount;
  }

  @Override
  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public List<StockAgentMap> getDataSource() {
    return dataSource;
  }

  public void setDataSource(List<StockAgentMap> dataSource) {
    this.dataSource = dataSource;
  }

  public EtopupStockAgentMapService getStockAgentMapService() {
    return stockAgentMapService;
  }

  public void setStockAgentMapService(EtopupStockAgentMapService stockAgentMapService) {
    this.stockAgentMapService = stockAgentMapService;
  }

  public StockAgentMapDTO getStockAgentMapDTO() {
    return stockAgentMapDTO;
  }

  public void setStockAgentMapDTO(StockAgentMapDTO stockAgentMapDTO) {
    this.stockAgentMapDTO = stockAgentMapDTO;
  }
  
  

}
