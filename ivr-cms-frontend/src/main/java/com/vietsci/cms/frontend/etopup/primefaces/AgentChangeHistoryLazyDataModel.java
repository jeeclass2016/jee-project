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
import com.vietsci.cms.frontend.etopup.dto.AgentDataChangeDTO;
import com.vietsci.cms.frontend.etopup.model.AgentDataChange;
import com.vietsci.cms.frontend.etopup.service.AgentHistService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.main.controller.LanguageController;

public class AgentChangeHistoryLazyDataModel extends LazyDataModel<AgentDataChange>{

  /**
   * 
   */
  private static final long serialVersionUID = 2030912949632670658L;

  
  
  private List<AgentDataChange> dataSource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private AgentHistService agentHistService;
  private AgentDataChangeDTO agentDataChangeDTO;
  
  @Inject 
  @Named
  LanguageController languageController;
  
  final Logger logger = LoggerFactory.getLogger(AgentChangeHistoryLazyDataModel.class);
  
  public AgentChangeHistoryLazyDataModel(AgentHistService agentHistService, AgentDataChangeDTO agentDataChangeDTO){
    this.agentHistService = agentHistService;
    this.agentDataChangeDTO = agentDataChangeDTO;
  }
  
  @Override
  public List<AgentDataChange> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
    try {
      int pageIndex = (first / pageSize) + 1;
      agentDataChangeDTO.setPageIndex(pageIndex);
      agentDataChangeDTO.setPageSize(pageSize);    
      agentDataChangeDTO.setSortField(sortField);
      
      if(sortField != null && sortOrder.equals(SortOrder.ASCENDING)){
        agentDataChangeDTO.setSortOrder(Constants.ASCENDING_SORT);
      } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)){
        agentDataChangeDTO.setSortOrder(Constants.DESCENDING_SORT);
      }
      dataSource = agentHistService.getAllAgentChanges(agentDataChangeDTO);
      setRowCount(agentHistService.countAllAgentChanges(agentDataChangeDTO).intValue());
    } catch (CmsRestException e) {
      Map<String, String> eMessage = EtopupUtil.read(e.getMessage());
      logger.error("Loading AgentDataChange data failed, because of " 
                  + getErrorMessage(eMessage.get(Constants.MESSAGE_CODE)) + ", errorCode=" + eMessage.get(Constants.MESSAGE_CODE));
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  getErrorMessage(eMessage.get(Constants.MESSAGE_CODE))); 
      FacesContext.getCurrentInstance().addMessage(null, message);  
     
    } catch (ResourceAccessException e){
      logger.error("Loading AgentDataChange data failed, because of " + Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER);
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
  public Object getRowKey(AgentDataChange AgentDataChange){
    return AgentDataChange.toString();
  }
  
  @Override
  public AgentDataChange getRowData(){
    if (dataSource == null) return null;
    int index = rowIndex % pageSize;
    if(index > dataSource.size()) return null;
    return dataSource.get(index);
  }
  
  @Override
  public AgentDataChange getRowData(String rowKey){
    if (dataSource == null) return null;
    for(AgentDataChange AgentDataChange : dataSource){
      if (AgentDataChange.toString().equals(rowKey)){
        return AgentDataChange;
      }
    }
    return null;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void setWrappedData(Object list){
    this.dataSource = (List<AgentDataChange>) list;
  }
  
  @Override
  public Object getWrappedData(){
    return dataSource;
  }

  
  public List<AgentDataChange> getDataSource() {
    return dataSource;
  }

  public void setDataSource(List<AgentDataChange> dataSource) {
    this.dataSource = dataSource;
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

  public AgentHistService getAgentManagementService() {
    return agentHistService;
  }

  public void setAgentManagementService(AgentHistService agentHistService) {
    this.agentHistService = agentHistService;
  }

  public AgentDataChangeDTO getAgentManagementDTO() {
    return agentDataChangeDTO;
  }

  public void setAgentManagementDTO(AgentDataChangeDTO agentManagementDTO) {
    this.agentDataChangeDTO = agentManagementDTO;
  }

}
