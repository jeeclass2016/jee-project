package com.vietsci.cms.frontend.main.model;

import java.io.Serializable;

/**
 * Member model 
 */
public class MemberOld implements Serializable {
  private static final long serialVersionUID = 896806308727038278L;
  
  private String fullName;
  private Long stockShopId;
  private Long shopId;
  private Long staffId;
  private Long stockStaffId;
  
  public MemberOld() {
    super();
  }

  public MemberOld(String fullName, Long stockShopId, Long shopId, Long staffId, Long stockStaffId) {
    super();
    this.fullName = fullName;
    this.stockShopId = stockShopId;
    this.shopId = shopId;
    this.staffId = staffId;
    this.stockStaffId = stockStaffId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Long getStockShopId() {
    return stockShopId;
  }

  public void setStockShopId(Long stockShopId) {
    this.stockShopId = stockShopId;
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

  public void setStaffId(long staffId) {
    this.staffId = staffId;
  }

  public Long getStockStaffId() {
    return stockStaffId;
  }

  public void setStockStaffId(Long stockStaffId) {
    this.stockStaffId = stockStaffId;
  }
}
