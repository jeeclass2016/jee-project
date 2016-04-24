package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.Agent;


public class AgentManagementDataModel extends ListDataModel<Agent> implements SelectableDataModel<Agent>, Serializable{

  private static final long serialVersionUID = -3548959217412051713L;

  public AgentManagementDataModel(){
    
  }
  
  public AgentManagementDataModel(List<Agent> data){
    super(data);
  }

  @Override
  public Agent getRowData(String rowKey) {
    @SuppressWarnings("unchecked")
    List<Agent> agents = (List<Agent>) getWrappedData();
    for(Agent agent : agents){
      if(agent.getAgentId() == Long.getLong(rowKey))
        return agent;
    }
    return null;
  }

  @Override
  public Object getRowKey(Agent agent) {
    // TODO Auto-generated method stub
    return agent.getAgentId();
  }

}
