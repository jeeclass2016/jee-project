package com.vietsci.cms.frontend.etopup.primefaces;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BonusStatusBrowserEtopupDataModel extends LazyDataModel<Trans> {

  private static final Log logger = LogFactory.getLog(BonusStatusBrowserEtopupDataModel.class);

  private TransactionManagementDTO managementDTO;
  private List<Trans> datasource;

  private int pageSize;
  private int rowIndex;
  private int rowCount;

  private TransactionManagementService transactionManagementService;


  public BonusStatusBrowserEtopupDataModel() {
    datasource = new ArrayList<>();
  }

  public BonusStatusBrowserEtopupDataModel(TransactionManagementService transactionManagementService, TransactionManagementDTO managementDTO) {
    this();
    this.managementDTO = managementDTO;
    this.transactionManagementService = transactionManagementService;
    init();
  }

  @Override
  public List<Trans> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    try {

      int pageIndex = (first / pageSize) + 1;
      managementDTO.setPageIndex(pageIndex);
      managementDTO.setPageSize(pageSize);
      managementDTO.setSortField(sortField);

      if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
        managementDTO.setSortOrder(Constants.ASCENDING_SORT);
      } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
        managementDTO.setSortOrder(Constants.DESCENDING_SORT);
      }

      init();

      setRowCount(datasource.size());
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Khong the load du lieu len bang hien thi");
    }

    return datasource;
  }

  private void init() {
    datasource = transactionManagementService.findBonusStatus(managementDTO);
  }

  @Override
  public boolean isRowAvailable() {
    if (datasource == null)
      return false;
    int index = rowIndex % pageSize;
    return index >= 0 && index < datasource.size();
  }

  @Override
  public Object getRowKey(Trans resultData) {
    return resultData.getTransId();
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
