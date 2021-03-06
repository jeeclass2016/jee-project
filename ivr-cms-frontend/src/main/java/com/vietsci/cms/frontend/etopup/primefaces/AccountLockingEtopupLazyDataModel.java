package com.vietsci.cms.frontend.etopup.primefaces;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.AccountLockingDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * LazyDataModel for Account Locking (Khoa tai khoan)
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public class AccountLockingEtopupLazyDataModel extends LazyDataModel<Trans> {

  private List<Trans> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;

  private TransManagementEtopupService transManagementService;
  private AccountLockingDTO accountLockingDTO;

  public AccountLockingEtopupLazyDataModel(TransManagementEtopupService transManagementService,
                                           AccountLockingDTO accountLockingDTO) {
    this.transManagementService = transManagementService;
    this.accountLockingDTO = accountLockingDTO;
  }

  @Override
  public List<Trans> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
    int pageIndex = (first / pageSize) + 1;
    accountLockingDTO.setPageIndex(pageIndex);
    accountLockingDTO.setPageSize(pageSize);

    accountLockingDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      accountLockingDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      accountLockingDTO.setSortOrder(Constants.DESCENDING_SORT);
    }

    datasource = transManagementService.findTrans(accountLockingDTO);
    setRowCount(transManagementService.countTrans(accountLockingDTO).intValue());

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
    return trans.getTransId();
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
    for (Trans trans : datasource) {
      if (String.valueOf(trans.getTransId()).equals(rowKey))
        return trans;
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
