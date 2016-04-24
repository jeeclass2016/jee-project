package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class BalanceAdjustmentBatchDTO extends BaseDTO implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 673068154529322604L;


  private Map<String, BigDecimal> agentBalance;

  private Integer adjustmentType;

  private String reason;

  private String referenceNumber;

  private Long staffId;

  private String userName;

  public Map<String, BigDecimal> getAgentBalance() {
    return agentBalance;
  }

  public void setAgentBalance(Map<String, BigDecimal> agentBalance) {
    this.agentBalance = agentBalance;
  }

  public Integer getAdjustmentType() {
    return adjustmentType;
  }

  public void setAdjustmentType(Integer adjustmentType) {
    this.adjustmentType = adjustmentType;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public Long getStaffId() {
    return staffId;
  }

  public void setStaffId(Long staffId) {
    this.staffId = staffId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
