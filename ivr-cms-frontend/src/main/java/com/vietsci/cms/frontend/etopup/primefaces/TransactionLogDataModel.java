package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.TransLog;

/** 
 * Data Model class for Transaction entity.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@SuppressWarnings("serial")
public class TransactionLogDataModel extends ListDataModel<TransLog> implements SelectableDataModel<TransLog>, Serializable {  

  public TransactionLogDataModel() {
  }

  public TransactionLogDataModel(List<TransLog> data) {
    super(data);
  }
  
  @Override
  public TransLog getRowData(String rowKey) {
    //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
    
    @SuppressWarnings("unchecked")
    List<TransLog> dataList = (List<TransLog>) getWrappedData();
    for(TransLog obj : dataList) {
        if(obj.getLogId().toString().equals(rowKey))
            return obj;
    }
    
    return null;
  }

  @Override
  public Object getRowKey(TransLog obj) {
    return obj.getLogId().toString();
  }
}
                    