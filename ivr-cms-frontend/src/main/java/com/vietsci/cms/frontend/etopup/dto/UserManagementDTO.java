package com.vietsci.cms.frontend.etopup.dto;

public class UserManagementDTO extends BaseDTO implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -9150771981735022567L;

  private Long userId;
  private String userName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
