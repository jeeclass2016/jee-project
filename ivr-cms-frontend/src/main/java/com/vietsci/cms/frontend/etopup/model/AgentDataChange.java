package com.vietsci.cms.frontend.etopup.model;

import java.util.Date;


public class AgentDataChange implements java.io.Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 3456424391321854908L;
  
  private String fieldName;
  private String oldValue;
  private String newValue;
  private Date actionDate;
  private String user;
  private String ipAddress;
  
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
  public String getOldValue() {
    return oldValue;
  }
  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }
  public String getNewValue() {
    return newValue;
  }
  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }
  public Date getActionDate() {
    return actionDate;
  }
  public void setActionDate(Date actionDate) {
    this.actionDate = actionDate;
  }
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }
  public String getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  
  
}
