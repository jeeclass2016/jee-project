package com.vietsci.cms.frontend.etopup.primefaces;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.ICCIDRangeDeletionDTO;
import com.vietsci.cms.frontend.etopup.model.LoadedSim;
import com.vietsci.cms.frontend.etopup.service.ICCIDManagentService;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * LazyDataModel for ICCIDRangeDeletion (XoaDaiICCID)
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public class ICCIDRangeDeletionEtopupLazyDataModel extends LazyDataModel<LoadedSim> implements
  SelectableDataModel<LoadedSim> {
  private List<LoadedSim> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;

  private ICCIDManagentService iccidManagentService;
  private ICCIDRangeDeletionDTO iccidRangeDeletionDTO;

  public ICCIDRangeDeletionEtopupLazyDataModel(ICCIDManagentService iccidManagentService, ICCIDRangeDeletionDTO iccidRangeDeletionDTO) {
    this.iccidManagentService = iccidManagentService;
    this.iccidRangeDeletionDTO = iccidRangeDeletionDTO;
  }

  @Override
  public List<LoadedSim> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    iccidRangeDeletionDTO.setPageIndex(pageIndex);
    iccidRangeDeletionDTO.setPageSize(pageSize);

    iccidRangeDeletionDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      iccidRangeDeletionDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      iccidRangeDeletionDTO.setSortOrder(Constants.DESCENDING_SORT);
    }

    datasource = iccidManagentService.findICCIDs(iccidRangeDeletionDTO);
    setRowCount(iccidManagentService.countICCIDs(iccidRangeDeletionDTO).intValue());
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
  public Object getRowKey(LoadedSim loadedSim) {
    return loadedSim.getId();
  }

  @Override
  public LoadedSim getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public LoadedSim getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (LoadedSim loadedSim : datasource) {
      if (String.valueOf(loadedSim.getId()).equals(rowKey))
        return loadedSim;
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
    this.datasource = (List<LoadedSim>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }
}
