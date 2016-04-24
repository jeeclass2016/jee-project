package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.CentreDTO;
import com.vietsci.cms.frontend.etopup.model.Centre;
import com.vietsci.cms.frontend.etopup.service.CentreService;
import com.vietsci.cms.frontend.exception.CmsRestException;

public class CentreLazyDataModel extends LazyDataModel<Centre> {

  /**
   * TODO
   * lehuyquang
   * Mar 26, 2014
   */
  private static final long serialVersionUID = -6420092758558236059L;

  private List<Centre> dataSource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private CentreService centreService;
  private CentreDTO centreDTO;
  
  final Logger logger = LoggerFactory.getLogger(getClass());
  
  public CentreLazyDataModel(CentreService centreService, CentreDTO centreDTO){
    this.centreService = centreService;
    this.centreDTO = centreDTO;
  }
  
  @Override
  public List<Centre> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
    try {
      int pageIndex = (first / pageSize) + 1;
      centreDTO.setPageIndex(pageIndex);
      centreDTO.setPageSize(pageSize);    
      centreDTO.setSortField(sortField);
      
      if(sortField != null && sortOrder.equals(SortOrder.ASCENDING)){
        centreDTO.setSortOrder(Constants.ASCENDING_SORT);
      } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)){
        centreDTO.setSortOrder(Constants.DESCENDING_SORT);
      }
      dataSource = centreService.findCentre(centreDTO);
      setRowCount(centreService.countTotalRecord(centreDTO));
    } catch (CmsRestException e) {
      Map<String, String> eMessage = EtopupUtil.read(e.getMessage());
      logger.error("Loading centre data failed, because of " 
                  + eMessage.get("message") + ", errorCode=" + eMessage.get(Constants.MESSAGE_CODE));
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  eMessage.get(Constants.MESSAGE)); 
      FacesContext.getCurrentInstance().addMessage(null, message);  
     
    } catch (ResourceAccessException e){
      logger.error("Loading centre data failed, because of " + Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER);
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
          Constants.CentreManagement.MESSAGE_CANNOT_ACCESS_SERVER); 
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    return dataSource;
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
  public Object getRowKey(Centre centre){
    return centre.getCentreId().toString();
  }
  
  @Override
  public Centre getRowData(){
    if (dataSource == null) return null;
    int index = rowIndex % pageSize;
    if(index > dataSource.size()) return null;
    return dataSource.get(index);
  }
  
  @Override
  public Centre getRowData(String rowKey){
    if (dataSource == null) return null;
    for(Centre centre : dataSource){
      if (centre.getCentreId().toString().equals(rowKey)){
        return centre;
      }
    }
    return null;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void setWrappedData(Object list){
    this.dataSource = (List<Centre>) list;
  }
  
  @Override
  public Object getWrappedData(){
    return dataSource;
  }

  
  public List<Centre> getDataSource() {
    return dataSource;
  }

  public void setDataSource(List<Centre> dataSource) {
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

  public CentreService getCentreService() {
    return centreService;
  }

  public void setCentreService(CentreService centreService) {
    this.centreService = centreService;
  }

  public CentreDTO getCentreDTO() {
    return centreDTO;
  }

  public void setCentreDTO(CentreDTO centreDTO) {
    this.centreDTO = centreDTO;
  }
  

}
