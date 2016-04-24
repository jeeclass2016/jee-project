package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.AgentAddressManagement;

/** 
 * Data Model class for Reason entity.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@SuppressWarnings("serial")
public class AgentAddressDataModel extends ListDataModel<AgentAddressManagement> implements SelectableDataModel<AgentAddressManagement>, Serializable {  

  public AgentAddressDataModel() {
  }

  public AgentAddressDataModel(List<AgentAddressManagement> data) {
    super(data);
  }
  
  @Override
  public AgentAddressManagement getRowData(String rowKey) {
    //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
    
    @SuppressWarnings("unchecked")
    List<AgentAddressManagement> dataList = (List<AgentAddressManagement>) getWrappedData();
    for(AgentAddressManagement obj : dataList) {
        if(obj.getAgentAddressId().toString().equals(rowKey))
            return obj;
    }
    
    return null;
  }

  @Override
  public Object getRowKey(AgentAddressManagement obj) {
    return obj.getAgentAddressId().toString();
  }
}
                    