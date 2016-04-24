package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.SIMBatchDTO;
import com.vietsci.cms.frontend.etopup.model.SimBatch;
import com.vietsci.cms.frontend.etopup.service.SIMBatchEtopupService;

public class SIMBatchEtopupLazyDataModel extends LazyDataModel<SimBatch> {
  /**
   * 
   */
  private static final long serialVersionUID = 6218789026970552916L;
  
  private List<SimBatch> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private SIMBatchEtopupService simBatchService;
  private SIMBatchDTO simBatchDTO;

  public SIMBatchEtopupLazyDataModel(SIMBatchEtopupService simBatchService, SIMBatchDTO simBatchDTO) {
    this.simBatchService = simBatchService;
    this.simBatchDTO = simBatchDTO;
  }
  
  @Override
  public List<SimBatch> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    simBatchDTO.setPageIndex(pageIndex);
    simBatchDTO.setPageSize(pageSize);
    
    simBatchDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      simBatchDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      simBatchDTO.setSortOrder(Constants.DESCENDING_SORT);
    }
    
    datasource = simBatchService.findSIMbatchs(simBatchDTO);
    setRowCount(simBatchService.countTotalRecord(simBatchDTO));
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
  public Object getRowKey(SimBatch simBatch) {
    return simBatch.getBatchId().toString();
  }

  @Override
  public SimBatch getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public SimBatch getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (SimBatch simBatch : datasource) {
      if (simBatch.getBatchId().toString().equals(rowKey))
        return simBatch;
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
    this.datasource = (List<SimBatch>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }

}
