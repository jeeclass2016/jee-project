/**
 * 
 */
package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CuongPV
 *
 */
public class UserDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8807571090962486767L;
  
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
  private Integer gender;
  private Long userCreated;
  private String userCreatedName;
  private Long groupId;
  private Long groupIdOld;
  
  /**
   * 
   */
  public UserDTO() {
    super();
  }
  /**
   * @param userId
   * @param userName
   * @param password
   * @param email
   * @param createdDate
   * @param lastChangedPassword
   * @param failedPasswordDate
   * @param failedPasswordCount
   * @param lastBlockDate
   * @param lastLoginDate
   * @param fullName
   * @param birthday
   * @param address
   * @param mobilePhone
   * @param company
   * @param fax
   * @param lastUpdatedDate
   * @param expiryDate
   * @param passwordExpiryStatus
   * @param pirority
   * @param status
   * @param gender
   */
  public UserDTO(Long userId, String userName, String password, String email, Date createdDate,
      Date lastChangedPassword, Date failedPasswordDate, Integer failedPasswordCount, Date lastBlockDate,
      Date lastLoginDate, String fullName, Date birthday, String address, String mobilePhone, String company,
      String fax, Date lastUpdatedDate, Date expiryDate, Integer passwordExpiryStatus, Integer pirority,
      Integer status, Integer gender ,Long userCreated, String userCreatedName) {
    super();
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.createdDate = createdDate;
    this.lastChangedPassword = lastChangedPassword;
    this.failedPasswordDate = failedPasswordDate;
    this.failedPasswordCount = failedPasswordCount;
    this.lastBlockDate = lastBlockDate;
    this.lastLoginDate = lastLoginDate;
    this.fullName = fullName;
    this.birthday = birthday;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.company = company;
    this.fax = fax;
    this.lastUpdatedDate = lastUpdatedDate;
    this.expiryDate = expiryDate;
    this.passwordExpiryStatus = passwordExpiryStatus;
    this.pirority = pirority;
    this.status = status;
    this.gender = gender;
    this.userCreated = userCreated;
    this.userCreatedName = userCreatedName;
  }
  
  public UserDTO(Long userId, String userName, String password, String email, Date createdDate,
      Date lastChangedPassword, Date failedPasswordDate, Integer failedPasswordCount, Date lastBlockDate,
      Date lastLoginDate, String fullName, Date birthday, String address, String mobilePhone, String company,
      String fax, Date lastUpdatedDate, Date expiryDate, Integer passwordExpiryStatus, Integer pirority,
      Integer status, Integer gender ,Long userCreated, String userCreatedName, Long groupId) {
    super();
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.createdDate = createdDate;
    this.lastChangedPassword = lastChangedPassword;
    this.failedPasswordDate = failedPasswordDate;
    this.failedPasswordCount = failedPasswordCount;
    this.lastBlockDate = lastBlockDate;
    this.lastLoginDate = lastLoginDate;
    this.fullName = fullName;
    this.birthday = birthday;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.company = company;
    this.fax = fax;
    this.lastUpdatedDate = lastUpdatedDate;
    this.expiryDate = expiryDate;
    this.passwordExpiryStatus = passwordExpiryStatus;
    this.pirority = pirority;
    this.status = status;
    this.gender = gender;
    this.userCreated = userCreated;
    this.userCreatedName = userCreatedName;
    this.groupId = groupId;
    this.groupIdOld = groupId;
  }
  /**
   * Dung cho chuc nang hien thi danh sach nguoi dung trong group
   * 
   * @author hung.tranminh
   * @param userId
   * @param userName
   * @param createDate
   * @param userCreated
   * @param userCreatedName
   * @param status
   */
  public UserDTO(Long userId, String userName) {
    this.userId = userId;
    this.userName = userName;
  }
  
  public UserDTO(Long userId, String userName, String fullName, Integer status) {
    this.userId = userId;
    this.userName = userName;
    this.fullName = fullName;
    this.status = status;
  }
  
  public UserDTO(Long userId, String userName, String fullName, Integer status, Date birthday, String address, String mobilePhone, String company,
      String fax, Integer gender, Integer passwordExpiryStatus, String email, Integer pirority) {
    this.userId = userId;
    this.userName = userName;
    this.fullName = fullName;
    this.status = status;
    this.birthday = birthday;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.company = company;
    this.fax = fax;
    this.gender = gender;
    this.passwordExpiryStatus = passwordExpiryStatus;
    this.email = email;
    this.pirority = pirority;
  }
  
  /**
   * @return the userId
   */
  public Long getUserId() {
    return userId;
  }
  /**
   * @param userId the userId to set
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }
  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  /**
   * @return the lastChangedPassword
   */
  public Date getLastChangedPassword() {
    return lastChangedPassword;
  }
  /**
   * @param lastChangedPassword the lastChangedPassword to set
   */
  public void setLastChangedPassword(Date lastChangedPassword) {
    this.lastChangedPassword = lastChangedPassword;
  }
  /**
   * @return the failedPasswordDate
   */
  public Date getFailedPasswordDate() {
    return failedPasswordDate;
  }
  /**
   * @param failedPasswordDate the failedPasswordDate to set
   */
  public void setFailedPasswordDate(Date failedPasswordDate) {
    this.failedPasswordDate = failedPasswordDate;
  }
  /**
   * @return the failedPasswordCount
   */
  public Integer getFailedPasswordCount() {
    return failedPasswordCount;
  }
  /**
   * @param failedPasswordCount the failedPasswordCount to set
   */
  public void setFailedPasswordCount(Integer failedPasswordCount) {
    this.failedPasswordCount = failedPasswordCount;
  }
  /**
   * @return the lastBlockDate
   */
  public Date getLastBlockDate() {
    return lastBlockDate;
  }
  /**
   * @param lastBlockDate the lastBlockDate to set
   */
  public void setLastBlockDate(Date lastBlockDate) {
    this.lastBlockDate = lastBlockDate;
  }
  /**
   * @return the lastLoginDate
   */
  public Date getLastLoginDate() {
    return lastLoginDate;
  }
  /**
   * @param lastLoginDate the lastLoginDate to set
   */
  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }
  /**
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }
  /**
   * @param fullName the fullName to set
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  /**
   * @return the birthday
   */
  public Date getBirthday() {
    return birthday;
  }
  /**
   * @param birthday the birthday to set
   */
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }
  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }
  /**
   * @return the mobilePhone
   */
  public String getMobilePhone() {
    return mobilePhone;
  }
  /**
   * @param mobilePhone the mobilePhone to set
   */
  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
  /**
   * @return the company
   */
  public String getCompany() {
    return company;
  }
  /**
   * @param company the company to set
   */
  public void setCompany(String company) {
    this.company = company;
  }
  /**
   * @return the fax
   */
  public String getFax() {
    return fax;
  }
  /**
   * @param fax the fax to set
   */
  public void setFax(String fax) {
    this.fax = fax;
  }
  /**
   * @return the lastUpdatedDate
   */
  public Date getLastUpdatedDate() {
    return lastUpdatedDate;
  }
  /**
   * @param lastUpdatedDate the lastUpdatedDate to set
   */
  public void setLastUpdatedDate(Date lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }
  /**
   * @return the expiryDate
   */
  public Date getExpiryDate() {
    return expiryDate;
  }
  /**
   * @param expiryDate the expiryDate to set
   */
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
  /**
   * @return the passwordExpiryStatus
   */
  public Integer getPasswordExpiryStatus() {
    return passwordExpiryStatus;
  }
  /**
   * @param passwordExpiryStatus the passwordExpiryStatus to set
   */
  public void setPasswordExpiryStatus(Integer passwordExpiryStatus) {
    this.passwordExpiryStatus = passwordExpiryStatus;
  }
  /**
   * @return the pirority
   */
  public Integer getPirority() {
    return pirority;
  }
  /**
   * @param pirority the pirority to set
   */
  public void setPirority(Integer pirority) {
    this.pirority = pirority;
  }
  /**
   * @return the status
   */
  public Integer getStatus() {
    return status;
  }
  /**
   * @param status the status to set
   */
  public void setStatus(Integer status) {
    this.status = status;
  }
  /**
   * @return the gender
   */
  public Integer getGender() {
    return gender;
  }
  /**
   * @param gender the gender to set
   */
  public void setGender(Integer gender) {
    this.gender = gender;
  }
  /**
   * @return the userCreated
   */
  public Long getUserCreated() {
    return userCreated;
  }
  /**
   * @param userCreated the userCreated to set
   */
  public void setUserCreated(Long userCreated) {
    this.userCreated = userCreated;
  }
  /**
   * @return the userCreatedName
   */
  public String getUserCreatedName() {
    return userCreatedName;
  }
  /**
   * @param userCreatedName the userCreatedName to set
   */
  public void setUserCreatedName(String userCreatedName) {
    this.userCreatedName = userCreatedName;
  }
  /**
   * @return the groupId
   */
  public Long getGroupId() {
    return groupId;
  }
  /**
   * @param groupId the groupId to set
   */
  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }
  public Long getGroupIdOld() {
    return groupIdOld;
  }
  public void setGroupIdOld(Long groupIdOld) {
    this.groupIdOld = groupIdOld;
  }
  
  
}
