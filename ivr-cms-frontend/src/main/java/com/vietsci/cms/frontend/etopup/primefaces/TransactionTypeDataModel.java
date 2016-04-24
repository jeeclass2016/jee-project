package com.vietsci.cms.frontend.etopup.primefaces;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.vietsci.cms.frontend.etopup.model.TransactionType;

public class TransactionTypeDataModel extends ListDataModel<TransactionType> implements SelectableDataModel<TransactionType>, Serializable {  

  /**
   * 
   */
  private static final long serialVersionUID = 691058402659940868L;

  public TransactionTypeDataModel() {
  }

  public TransactionTypeDataModel(List<TransactionType> data) {
    super(data);
  }
  
  @Override
  public TransactionType getRowData(String rowKey) {
    //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
    
    @SuppressWarnings("unchecked")
    List<TransactionType> TransactionTypes = (List<TransactionType>) getWrappedData();
    for(TransactionType TransactionType : TransactionTypes) {
        if(TransactionType.getCode().equals(rowKey))
            return TransactionType;
    }
    
    return null;
  }

  @Override
  public Object getRowKey(TransactionType TransactionType) {
    return TransactionType.getCode();
  }
}
                    