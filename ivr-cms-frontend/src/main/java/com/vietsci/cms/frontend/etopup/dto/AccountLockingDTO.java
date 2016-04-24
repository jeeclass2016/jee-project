package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO class for Account Locking
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public class AccountLockingDTO extends BaseDTO implements Serializable {

  private String sendNumber;

  private String receivedNumber;

  private Date startDate;

  private Date endDate;

  private BigDecimal transAmount;

  private long userId;

  private String transType;

  public AccountLockingDTO() {
  }

  public AccountLockingDTO(String sendNumber, String receivedNumber, Date startDate, Date endDate) {
    this.sendNumber = sendNumber;
    this.receivedNumber = receivedNumber;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getSendNumber() {
    return sendNumber;
  }

  public void setSendNumber(String sendNumber) {
    this.sendNumber = sendNumber;
  }

  public String getReceivedNumber() {
    return receivedNumber;
  }

  public void setReceivedNumber(String receivedNumber) {
    this.receivedNumber = receivedNumber;
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

  public BigDecimal getTransAmount() {
    return transAmount;
  }

  public void setTransAmount(BigDecimal transAmount) {
    this.transAmount = transAmount;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getTransType() {
    return transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
  }
}
