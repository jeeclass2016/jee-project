package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author NguyenPV
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class AgentAddressManagement implements Serializable {

  private static final long serialVersionUID = -1178951582653703532L;
  private Long agentAddressId;
  
  private Agent agent;
  
  private String region;
  private String province;
  private String district;
  private String status;
  private String msisdn;
  private Date createDate;
  private Date startCallDatetime;

  public AgentAddressManagement() {
  }

  public AgentAddressManagement(Long agentAddressId) {
    this.agentAddressId = agentAddressId;
  }

  /*public AgentAddressManagement(Long agentAddressId, Long agentId, String msisdn) {
    this.agentAddressId = agentAddressId;
    this.agentId = agentId;
    this.msisdn = msisdn;
  }*/

  public Long getAgentAddressId() {
    return agentAddressId;
  }

  public void setAgentAddressId(Long agentAddressId) {
    this.agentAddressId = agentAddressId;
  }

  /*public Long getAgentId() {
    return agentId;
  }

  public void setAgentId(Long agentId) {
    this.agentId = agentId;
  }*/

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
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

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (agentAddressId != null ? agentAddressId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not
    // set
    if (this.agentAddressId == null) {
      return false;
    }
    if (!(object instanceof AgentAddressManagement)) {
      return false;
    }
    AgentAddressManagement other = (AgentAddressManagement) object;
    if ((this.agentAddressId == null && other.agentAddressId != null)
        || (this.agentAddressId != null && !this.agentAddressId.equals(other.agentAddressId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.htc.epos.api.etopup.model.AgentAddressManagement[ agentAddressId=" + agentAddressId + " ]";
  }

}
