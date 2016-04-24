package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for Etopup transaction look up (Tra cuu giao dich ETOPUP)
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public class EtopupTransLookupDTO extends BaseDTO implements Serializable {

  private Date startDate;

  private Date endDate;

  private String transType;

  private String msisdnSource;

  private String msisdnTarget;

  public EtopupTransLookupDTO() {
    transType = "";
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getTransType() {
    return transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
  }

  public String getMsisdnSource() {
    return msisdnSource;
  }

  public void setMsisdnSource(String msisdnSource) {
    this.msisdnSource = msisdnSource;
  }

  public String getMsisdnTarget() {
    return msisdnTarget;
  }

  public void setMsisdnTarget(String msisdnTarget) {
    this.msisdnTarget = msisdnTarget;
  }
}
