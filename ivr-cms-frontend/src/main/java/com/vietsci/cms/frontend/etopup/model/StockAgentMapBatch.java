package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class StockAgentMapBatch implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 351373674689303599L;

  private Long batchId = 0L;
  private Set<StockAgentMapItem> goodStockAgentMapItems = new HashSet<>();
  private Set<BadItemInReading> badItemsInReading = new HashSet<>();
  private Set<StockAgentMapItem> badStockAgentMapItems = new HashSet<>();

  public Long getBatchId() {
    return batchId;
  }

  public void setBatchId(Long batchId) {
    this.batchId = batchId;
  }

  public Set<StockAgentMapItem> getGoodStockAgentMapItems() {
    return goodStockAgentMapItems;
  }

  public void setGoodStockAgentMapItems(Set<StockAgentMapItem> goodStockAgentMapItems) {
    this.goodStockAgentMapItems = goodStockAgentMapItems;
  }

  public Set<StockAgentMapItem> getBadStockAgentMapItems() {
    return badStockAgentMapItems;
  }

  public void setBadStockAgentMapItems(Set<StockAgentMapItem> badStockAgentMapItems) {
    this.badStockAgentMapItems = badStockAgentMapItems;
  }

  public Set<BadItemInReading> getBadItemsInReading() {
    return badItemsInReading;
  }

  public void setBadItemsInReading(Set<BadItemInReading> badItemsInReading) {
    this.badItemsInReading = badItemsInReading;
  }

  public boolean hasErrorInReading() {
    if (badItemsInReading != null && badItemsInReading.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean hasErrorInProcessing() {
    if (badStockAgentMapItems != null && badStockAgentMapItems.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  public class BadItemInReading {
    String itemValue;
    String errorMessage;

    public BadItemInReading() {
    }

    public BadItemInReading(String itemValue, String errorMessage) {
      this.itemValue = itemValue;
      this.errorMessage = errorMessage;
    }

    public String getItemValue() {
      return itemValue;
    }

    public void setItemValue(String itemValue) {
      this.itemValue = itemValue;
    }

    public String getErrorMessage() {
      return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
      result = prime * result + ((itemValue == null) ? 0 : itemValue.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      BadItemInReading other = (BadItemInReading) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (errorMessage == null) {
        if (other.errorMessage != null)
          return false;
      } else if (!errorMessage.equals(other.errorMessage))
        return false;
      if (itemValue == null) {
        if (other.itemValue != null)
          return false;
      } else if (!itemValue.equals(other.itemValue))
        return false;
      return true;
    }

    private StockAgentMapBatch getOuterType() {
      return StockAgentMapBatch.this;
    }
  }
}
