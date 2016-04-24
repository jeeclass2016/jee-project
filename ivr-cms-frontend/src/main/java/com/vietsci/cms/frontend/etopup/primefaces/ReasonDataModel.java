package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.Reason;

/** 
 * Data Model class for Reason entity.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@SuppressWarnings("serial")
public class ReasonDataModel extends ListDataModel<Reason> implements SelectableDataModel<Reason>, Serializable {  

  public ReasonDataModel() {
  }

  public ReasonDataModel(List<Reason> data) {
    super(data);
  }
  
  @Override
  public Reason getRowData(String rowKey) {
    //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
    
    @SuppressWarnings("unchecked")
    List<Reason> reasons = (List<Reason>) getWrappedData();
    for(Reason reason : reasons) {
        if(reason.getCode().equals(rowKey))
            return reason;
    }
    
    return null;
  }

  @Override
  public Object getRowKey(Reason reason) {
    return reason.getCode();
  }
}
                    