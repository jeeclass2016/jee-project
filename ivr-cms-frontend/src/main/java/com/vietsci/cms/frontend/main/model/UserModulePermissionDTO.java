package com.vietsci.cms.frontend.main.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class UserModulePermissionDTO implements Serializable {

  private static final long serialVersionUID = -282296347172270696L;

  private Long permissionId;
  private Long moduleId;
  private String moduleName;
  private Long userId;
  private Long createUsersId;
  private String userName;
  private Date createdDate;

  /**
   * 
   */
  public UserModulePermissionDTO() {
    super();
  }

  /**
   * @param permissionId
   * @param moduleId
   * @param moduleName
   * @param userId
   * @param createUsersId
   * @param userName
   * @param createdDate
   * @param type
   */
  public UserModulePermissionDTO(Long permissionId, Long moduleId, String moduleName, Long userId, Long createUsersId,
      String userName, Date createdDate) {
    super();
    this.permissionId = permissionId;
    this.moduleId = moduleId;
    this.moduleName = moduleName;
    this.userId = userId;
    this.createUsersId = createUsersId;
    this.userName = userName;
    this.createdDate = createdDate;
  }

  /**
   * @return the permissionId
   */
  public Long getPermissionId() {
    return permissionId;
  }

  /**
   * @param permissionId the permissionId to set
   */
  public void setPermissionId(Long permissionId) {
    this.permissionId = permissionId;
  }

  /**
   * @return the moduleId
   */
  public Long getModuleId() {
    return moduleId;
  }

  /**
   * @param moduleId the moduleId to set
   */
  public void setModuleId(Long moduleId) {
    this.moduleId = moduleId;
  }

  /**
   * @return the moduleName
   */
  public String getModuleName() {
    return moduleName;
  }

  /**
   * @param moduleName the moduleName to set
   */
  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
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
   * @return the createUsersId
   */
  public Long getCreateUsersId() {
    return createUsersId;
  }

  /**
   * @param createUsersId the createUsersId to set
   */
  public void setCreateUsersId(Long createUsersId) {
    this.createUsersId = createUsersId;
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
}
