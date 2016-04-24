package com.vietsci.cms.frontend.etopup.model;

import java.math.BigDecimal;
import java.util.Date;

public class ErpPosSaleOrders implements java.io.Serializable {

  private Long orderNumber;
  private Integer headerId;
  private Integer orderTypeId;
  private String orderType;
  private Integer shipToOrgId;
  private String saleChannel;
  private String partyType;
  private String partyNumber;
  private Integer partyId;
  private String dealerCode;
  private String partyName;
  private Date orderDate;
  private Integer lineNumber;
  private Integer lineId;
  private Integer inventoryItemId;
  private String itemTypeCode;
  private String orderQuantityUom;
  private String shippingQuantityUom;
  private Integer orderedQuantity;
  private Integer pricingQuantity;
  private String pricingQuantityUom;
  private BigDecimal amount;
  private BigDecimal unitListPrice;
  private Integer taxValue;
  private String orderedItem;
  private Integer orderedItemId;
  private Date lastModified;
  private Integer staffId;
  private String staffCode;
  private Date issueDateTime;
  private Integer status;
  private Long transId;
  private String custPoNumber;


  public Long getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Long orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Integer getHeaderId() {
    return headerId;
  }

  public void setHeaderId(Integer headerId) {
    this.headerId = headerId;
  }

  public Integer getOrderTypeId() {
    return orderTypeId;
  }

  public void setOrderTypeId(Integer orderTypeId) {
    this.orderTypeId = orderTypeId;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public Integer getShipToOrgId() {
    return shipToOrgId;
  }

  public void setShipToOrgId(Integer shipToOrgId) {
    this.shipToOrgId = shipToOrgId;
  }

  public String getSaleChannel() {
    return saleChannel;
  }

  public void setSaleChannel(String saleChannel) {
    this.saleChannel = saleChannel;
  }

  public String getPartyType() {
    return partyType;
  }

  public void setPartyType(String partyType) {
    this.partyType = partyType;
  }

  public String getPartyNumber() {
    return partyNumber;
  }

  public void setPartyNumber(String partyNumber) {
    this.partyNumber = partyNumber;
  }

  public Integer getPartyId() {
    return partyId;
  }

  public void setPartyId(Integer partyId) {
    this.partyId = partyId;
  }

  public String getDealerCode() {
    return dealerCode;
  }

  public void setDealerCode(String dealerCode) {
    this.dealerCode = dealerCode;
  }

  public String getPartyName() {
    return partyName;
  }

  public void setPartyName(String partyName) {
    this.partyName = partyName;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Integer getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
  }

  public Integer getLineId() {
    return lineId;
  }

  public void setLineId(Integer lineId) {
    this.lineId = lineId;
  }

  public Integer getInventoryItemId() {
    return inventoryItemId;
  }

  public void setInventoryItemId(Integer inventoryItemId) {
    this.inventoryItemId = inventoryItemId;
  }

  public String getItemTypeCode() {
    return itemTypeCode;
  }

  public void setItemTypeCode(String itemTypeCode) {
    this.itemTypeCode = itemTypeCode;
  }

  public String getOrderQuantityUom() {
    return orderQuantityUom;
  }

  public void setOrderQuantityUom(String orderQuantityUom) {
    this.orderQuantityUom = orderQuantityUom;
  }

  public String getShippingQuantityUom() {
    return shippingQuantityUom;
  }

  public void setShippingQuantityUom(String shippingQuantityUom) {
    this.shippingQuantityUom = shippingQuantityUom;
  }

  public Integer getOrderedQuantity() {
    return orderedQuantity;
  }

  public void setOrderedQuantity(Integer orderedQuantity) {
    this.orderedQuantity = orderedQuantity;
  }

  public Integer getPricingQuantity() {
    return pricingQuantity;
  }

  public void setPricingQuantity(Integer pricingQuantity) {
    this.pricingQuantity = pricingQuantity;
  }

  public String getPricingQuantityUom() {
    return pricingQuantityUom;
  }

  public void setPricingQuantityUom(String pricingQuantityUom) {
    this.pricingQuantityUom = pricingQuantityUom;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getUnitListPrice() {
    return unitListPrice;
  }

  public void setUnitListPrice(BigDecimal unitListPrice) {
    this.unitListPrice = unitListPrice;
  }

  public Integer getTaxValue() {
    return taxValue;
  }

  public void setTaxValue(Integer taxValue) {
    this.taxValue = taxValue;
  }

  public String getOrderedItem() {
    return orderedItem;
  }

  public void setOrderedItem(String orderedItem) {
    this.orderedItem = orderedItem;
  }

  public Integer getOrderedItemId() {
    return orderedItemId;
  }

  public void setOrderedItemId(Integer orderedItemId) {
    this.orderedItemId = orderedItemId;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public Integer getStaffId() {
    return staffId;
  }

  public void setStaffId(Integer staffId) {
    this.staffId = staffId;
  }

  public String getStaffCode() {
    return staffCode;
  }

  public void setStaffCode(String staffCode) {
    this.staffCode = staffCode;
  }

  public Date getIssueDateTime() {
    return issueDateTime;
  }

  public void setIssueDateTime(Date issueDateTime) {
    this.issueDateTime = issueDateTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getTransId() {
    return transId;
  }

  public void setTransId(Long transId) {
    this.transId = transId;
  }

  public String getCustPoNumber() {
    return custPoNumber;
  }

  public void setCustPoNumber(String custPoNumber) {
    this.custPoNumber = custPoNumber;
  }

  public static enum ErpPosSaleOrdersStatus {
    NOT_PASS_AUDIT(0, "Chưa duyệt"),
    PASS_AUDIT(1, "Đã duyệt"),
    REJECT(2, "Từ chối"),
    DELETE(3, "Huỷ"),
    ALL(-1, "Tất cả");

    private int value;
    private String name;


    private ErpPosSaleOrdersStatus(int value, String name) {
      this.value = value;
      this.name = name;
    }

    public int getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }
}
