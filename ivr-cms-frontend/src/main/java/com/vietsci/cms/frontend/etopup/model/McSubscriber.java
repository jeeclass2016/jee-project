package com.vietsci.cms.frontend.etopup.model;

import java.math.BigDecimal;
import java.util.Date;


public class McSubscriber implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -5353914458668266931L;
  private long subscriberId;
  private String mdn;
  private String imsi;
  private BigDecimal customerId;
  private String status;
  private Long stockCreated;
  private Long stockModified;
  private Date dateCreated;
  private Date lastModify;
  private String cosCode;
  private Long svProductInstanceId;
  private String provisioning;
  private String serial;
  private String lastAuditId;
  private Boolean commStatus;
  private String updatedCust;
  private Date expireDate;

  public McSubscriber() {
  }

  public McSubscriber(long subscriberId, String mdn, String imsi) {
    this.subscriberId = subscriberId;
    this.mdn = mdn;
    this.imsi = imsi;
  }

  public McSubscriber(long subscriberId, String mdn, String imsi, BigDecimal customerId, String status,
      Long stockCreated, Long stockModified, Date dateCreated, Date lastModify, String cosCode,
      Long svProductInstanceId, String provisioning, String serial, String lastAuditId, Boolean commStatus,
      String updatedCust) {
    this.subscriberId = subscriberId;
    this.mdn = mdn;
    this.imsi = imsi;
    this.customerId = customerId;
    this.status = status;
    this.stockCreated = stockCreated;
    this.stockModified = stockModified;
    this.dateCreated = dateCreated;
    this.lastModify = lastModify;
    this.cosCode = cosCode;
    this.svProductInstanceId = svProductInstanceId;
    this.provisioning = provisioning;
    this.serial = serial;
    this.lastAuditId = lastAuditId;
    this.commStatus = commStatus;
    this.updatedCust = updatedCust;
  }

  public long getSubscriberId() {
    return this.subscriberId;
  }

  public void setSubscriberId(long subscriberId) {
    this.subscriberId = subscriberId;
  }

  public String getMdn() {
    return this.mdn;
  }

  public void setMdn(String mdn) {
    this.mdn = mdn;
  }

  public String getImsi() {
    return this.imsi;
  }

  public void setImsi(String imsi) {
    this.imsi = imsi;
  }

  public BigDecimal getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(BigDecimal customerId) {
    this.customerId = customerId;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getStockCreated() {
    return this.stockCreated;
  }

  public void setStockCreated(Long stockCreated) {
    this.stockCreated = stockCreated;
  }

  public Long getStockModified() {
    return this.stockModified;
  }

  public void setStockModified(Long stockModified) {
    this.stockModified = stockModified;
  }

  public Date getDateCreated() {
    return this.dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getLastModify() {
    return this.lastModify;
  }

  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }

  public String getCosCode() {
    return this.cosCode;
  }

  public void setCosCode(String cosCode) {
    this.cosCode = cosCode;
  }

  public Long getSvProductInstanceId() {
    return this.svProductInstanceId;
  }

  public void setSvProductInstanceId(Long svProductInstanceId) {
    this.svProductInstanceId = svProductInstanceId;
  }

  public String getProvisioning() {
    return this.provisioning;
  }

  public void setProvisioning(String provisioning) {
    this.provisioning = provisioning;
  }

  public String getSerial() {
    return this.serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getLastAuditId() {
    return this.lastAuditId;
  }

  public void setLastAuditId(String lastAuditId) {
    this.lastAuditId = lastAuditId;
  }

  public Boolean getCommStatus() {
    return this.commStatus;
  }

  public void setCommStatus(Boolean commStatus) {
    this.commStatus = commStatus;
  }

  public String getUpdatedCust() {
    return this.updatedCust;
  }

  public void setUpdatedCust(String updatedCust) {
    this.updatedCust = updatedCust;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public static enum Status {
    NOT_ACTIVE("0", "Không hoạt động"),
    ACTIVE("1", "Đang hoạt động");

    private String value;
    private String name;


    private Status(String value, String name) {
      this.value = value;
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }

}
