package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.List;

import com.vietsci.cms.frontend.etopup.model.Agent;


/** 
 * DTO dung cho khuyen mai theo lo.
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: 7114 $
 * $LastChangedDate: 2014-04-10 21:17:01 -0700 (Th 5, 10 thg 4 2014) $  
 */
public class BatchPromotionDTO extends BaseDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7421154716639354921L;
  
  private List<String> targetMsisdnList;
  
  private List<Agent> targetAgentList;
  
  private List<Double> transferedAmountList;
  
  private String promotionCode;
  
  private long staffId;

  public List<String> getTargetMsisdnList() {
    return targetMsisdnList;
  }

  public void setTargetMsisdnList(List<String> targetMsisdnList) {
    this.targetMsisdnList = targetMsisdnList;
  }

  public List<Double> getTransferedAmountList() {
    return transferedAmountList;
  }

  public void setTransferedAmountList(List<Double> transferedAmountList) {
    this.transferedAmountList = transferedAmountList;
  }

  public String getPromotionCode() {
    return promotionCode;
  }

  public void setPromotionCode(String promotionCode) {
    this.promotionCode = promotionCode;
  }

  public List<Agent> getTargetAgentList() {
    return targetAgentList;
  }

  public void setTargetAgentList(List<Agent> targetAgentList) {
    this.targetAgentList = targetAgentList;
  }

  public long getStaffId() {
    return staffId;
  }

  public void setStaffId(long staffId) {
    this.staffId = staffId;
  }
}