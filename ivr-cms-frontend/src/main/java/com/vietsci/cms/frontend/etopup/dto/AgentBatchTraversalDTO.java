package com.vietsci.cms.frontend.etopup.dto;

/**
 * DTO class for Agent Batch Traversal
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

public class AgentBatchTraversalDTO {

  private String msisdn;

  private String tradeName;

  private String sapCode;

  private String outletAddress;

  private String agentStatus;

  private String error;

  private boolean valid;

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

  public String getSapCode() {
    return sapCode;
  }

  public void setSapCode(String sapCode) {
    this.sapCode = sapCode;
  }

  public String getOutletAddress() {
    return outletAddress;
  }

  public void setOutletAddress(String outletAddress) {
    this.outletAddress = outletAddress;
  }

  public String getAgentStatus() {
    return agentStatus;
  }

  public void setAgentStatus(String agentStatus) {
    this.agentStatus = agentStatus;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }
}
