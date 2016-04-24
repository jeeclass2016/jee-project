package com.vietsci.cms.frontend.etopup.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AgentManagementDTO extends BaseDTO implements java.io.Serializable {

  /**
   * TODO
   * lehuyquang
   * Apr 4, 2014
   */
  private static final long serialVersionUID = -6505934908431960934L;

  private Long agentId;
  private String msisdn;
  private String iccid;
  private String tradeName;
  private String ownerName;
  private Date birthDate;
  private String contactNo;
  private String outletAddress;
  private String email;
  private String secureQuestion;
  private String mpin;
  private String sapCode;
  private Date createDate;
  private Date lastModified;
  private long loginFailureCount;
  private Date mpinExpireDate;
  private Long parentId;
  private Integer status;
  private String tin;
  private long centreId;
  private String idNo;
  private String mpinStatus;
  private String reason;
  private Boolean receiveReportByMail;
  private BigDecimal agentParent;
  private String vendorType;
  private String district;
  private String province;
  private String type;
  private String agentStatus;
  private Date dateIssue;
  private String placeIssue;
  private String territoryStatus;
  private Date activeDate;
  private Boolean transStatus;
  private String qrCode;

  public AgentManagementDTO() {
  }

  public Long getAgentId() {
    return this.agentId;
  }

  public void setAgentId(Long agentId) {
    this.agentId = agentId;
  }

  public String getMsisdn() {
    return this.msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getIccid() {
    return this.iccid;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public String getTradeName() {
    return this.tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getOwnerName() {
    return this.ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public Date getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getContactNo() {
    return this.contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public String getOutletAddress() {
    return this.outletAddress;
  }

  public void setOutletAddress(String outletAddress) {
    this.outletAddress = outletAddress;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSecureQuestion() {
    return this.secureQuestion;
  }

  public void setSecureQuestion(String secureQuestion) {
    this.secureQuestion = secureQuestion;
  }

  public String getMpin() {
    return this.mpin;
  }

  public void setMpin(String mpin) {
    this.mpin = mpin;
  }

  public String getSapCode() {
    return this.sapCode;
  }

  public void setSapCode(String sapCode) {
    this.sapCode = sapCode;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastModified() {
    return this.lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public long getLoginFailureCount() {
    return this.loginFailureCount;
  }

  public void setLoginFailureCount(long loginFailureCount) {
    this.loginFailureCount = loginFailureCount;
  }

  public Date getMpinExpireDate() {
    return this.mpinExpireDate;
  }

  public void setMpinExpireDate(Date mpinExpireDate) {
    this.mpinExpireDate = mpinExpireDate;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getTin() {
    return this.tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public long getCentreId() {
    return this.centreId;
  }

  public void setCentreId(long centreId) {
    this.centreId = centreId;
  }

  public String getIdNo() {
    return this.idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getMpinStatus() {
    return this.mpinStatus;
  }

  public void setMpinStatus(String mpinStatus) {
    this.mpinStatus = mpinStatus;
  }

  public String getReason() {
    return this.reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Boolean getReceiveReportByMail() {
    return this.receiveReportByMail;
  }

  public void setReceiveReportByMail(Boolean receiveReportByMail) {
    this.receiveReportByMail = receiveReportByMail;
  }

  public BigDecimal getAgentParent() {
    return this.agentParent;
  }

  public void setAgentParent(BigDecimal agentParent) {
    this.agentParent = agentParent;
  }

  public String getVendorType() {
    return this.vendorType;
  }

  public void setVendorType(String vendorType) {
    this.vendorType = vendorType;
  }

  public String getDistrict() {
    return this.district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAgentStatus() {
    return this.agentStatus;
  }

  public void setAgentStatus(String agentStatus) {
    this.agentStatus = agentStatus;
  }

  public Date getDateIssue() {
    return this.dateIssue;
  }

  public void setDateIssue(Date dateIssue) {
    this.dateIssue = dateIssue;
  }

  public String getPlaceIssue() {
    return this.placeIssue;
  }

  public void setPlaceIssue(String placeIssue) {
    this.placeIssue = placeIssue;
  }

  public String getTerritoryStatus() {
    return this.territoryStatus;
  }

  public void setTerritoryStatus(String territoryStatus) {
    this.territoryStatus = territoryStatus;
  }

  public Date getActiveDate() {
    return this.activeDate;
  }

  public void setActiveDate(Date activeDate) {
    this.activeDate = activeDate;
  }

  public Boolean getTransStatus() {
    return this.transStatus;
  }

  public void setTransStatus(Boolean transStatus) {
    this.transStatus = transStatus;
  }

  public String getQrCode() {
    return this.qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

}
