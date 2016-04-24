package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.Centre;


public class CentreDataModel extends ListDataModel<Centre> implements SelectableDataModel<Centre>, Serializable{

  
  /**
   * TODO
   * lehuyquang
   * Mar 25, 2014
   */
  private static final long serialVersionUID = -3547902083671486426L;

  public CentreDataModel(){
    
  }
  
  public CentreDataModel(List<Centre> data){
    super(data);
  }

  @Override
  public Centre getRowData(String rowKey) {
    @SuppressWarnings("unchecked")
    List<Centre> centres = (List<Centre>) getWrappedData();
    for(Centre centre : centres){
      if(centre.getCentreId() == Long.getLong(rowKey))
        return centre;
    }
    return null;
  }

  @Override
  public Object getRowKey(Centre centre) {
    // TODO Auto-generated method stub
    return centre.getCentreId();
  }

}
