package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceAdjustmentResultData implements Serializable{

  private String msisdn;
  private String balanceNumber;
  private String status;
  private String balanceResult;

  public BalanceAdjustmentResultData() {
  }

  public BalanceAdjustmentResultData(String msisdn, String balanceNumber, String status, String balanceResult) {
    this.msisdn = msisdn;
    this.balanceNumber = balanceNumber;
    this.status = status;
    this.balanceResult = balanceResult;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getBalanceNumber() {
    return balanceNumber;
  }

  public void setBalanceNumber(String balanceNumber) {
    this.balanceNumber = balanceNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getBalanceResult() {
    return balanceResult;
  }

  public void setBalanceResult(String balanceResult) {
    this.balanceResult = balanceResult;
  }

  @Override
  public String toString() {
    return "BalanceAdjustmentResultData{" +
            "msisdn='" + msisdn + '\'' +
            ", balanceNumber=" + balanceNumber +
            ", status='" + status + '\'' +
            ", balanceResult=" + balanceResult +
            '}';
  }
}
