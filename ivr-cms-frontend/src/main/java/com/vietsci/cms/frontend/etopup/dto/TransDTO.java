package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransDTO extends BaseDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5549882095723700920L;

  private String sourceMsisdn;

  private String targetMsisdn;

  private String sourceMpin;

  private BigDecimal transAmount;

  public String getSourceMsisdn() {
    return sourceMsisdn;
  }

  public void setSourceMsisdn(String sourceMsisdn) {
    this.sourceMsisdn = sourceMsisdn;
  }

  public String getTargetMsisdn() {
    return targetMsisdn;
  }

  public void setTargetMsisdn(String targetMsisdn) {
    this.targetMsisdn = targetMsisdn;
  }

  public String getSourceMpin() {
    return sourceMpin;
  }

  public void setSourceMpin(String sourceMpin) {
    this.sourceMpin = sourceMpin;
  }

  public BigDecimal getTransAmount() {
    return transAmount;
  }

  public void setTransAmount(BigDecimal transAmount) {
    this.transAmount = transAmount;
  }

}
