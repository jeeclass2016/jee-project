/**
 * 
 */
package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;

/**
 * @author NguyenPV
 *
 */
public class TLDTransactionLazyDataModel extends LazyDataModel<Trans> {

  /**
   * 
   */
  private static final long serialVersionUID = 2158537423643370473L;
  
  private List<Trans> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private TransactionManagementService transactionManagementService;
  private TransactionManagementDTO transactionManagementDTO;

  public TLDTransactionLazyDataModel(TransactionManagementService transactionManagementService, TransactionManagementDTO transactionManagementDTO) {
    this.transactionManagementService = transactionManagementService;
    this.transactionManagementDTO = transactionManagementDTO;
  }

  @Override
  public List<Trans> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    transactionManagementDTO.setPageIndex(pageIndex);
    transactionManagementDTO.setPageSize(pageSize);
    
    transactionManagementDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      transactionManagementDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      transactionManagementDTO.setSortOrder(Constants.DESCENDING_SORT);
    }
    datasource = transactionManagementService.findTLDPTrans(transactionManagementDTO);
    setRowCount(transactionManagementService.countTLDPTrans(transactionManagementDTO));
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
  public Object getRowKey(Trans trans) {
    return trans.getTransId().toString();
  }

  @Override
  public Trans getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public Trans getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (Trans reason : datasource) {
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
    this.datasource = (List<Trans>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }

}
