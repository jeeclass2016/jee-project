package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.Agent;

public class AgentDataModel extends ListDataModel<Agent> implements SelectableDataModel<Agent>, Serializable {
  
  /**
   * Serial Version ID.
   */
  private static final long serialVersionUID = -8371897571430676085L;

  public AgentDataModel() {
    
  }
  
  public AgentDataModel(List<Agent> data) {
    super(data);
  }

  @Override
  public Agent getRowData(String rowKey) {

    @SuppressWarnings("unchecked")
      List<Agent> agents = (List<Agent>) getWrappedData();
      for(Agent agent : agents) {
        if(new Long(agent.getAgentId()).equals(rowKey))
          return agent;
      }
      
      return null;
  }

  @Override
  public Object getRowKey(Agent agent) {
    return agent.getAgentId();
  }

}
