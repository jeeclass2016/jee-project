/**
 * 
 */
package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.AgentAddressMgmtDTO;
import com.vietsci.cms.frontend.etopup.model.AgentAddressManagement;
import com.vietsci.cms.frontend.etopup.service.AgentAddressManagementService;
import com.vietsci.cms.frontend.exception.CmsRestException;

/**
 * @author NguyenPV
 *
 */
public class AgentAddressLazyDataModel extends LazyDataModel<AgentAddressManagement> {

  /**
   * 
   */
  private static final long serialVersionUID = 2158537423643370473L;
  
  private static final Log logger = LogFactory.getLog(AgentAddressLazyDataModel.class);
  
  private List<AgentAddressManagement> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private AgentAddressManagementService agentAddressManagementService;
  private AgentAddressMgmtDTO agentAddressMgmtDTO;

  public AgentAddressLazyDataModel() {
  }
  
  public AgentAddressLazyDataModel(AgentAddressManagementService agentAddressManagementService, AgentAddressMgmtDTO agentAddressMgmtDTO) {
    this.agentAddressManagementService = agentAddressManagementService;
    this.agentAddressMgmtDTO = agentAddressMgmtDTO;
  }

  @Override
  public List<AgentAddressManagement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    agentAddressMgmtDTO.setPageIndex(pageIndex);
    agentAddressMgmtDTO.setPageSize(pageSize);
    
    agentAddressMgmtDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      agentAddressMgmtDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      agentAddressMgmtDTO.setSortOrder(Constants.DESCENDING_SORT);
    }
    datasource = agentAddressManagementService.findAgentAddressList(agentAddressMgmtDTO);
    setRowCount(agentAddressManagementService.countTotalRecord(agentAddressMgmtDTO));
    return datasource;
  }

  @Override
  public boolean isRowAvailable() {
    if (datasource == null)
      return false;
    int index = rowIndex % pageSize;
    return index >= 0 && index < datasource.size();
  }

  @Override
  public Object getRowKey(AgentAddressManagement obj) {
    return obj.getAgentAddressId().toString();
  }

  @Override
  public AgentAddressManagement getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public AgentAddressManagement getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (AgentAddressManagement obj : datasource) {
      if (obj.getAgentAddressId().toString().equals(rowKey))
        return obj;
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
    this.datasource = (List<AgentAddressManagement>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }
  
}
