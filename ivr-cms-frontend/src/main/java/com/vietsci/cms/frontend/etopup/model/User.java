package com.vietsci.cms.frontend.etopup.model;

import java.util.Date;

public class User implements java.io.Serializable {

  /**
  * 
  */
  private static final long serialVersionUID = -4193589083882798319L;

  private Long userId;
  private String userName;
  private String password;
  private String email;
  private Date createdDate;
  private Date lastChangedPassword;
  private Date failedPasswordDate;
  private Integer failedPasswordCount;
  private Date lastBlockDate;
  private Date lastLoginDate;
  private String fullName;
  private Date birthday;
  private String address;
  private String mobilePhone;
  private String company;
  private String fax;
  private Date lastUpdatedDate;
  private Date expiryDate;
  private Integer passwordExpiryStatus;
  private Integer pirority;
  private Integer status;
  private Integer deleteFlag;
  private Integer gender;
  private Long userCreated;
  
  public User() {
  }
  
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public Date getLastChangedPassword() {
    return lastChangedPassword;
  }
  public void setLastChangedPassword(Date lastChangedPassword) {
    this.lastChangedPassword = lastChangedPassword;
  }
  public Date getFailedPasswordDate() {
    return failedPasswordDate;
  }
  public void setFailedPasswordDate(Date failedPasswordDate) {
    this.failedPasswordDate = failedPasswordDate;
  }
  public Integer getFailedPasswordCount() {
    return failedPasswordCount;
  }
  public void setFailedPasswordCount(Integer failedPasswordCount) {
    this.failedPasswordCount = failedPasswordCount;
  }
  public Date getLastBlockDate() {
    return lastBlockDate;
  }
  public void setLastBlockDate(Date lastBlockDate) {
    this.lastBlockDate = lastBlockDate;
  }
  public Date getLastLoginDate() {
    return lastLoginDate;
  }
  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getMobilePhone() {
    return mobilePhone;
  }
  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }
  public String getFax() {
    return fax;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }
  public Date getLastUpdatedDate() {
    return lastUpdatedDate;
  }
  public void setLastUpdatedDate(Date lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }
  public Date getExpiryDate() {
    return expiryDate;
  }
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
  public Integer getPasswordExpiryStatus() {
    return passwordExpiryStatus;
  }
  public void setPasswordExpiryStatus(Integer passwordExpiryStatus) {
    this.passwordExpiryStatus = passwordExpiryStatus;
  }
  public Integer getPirority() {
    return pirority;
  }
  public void setPirority(Integer pirority) {
    this.pirority = pirority;
  }
  public Integer getStatus() {
    return status;
  }
  public void setStatus(Integer status) {
    this.status = status;
  }
  public Integer getDeleteFlag() {
    return deleteFlag;
  }
  public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
  public Integer getGender() {
    return gender;
  }
  public void setGender(Integer gender) {
    this.gender = gender;
  }
  public Long getUserCreated() {
    return userCreated;
  }
  public void setUserCreated(Long userCreated) {
    this.userCreated = userCreated;
  }
  
}
