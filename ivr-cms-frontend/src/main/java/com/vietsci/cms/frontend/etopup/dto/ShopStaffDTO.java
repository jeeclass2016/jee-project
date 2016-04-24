package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;

public class ShopStaffDTO implements Serializable {
  private static final long serialVersionUID = 2340098493520550673L;
  private Long shopId;
  private Long staffId;
  private String type;
  private String status;
  private Long stockStaffId;
  private Long stockShopId;
  private String shopName;
  
  public ShopStaffDTO() {
  }
  
  public ShopStaffDTO(Long shopId, Long staffId, String type, String status, Long stockStaffId, Long stockShopId,
      String shopName) {
    super();
    this.shopId = shopId;
    this.staffId = staffId;
    this.type = type;
    this.status = status;
    this.stockStaffId = stockStaffId;
    this.stockShopId = stockShopId;
    this.shopName = shopName;
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
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Long getStockStaffId() {
    return stockStaffId;
  }
  public void setStockStaffId(Long stockId) {
    this.stockStaffId = stockId;
  }
  
  public Long getStockShopId() {
    return stockShopId;
  }

  public void setStockShopId(Long stockShopId) {
    this.stockShopId = stockShopId;
  }


  public String getShopName() {
    return shopName;
  }


  public void setShopName(String shopName) {
    this.shopName = shopName;
  }
}
