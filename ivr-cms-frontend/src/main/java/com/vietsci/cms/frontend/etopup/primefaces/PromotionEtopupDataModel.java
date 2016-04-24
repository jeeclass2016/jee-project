package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import com.vietsci.cms.frontend.etopup.model.PromotionEtopup;

import org.primefaces.model.SelectableDataModel;

public class PromotionEtopupDataModel extends ListDataModel<PromotionEtopup> implements SelectableDataModel<PromotionEtopup>, Serializable {

  /**
   * Serial Version ID.
   */
  private static final long serialVersionUID = -8371897571430676085L;

  public PromotionEtopupDataModel() {

  }

  public PromotionEtopupDataModel(List<PromotionEtopup> data) {
    super(data);
  }

  @Override
  public PromotionEtopup getRowData(String rowKey) {

    @SuppressWarnings("unchecked")
    List<PromotionEtopup> promotions = (List<PromotionEtopup>) getWrappedData();
    for (PromotionEtopup promotion : promotions) {
      if(promotion.getId().equals(rowKey))
        return promotion;
    }

    return null;
  }

  @Override
  public Object getRowKey(PromotionEtopup promotion) {
    return 1;
  }

}
