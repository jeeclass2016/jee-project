package com.vietsci.cms.frontend.etopup.model;

import com.vietsci.cms.frontend.etopup.common.util.Constants;

/** 
 * Lop the hien thong tin Ly do.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class Reason implements java.io.Serializable {

  
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -6165060896392000616L;
  
  private Long reasonId;
  private String code;
  private String reasonDescribe;
  private Boolean status;
  private String statusInfo;
  private Boolean delStatus;
  private String statusNumberValue;
  
  public Reason() {
  }

  public Reason(Long reasonId, String code) {
    this.reasonId = reasonId;
    this.code = code;
  }

  public Reason(Long reasonId, String code, String reasonDescribe, Boolean status) {
    this.reasonId = reasonId;
    this.code = code;
    this.reasonDescribe = reasonDescribe;
    this.status = status;
  }

  public Long getReasonId() {
    return this.reasonId;
  }

  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getReasonDescribe() {
    return this.reasonDescribe;
  }

  public void setReasonDescribe(String reasonDescribe) {
    this.reasonDescribe = reasonDescribe;
  }

  public Boolean getStatus() {
    return this.status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
  
  public Boolean getDelStatus() {
    return this.delStatus;
  }

  public void setDelStatus(Boolean delStatus) {
    this.delStatus = delStatus;
  }

  /**
   * @return the statusInfo
   */
  public String getStatusInfo() {
    if (status != null && status) {
      statusInfo = Constants.ReasonManagement.ACTIVE_STATUS_LABEL;
    } else {
    	statusInfo = Constants.ReasonManagement.INACTIVE_STATUS_LABEL;
    }
    return statusInfo;
  }

  /**
   * @param statusInfo the statusInfo to set
   */
  public void setStatusInfo(String statusInfo) {
    this.statusInfo = statusInfo;
  }

  /**
   * @return the statusNumberValue
   */
  public String getStatusNumberValue() {
    return statusNumberValue;
  }

  /**
   * @param statusNumberValue the statusNumberValue to set
   */
  public void setStatusNumberValue(String statusNumberValue) {
    this.statusNumberValue = statusNumberValue;
  }

  public static enum ReasonCode {
    
    ICCID_MSISDN_CHANGE("SUBS_CHG", "ICCID_MSISDN_CHANGE");
    
    private String value;
    private String name;
    
    private ReasonCode(String value, String name) {
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
