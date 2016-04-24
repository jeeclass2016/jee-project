package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;

/**
 * DTO dùng cho các thao tác với đại lý.
 *
 * @author lam.lethanh
 *         Copyright © 2014 HTC
 *         $LastChangedRevision: $
 *         $LastChangedDate: $
 */
public class AgentDTO extends BaseDTO implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = -5415176756264413349L;

  // Số thuê bao
  private String msisdn;

  // Tên thương mại
  private String tradeName;

  // Chủ sở hữu
  private String ownerName;

  // ICCID
  private String iccid;

  private String agentStatus;

  /**
   * Default constructor.
   */
  public AgentDTO() {

  }

  /**
   * @param msisdn
   * @param tradeName
   * @param ownerName
   * @param iccid
   */
  public AgentDTO(String msisdn, String tradeName, String ownerName, String iccid) {
    this.msisdn = msisdn;
    this.tradeName = tradeName;
    this.ownerName = ownerName;
    this.iccid = iccid;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
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

  public String getIccid() {
    return iccid;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public String getAgentStatus() {
    return agentStatus;
  }

  public void setAgentStatus(String agentStatus) {
    this.agentStatus = agentStatus;
  }

  /* (non-Javadoc)
  * @see java.lang.Object#toString()
  */
  @Override
  public String toString() {
    StringBuilder searchContent = new StringBuilder();
    if (msisdn != null) {
      searchContent.append("/msisdn:").append(msisdn);
    }
    if (tradeName != null) {
      searchContent.append("/tradeName:").append(tradeName);
    }
    if (ownerName != null) {
      searchContent.append("/ownerName:").append(ownerName);
    }
    if (iccid != null) {
      searchContent.append("/iccid:").append(iccid);
    }
    if (agentStatus != null) {
      searchContent.append("/agentStatus:").append(agentStatus);
    }
    return searchContent.toString();
  }
}
