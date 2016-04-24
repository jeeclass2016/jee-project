package com.vietsci.cms.frontend.etopup.primefaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.PromotionDTO;
import com.vietsci.cms.frontend.etopup.model.PromotionEtopup;
import com.vietsci.cms.frontend.etopup.service.PromotionEtopupService;

public class PromotionEtopupLazyDataModel extends LazyDataModel<PromotionEtopup> {

  /**
   * Serial ID
   */
  private static final long serialVersionUID = 6624373770455755447L;
  
  private List<PromotionEtopup> datasource;
  private int pageSize;
  private int rowIndex;
  private int rowCount;
  private PromotionEtopupService promotionEtopupService;
  private PromotionDTO promotionDTO;

  public PromotionEtopupLazyDataModel(PromotionEtopupService promotionEtopupService, PromotionDTO promotionDTO) {
    this.promotionEtopupService = promotionEtopupService;
    this.promotionDTO = promotionDTO;
  }

  @Override
  public List<PromotionEtopup> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    int pageIndex = (first / pageSize) + 1;
    promotionDTO.setPageIndex(pageIndex);
    promotionDTO.setPageSize(pageSize);
    
    promotionDTO.setSortField(sortField);
    if (sortField != null && sortOrder.equals(SortOrder.ASCENDING)) {
      promotionDTO.setSortOrder(Constants.ASCENDING_SORT);
    } else if (sortField != null && sortOrder.equals(SortOrder.DESCENDING)) {
      promotionDTO.setSortOrder(Constants.DESCENDING_SORT);
    }
    
    datasource = promotionEtopupService.doFindPromotions(promotionDTO);
    setRowCount(promotionEtopupService.countTotalRecord(promotionDTO));
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
  public Object getRowKey(PromotionEtopup promotion) {
    return promotion.getId().toString();
  }

  @Override
  public PromotionEtopup getRowData() {
    if (datasource == null)
      return null;
    int index = rowIndex % pageSize;
    if (index > datasource.size()) {
      return null;
    }
    return datasource.get(index);
  }

  @Override
  public PromotionEtopup getRowData(String rowKey) {
    if (datasource == null)
      return null;
    for (PromotionEtopup promotion : datasource) {
      if (promotion.getId().toString().equals(rowKey))
        return promotion;
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
    this.datasource = (List<PromotionEtopup>) list;
  }

  @Override
  public Object getWrappedData() {
    return datasource;
  }

}
