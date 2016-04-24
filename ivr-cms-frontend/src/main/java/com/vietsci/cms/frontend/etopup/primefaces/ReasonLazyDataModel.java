/**
 * 
 */
package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.ReasonDTO;
import com.vietsci.cms.frontend.etopup.model.Reason;
import com.vietsci.cms.frontend.etopup.service.EtopupReasonService;

/**
 * @author NguyenPV
 *
 */
public class ReasonLazyDataModel extends LazyDataModel<Reason> {

  /**
   * 
   */
  private static final long serialVersionUID = 2158537423643370473L;
  
  private List<Reason> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private EtopupReasonService reasonService;
  private ReasonDTO reasonDTO;

  public ReasonLazyDataModel(EtopupReasonService reasonService, ReasonDTO reasonDTO) {
    this.reasonService = reasonService;
    this.reasonDTO = reasonDTO;
  }

  @Override
  public List<Reason> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    reasonDTO.setPageIndex(pageIndex);
    reasonDTO.setPageSize(pageSize);
    
    reasonDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      reasonDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      reasonDTO.setSortOrder(Constants.DESCENDING_SORT);
    }
    datasource = reasonService.findReasons(reasonDTO);
    setRowCount(reasonService.countTotalRecord(reasonDTO));
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
  public Object getRowKey(Reason reason) {
    return reason.getReasonId().toString();
  }

  @Override
  public Reason getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public Reason getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (Reason reason : datasource) {
      if (reason.getReasonId().toString().equals(rowKey))
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
    this.datasource = (List<Reason>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }

}
