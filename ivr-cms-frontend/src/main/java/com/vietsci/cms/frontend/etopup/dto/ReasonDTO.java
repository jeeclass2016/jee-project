package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;

/** 
 * DTO dung cho viec tra cuu danh muc ly do.
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class ReasonDTO extends BaseDTO implements Serializable {

  /**
   * Serial Version UID. 
   */
  private static final long serialVersionUID = -2877257022558050477L;
  
  private Long reasonId;
  private String code;
  private String reasonDescribe;
  private Boolean status;
  
  private String statusNumberValue;
  
  /**
  * Default constructor. 
  */
  public ReasonDTO() {
  }
  
  /**
  * @param code
  * @param reasonDescribe
  * @param status
  */
  public ReasonDTO(String code, String reasonDescribe, Boolean status) {
    this.code = code;
    this.reasonDescribe = reasonDescribe;
    this.status = status;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }
  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }
  /**
   * @return the reasonDescribe
   */
  public String getReasonDescribe() {
    return reasonDescribe;
  }
  /**
   * @param reasonDescribe the reasonDescribe to set
   */
  public void setReasonDescribe(String reasonDescribe) {
    this.reasonDescribe = reasonDescribe;
  }
  /**
   * @return the status
   */
  public Boolean getStatus() {
    return status;
  }
  /**
   * @param status the status to set
   */
  public void setStatus(Boolean status) {
    this.status = status;
  }

  /**
   * @return the reasonId
   */
  public Long getReasonId() {
    return reasonId;
  }

  /**
   * @param reasonId the reasonId to set
   */
  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
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

  /* (non-Javadoc)
  * @see java.lang.Object#toString()
  */
  @Override
  public String toString() {
    StringBuilder searchContent = new StringBuilder();
    if (code != null) {
      searchContent.append("/code:").append(code);
    }
    if (reasonDescribe != null) {
      searchContent.append("/reasonDesc:").append(reasonDescribe);
    }
    if (status != null) {
      searchContent.append("/status:").append(status);
    }
    return searchContent.toString();
  }
}