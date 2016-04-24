package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/** 
 * DTO dung cho viec tra cuu thong tin giao dich.
 * @author trung.doduc
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class TransactionManagementDTO extends BaseDTO implements Serializable {
  
  /**
  * Serial Version UID. 
  */
  private static final long serialVersionUID = 6608313116800742891L;

  private Long transId;
  private String msisdnSent;
  private String msisdnReceived;
  private Date startDate;
  private Date endDate;
  private Date createDate;
  private Date lastModified;
  private String transType;
  private Long sourceAccountId;
  private Long targetAccountId;
  private Integer processStatus;
  
  public Long getTransId() {
    return transId;
  }
  public void setTransId(Long transId) {
    this.transId = transId;
  }
  public String getMsisdnSent() {
    return msisdnSent;
  }
  public void setMsisdnSent(String msisdnSent) {
    this.msisdnSent = msisdnSent;
  }
  public String getMsisdnReceived() {
    return msisdnReceived;
  }
  public void setMsisdnReceived(String msisdnReceived) {
    this.msisdnReceived = msisdnReceived;
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

  public Long getSourceAccountId() {
    return sourceAccountId;
  }

  public void setSourceAccountId(Long sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }
  public Long getTargetAccountId() {
    return targetAccountId;
  }
  public void setTargetAccountId(Long targetAccountId) {
    this.targetAccountId = targetAccountId;
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
  public Integer getProcessStatus() {
    return processStatus;
  }
  public void setProcessStatus(Integer processStatus) {
    this.processStatus = processStatus;
  }
}
