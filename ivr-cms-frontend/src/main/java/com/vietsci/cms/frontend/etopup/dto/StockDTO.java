package com.vietsci.cms.frontend.etopup.dto;

public class StockDTO {
  private Long stockId;
  private Long shopId;
  private Long staffId;
  private String type;
  private boolean status;
  public Long getStockId() {
    return stockId;
  }
  public void setStockId(Long stockId) {
    this.stockId = stockId;
  }
  public Long getShopId() {
    return shopId;
  }
  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }
  public Long getStaffId() {
    return staffId;
  }
  public void setStaffId(Long staffId) {
    this.staffId = staffId;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public boolean isStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }

  
  
}