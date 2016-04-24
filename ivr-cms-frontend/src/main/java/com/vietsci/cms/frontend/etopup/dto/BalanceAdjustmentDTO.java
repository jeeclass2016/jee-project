package com.vietsci.cms.frontend.etopup.dto;

import com.vietsci.cms.frontend.etopup.model.BalanceAdjustmentType;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceAdjustmentDTO extends BaseDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -2105666778526446874L;

  private String receivedAccount;

  private BigDecimal amountOfMoney;

  private int adjustmentType;

  private String reason;

  private String referenceNumber;

  private String note;

  private long staffId;

  private String userName;

  public BalanceAdjustmentDTO() {
    this.amountOfMoney = new BigDecimal(0);
    this.adjustmentType = BalanceAdjustmentType.POSITIVE.getValue();
  }

  public BalanceAdjustmentDTO(String receivedAccount, BigDecimal amountOfMoney, int adjustmentType, String reason,
                              String referenceNumber, String note, long staffId, String userName) {
    this.receivedAccount = receivedAccount;
    this.amountOfMoney = amountOfMoney;
    this.adjustmentType = adjustmentType;
    this.reason = reason;
    this.referenceNumber = referenceNumber;
    this.note = note;
    this.staffId = staffId;
    this.userName = userName;
  }

  public String getReceivedAccount() {
    return receivedAccount;
  }

  public void setReceivedAccount(String receivedAccount) {
    this.receivedAccount = receivedAccount;
  }

  public BigDecimal getAmountOfMoney() {
    return amountOfMoney;
  }

  public void setAmountOfMoney(BigDecimal amountOfMoney) {
    this.amountOfMoney = amountOfMoney;
  }

  public int getAdjustmentType() {
    return adjustmentType;
  }

  public void setAdjustmentType(int adjustmentType) {
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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public long getStaffId() {
    return staffId;
  }

  public void setStaffId(long staffId) {
    this.staffId = staffId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
