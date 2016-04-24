/**
 * 
 */
package com.vietsci.cms.frontend.etopup.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author NguyenPV
 *
 */
public class TLDTransDTO extends BaseDTO {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -6328378446486453498L;
  
  private Long creatorId;
  private String orderNumber;
  private Long centreId;
  private String lop;
  private Date lopDate;
  private Double commission;
  private String paymentMethod;
  private Long completorId;
  private Date contractDate;
  private String recipient;
  private String idNo;
  private Date dateIssue;
  private String placeIssue;
  private String contractNo;
  private String sourceMsisdn;
  private Long sourceAccountId;
  private String targetMsisdn;
  private Long targetAccountId;
  private String targetAgentTradeName;
  private BigDecimal targetCurBalance;
  private BigDecimal transAmount;
  private Long transId;
  
  public Long getCreatorId() {
    return creatorId;
  }
  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }
  public String getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }
  public Long getCentreId() {
    return centreId;
  }
  public void setCentreId(Long centreId) {
    this.centreId = centreId;
  }
  public String getLop() {
    return lop;
  }
  public void setLop(String lop) {
    this.lop = lop;
  }
  public Date getLopDate() {
    return lopDate;
  }
  public void setLopDate(Date lopDate) {
    this.lopDate = lopDate;
  }
  public Double getCommission() {
    return commission;
  }
  public void setCommission(Double commission) {
    this.commission = commission;
  }
  public String getPaymentMethod() {
    return paymentMethod;
  }
  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }
  public Long getCompletorId() {
    return completorId;
  }
  public void setCompletorId(Long completorId) {
    this.completorId = completorId;
  }
  public Date getContractDate() {
    return contractDate;
  }
  public void setContractDate(Date contractDate) {
    this.contractDate = contractDate;
  }
  public String getRecipient() {
    return recipient;
  }
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
  public String getIdNo() {
    return idNo;
  }
  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }
  public Date getDateIssue() {
    return dateIssue;
  }
  public void setDateIssue(Date dateIssue) {
    this.dateIssue = dateIssue;
  }
  public String getPlaceIssue() {
    return placeIssue;
  }
  public void setPlaceIssue(String placeIssue) {
    this.placeIssue = placeIssue;
  }
  public String getContractNo() {
    return contractNo;
  }
  public void setContractNo(String contractNo) {
    this.contractNo = contractNo;
  }
  public String getSourceMsisdn() {
    return sourceMsisdn;
  }
  public void setSourceMsisdn(String sourceMsisdn) {
    this.sourceMsisdn = sourceMsisdn;
  }
  public Long getSourceAccountId() {
    return sourceAccountId;
  }
  public void setSourceAccountId(Long sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }
  public String getTargetMsisdn() {
    return targetMsisdn;
  }
  public void setTargetMsisdn(String targetMsisdn) {
    this.targetMsisdn = targetMsisdn;
  }
  public Long getTargetAccountId() {
    return targetAccountId;
  }
  public void setTargetAccountId(Long targetAccountId) {
    this.targetAccountId = targetAccountId;
  }
  public BigDecimal getTransAmount() {
    return transAmount;
  }
  public void setTransAmount(BigDecimal transAmount) {
    this.transAmount = transAmount;
  }
  public BigDecimal getTargetCurBalance() {
    return targetCurBalance;
  }
  public void setTargetCurBalance(BigDecimal targetCurBalance) {
    this.targetCurBalance = targetCurBalance;
  }
  public Long getTransId() {
    return transId;
  }
  public void setTransId(Long transId) {
    this.transId = transId;
  }
  public String getTargetAgentTradeName() {
    return targetAgentTradeName;
  }
  public void setTargetAgentTradeName(String targetAgentTradeName) {
    this.targetAgentTradeName = targetAgentTradeName;
  }
  
}
