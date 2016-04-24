package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.StockAgentMap;

public class StockAgentMapDataModel extends ListDataModel<StockAgentMap> implements SelectableDataModel<StockAgentMap>, Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1156654128165740450L;
  
  public StockAgentMapDataModel(){
    
  }
  
  public StockAgentMapDataModel(List<StockAgentMap> data){
    super(data);
  }

  @Override
  public StockAgentMap getRowData(String stockAgentKey) {
    @SuppressWarnings("unchecked")
    List<StockAgentMap> stockAgentMaps = (List<StockAgentMap>) getWrappedData();
    for(StockAgentMap stockAgentMap : stockAgentMaps){
      if(stockAgentMap.getId().toString().equals(stockAgentKey))
        return stockAgentMap;
    }
    return null;
  }

  @Override
  public Object getRowKey(StockAgentMap row) {
    return row.getId().toString();
  }

}
