package com.vietsci.cms.frontend.etopup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class AgentAccount implements Serializable {

  private static final long serialVersionUID = -1557336186306320844L;
  private long accountId;
  private Agent agent;
  private Date createDate;
  private Date openDate;
  private BigDecimal openBalance;
  private BigDecimal currBalance;
  private BigDecimal availBalance;
  private BigDecimal realBalance;
  private BigDecimal threshold;
  private Integer status;

  public AgentAccount() {
  }

  public AgentAccount(long accountId, BigDecimal openBalance, BigDecimal currBalance, BigDecimal availBalance,
                      BigDecimal realBalance, BigDecimal threshold) {
    this.accountId = accountId;
    this.openBalance = openBalance;
    this.currBalance = currBalance;
    this.availBalance = availBalance;
    this.realBalance = realBalance;
    this.threshold = threshold;
  }

  public AgentAccount(long accountId, Agent agent, Date createDate, Date openDate, BigDecimal openBalance,
                      BigDecimal currBalance, BigDecimal availBalance, BigDecimal realBalance, BigDecimal threshold,
                      Integer status) {
    this.accountId = accountId;
    this.agent = agent;
    this.createDate = createDate;
    this.openDate = openDate;
    this.openBalance = openBalance;
    this.currBalance = currBalance;
    this.availBalance = availBalance;
    this.realBalance = realBalance;
    this.threshold = threshold;
    this.status = status;
  }

  public long getAccountId() {
    return this.accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  public Agent getAgent() {
    return this.agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getOpenDate() {
    return this.openDate;
  }

  public void setOpenDate(Date openDate) {
    this.openDate = openDate;
  }

  public BigDecimal getOpenBalance() {
    return this.openBalance;
  }

  public void setOpenBalance(BigDecimal openBalance) {
    this.openBalance = openBalance;
  }

  public BigDecimal getCurrBalance() {
    return this.currBalance;
  }

  public void setCurrBalance(BigDecimal currBalance) {
    this.currBalance = currBalance;
  }

  public BigDecimal getAvailBalance() {
    return this.availBalance;
  }

  public void setAvailBalance(BigDecimal availBalance) {
    this.availBalance = availBalance;
  }

  public BigDecimal getRealBalance() {
    return this.realBalance;
  }

  public void setRealBalance(BigDecimal realBalance) {
    this.realBalance = realBalance;
  }

  public BigDecimal getThreshold() {
    return this.threshold;
  }

  public void setThreshold(BigDecimal threshold) {
    this.threshold = threshold;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public static enum Status {
    ACTIVE(1, "Hoạt động"),
    TEMPORAL_LOCKED(2, "Tạm khóa");

    private int value;
    private String name;


    private Status(int value, String name) {
      this.value = value;
      this.name = name;
    }

    public int getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }

}
