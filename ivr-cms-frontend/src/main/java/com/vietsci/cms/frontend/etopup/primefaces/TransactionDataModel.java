package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.Trans;

/** 
 * Data Model class for Transaction entity.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@SuppressWarnings("serial")
public class TransactionDataModel extends ListDataModel<Trans> implements SelectableDataModel<Trans>, Serializable {  

  public TransactionDataModel() {
  }

  public TransactionDataModel(List<Trans> data) {
    super(data);
  }
  
  @Override
  public Trans getRowData(String rowKey) {
    //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
    
    @SuppressWarnings("unchecked")
    List<Trans> dataList = (List<Trans>) getWrappedData();
    for(Trans obj : dataList) {
        if(obj.getTransId().toString().equals(rowKey))
            return obj;
    }
    
    return null;
  }

  @Override
  public Object getRowKey(Trans obj) {
    return obj.getTransId().toString();
  }
}
                    