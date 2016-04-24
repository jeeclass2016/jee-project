package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.AgentDataChange;

public class AgentChangeHistoryDataModel extends ListDataModel<AgentDataChange> implements SelectableDataModel<AgentDataChange>, Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 8990425827348780599L;

  @Override
  public AgentDataChange getRowData(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getRowKey(AgentDataChange arg0) {
    // TODO Auto-generated method stub
    return null;
  }

}
