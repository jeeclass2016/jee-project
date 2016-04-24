package com.vietsci.cms.frontend.etopup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Lop the hien thong tin dai ly.
 *
 * @author lam.lethanh
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class Agent implements Serializable {
  
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 7282278541276099896L;
  
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
  private Long loginFailureCount;
  private Date mpinExpireDate;
  private Long parentId;
  private Integer status;
  private String tin;
  private Long centreId;
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

  private Set<AgentAccount> agentAccounts = new HashSet<AgentAccount>(0);
  private Set<AgentAddressManagement> agentAddressManagements = new HashSet<AgentAddressManagement>(0);
  
  private Status statusEnum;
  
  public Agent() {
  }

  public Long getAgentId() {
    return agentId;
  }

  public void setAgentId(Long agentId) {
    this.agentId = agentId;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getIccid() {
    return iccid;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public String getOutletAddress() {
    return outletAddress;
  }

  public void setOutletAddress(String outletAddress) {
    this.outletAddress = outletAddress;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSecureQuestion() {
    return secureQuestion;
  }

  public void setSecureQuestion(String secureQuestion) {
    this.secureQuestion = secureQuestion;
  }

  public String getMpin() {
    return mpin;
  }

  public void setMpin(String mpin) {
    this.mpin = mpin;
  }

  public String getSapCode() {
    return sapCode;
  }

  public void setSapCode(String sapCode) {
    this.sapCode = sapCode;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public Long getLoginFailureCount() {
    return loginFailureCount;
  }

  public void setLoginFailureCount(Long loginFailureCount) {
    this.loginFailureCount = loginFailureCount;
  }

  public Date getMpinExpireDate() {
    return mpinExpireDate;
  }

  public void setMpinExpireDate(Date mpinExpireDate) {
    this.mpinExpireDate = mpinExpireDate;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public Long getCentreId() {
    return centreId;
  }

  public void setCentreId(Long centreId) {
    this.centreId = centreId;
  }

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getMpinStatus() {
    return mpinStatus;
  }

  public void setMpinStatus(String mpinStatus) {
    this.mpinStatus = mpinStatus;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Boolean getReceiveReportByMail() {
    return receiveReportByMail;
  }

  public void setReceiveReportByMail(Boolean receiveReportByMail) {
    this.receiveReportByMail = receiveReportByMail;
  }

  public BigDecimal getAgentParent() {
    return agentParent;
  }

  public void setAgentParent(BigDecimal agentParent) {
    this.agentParent = agentParent;
  }

  public String getVendorType() {
    return vendorType;
  }

  public void setVendorType(String vendorType) {
    this.vendorType = vendorType;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAgentStatus() {
    return agentStatus;
  }

  public void setAgentStatus(String agentStatus) {
    this.agentStatus = agentStatus;
  }

  public Date getDateIssue() {
    return dateIssue;
  }

  public void setDateIssue(Date dateIssue) {
    this.dateIssue = dateIssue;
  }

  public String getPlaceIssue() {
    return placeIssue;
  }

  public void setPlaceIssue(String placeIssue) {
    this.placeIssue = placeIssue;
  }

  public String getTerritoryStatus() {
    return territoryStatus;
  }

  public void setTerritoryStatus(String territoryStatus) {
    this.territoryStatus = territoryStatus;
  }

  public Date getActiveDate() {
    return activeDate;
  }

  public void setActiveDate(Date activeDate) {
    this.activeDate = activeDate;
  }

  public Boolean getTransStatus() {
    return transStatus;
  }

  public void setTransStatus(Boolean transStatus) {
    this.transStatus = transStatus;
  }

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public Set<AgentAccount> getAgentAccounts() {
    return this.agentAccounts;
  }

  public void setAgentAccounts(Set<AgentAccount> agentAccounts) {
    this.agentAccounts = agentAccounts;
  }
  
  public Set<AgentAddressManagement> getAgentAddressManagements() {
    return agentAddressManagements;
  }

  public void setAgentAddressManagements(Set<AgentAddressManagement> agentAddressManagements) {
    this.agentAddressManagements = agentAddressManagements;
  }
  
 public Status getStatusEnum() {
    return statusEnum;
  }

  public void setStatusEnum(Status statusEnum) {
    this.statusEnum = statusEnum;
  }

  public static enum Status {
    ACTIVE(1, "Đang hoạt động"),
    LOST_SIM(2, "Mất SIM"),
    TEMPORAL_LOCKED(3, "Tạm khóa"),
    DELETED(4, "Xóa");

    private int value;
    private String name;


    private Status(int value, String name) {
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

  public static enum AgentStatus {

    NEW("1", "New agent"),
    ACTIVE("2", "Active"),
    IDLE("3", "Idle"),
    NOT_PASS_AUDIT("4", "Not pass audit"),
    PASS_AUDIT("5", "Pass audit");

    private String value;
    private String name;


    private AgentStatus(String value, String name) {
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
