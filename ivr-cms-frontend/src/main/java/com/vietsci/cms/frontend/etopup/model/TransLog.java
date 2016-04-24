package com.vietsci.cms.frontend.etopup.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class TransLog implements java.io.Serializable {

  private static final long serialVersionUID = 4216425039878376227L;
  private Long logId;
  private Trans trans;
  private Date logDatetime;
  private Long sessionId;
  private String resultCode;
  private Integer recordStatus;
  private String sourceNumber;
  private String targetNumber;
  private String data;
  private String keyword;
  private BigDecimal transAmount;
  private String recordType;
  private Long channelId;
  private Long userId;

  public TransLog() {
  }

  public Long getLogId() {
    return this.logId;
  }

  public void setLogId(Long logId) {
    this.logId = logId;
  }

  public Trans getTrans() {
    return this.trans;
  }

  public void setTrans(Trans trans) {
    this.trans = trans;
  }

  public Date getLogDatetime() {
    return this.logDatetime;
  }

  public void setLogDatetime(Date logDatetime) {
    this.logDatetime = logDatetime;
  }

  public Long getSessionId() {
    return this.sessionId;
  }

  public void setSessionId(Long sessionId) {
    this.sessionId = sessionId;
  }

  public String getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public Integer getRecordStatus() {
    return this.recordStatus;
  }

  public void setRecordStatus(Integer recordStatus) {
    this.recordStatus = recordStatus;
  }

  public String getSourceNumber() {
    return this.sourceNumber;
  }

  public void setSourceNumber(String sourceNumber) {
    this.sourceNumber = sourceNumber;
  }

  public String getTargetNumber() {
    return this.targetNumber;
  }

  public void setTargetNumber(String targetNumber) {
    this.targetNumber = targetNumber;
  }

  public String getData() {
    return this.data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getKeyword() {
    return this.keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public BigDecimal getTransAmount() {
    return this.transAmount;
  }

  public void setTransAmount(BigDecimal transAmount) {
    this.transAmount = transAmount;
  }

  public String getRecordType() {
    return this.recordType;
  }

  public void setRecordType(String recordType) {
    this.recordType = recordType;
  }

  public Long getChannelId() {
    return this.channelId;
  }

  public void setChannelId(Long channelId) {
    this.channelId = channelId;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public static enum ResultCode {
    SUCCESS("200", "Thành công"),
    AGENT_NOT_EXIST("400", "Đại lý không tồn tại"),
    BALANCE_NOT_ENOUGH("401", "Tài khoản không đủ để thực hiện giao dịch");

    private String value;
    private String name;


    private ResultCode(String value, String name) {
      this.value = value;
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }

}
