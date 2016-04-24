package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/** 
 * DTO dung cho viec quan ly dia ban hoat dong cua dai ly.
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class AgentAddressMgmtDTO extends BaseDTO implements Serializable {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -833143241889535016L;

  private String msisdn;
  
  private Long agentAddressId;
  private Long agentId;
  private String agentName;
  private Long parentAgentId;
  private String parentAgentName;
  private String region;
  private String province;
  private String district;
  private String status;
  private Date createDate;
  private Date startCallDatetime;
  public String getMsisdn() {
    return msisdn;
  }
  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }
  public Long getAgentAddressId() {
    return agentAddressId;
  }
  public void setAgentAddressId(Long agentAddressId) {
    this.agentAddressId = agentAddressId;
  }
  public Long getAgentId() {
    return agentId;
  }
  public void setAgentId(Long agentId) {
    this.agentId = agentId;
  }
  public String getAgentName() {
    return agentName;
  }
  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }
  public Long getParentAgentId() {
    return parentAgentId;
  }
  public void setParentAgentId(Long parentAgentId) {
    this.parentAgentId = parentAgentId;
  }
  public String getParentAgentName() {
    return parentAgentName;
  }
  public void setParentAgentName(String parentAgentName) {
    this.parentAgentName = parentAgentName;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getProvince() {
    return province;
  }
  public void setProvince(String province) {
    this.province = province;
  }
  public String getDistrict() {
    return district;
  }
  public void setDistrict(String district) {
    this.district = district;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  public Date getStartCallDatetime() {
    return startCallDatetime;
  }
  public void setStartCallDatetime(Date startCallDatetime) {
    this.startCallDatetime = startCallDatetime;
  }

}
