package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.TransactionTypeDTO;
import com.vietsci.cms.frontend.etopup.model.TransactionType;
import com.vietsci.cms.frontend.etopup.service.TransactionTypeService;

public class TransactionTypeLazyDataModel extends LazyDataModel<TransactionType> {

  /**
   * 
   */
  private static final long serialVersionUID = 2158537423643370473L;
  
  private List<TransactionType> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private TransactionTypeService transactionTypeService;
  private TransactionTypeDTO transactionTypeDTO;

  public TransactionTypeLazyDataModel(TransactionTypeService transactionTypeService, TransactionTypeDTO transactionTypeDTO) {
    this.transactionTypeService = transactionTypeService;
    this.transactionTypeDTO = transactionTypeDTO;
  }

  @Override
  public List<TransactionType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    transactionTypeDTO.setPageIndex(pageIndex);
    transactionTypeDTO.setPageSize(pageSize);
    
    transactionTypeDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      transactionTypeDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      transactionTypeDTO.setSortOrder(Constants.DESCENDING_SORT);
    }
    datasource = transactionTypeService.getTransactionTypeList(transactionTypeDTO);
    setRowCount(transactionTypeService.countTotalRecord(transactionTypeDTO));
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
  public Object getRowKey(TransactionType transactionType) {
    return transactionType.getId();
  }

  @Override
  public TransactionType getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public TransactionType getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (TransactionType transactionType : datasource) {
      if (transactionType.getId() == Long.parseLong(rowKey))
        return transactionType;
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
    this.datasource = (List<TransactionType>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }

}
