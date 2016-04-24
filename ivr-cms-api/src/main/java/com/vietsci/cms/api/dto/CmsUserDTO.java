package com.vietsci.cms.api.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CmsUserDTO extends BaseDTO implements java.io.Serializable {

	/**
   * 
   */
	private static final long serialVersionUID = -9150771981735022567L;

	private Long userId;
	private String userName;
	private String password;
	private BigDecimal role;
	private String mobile;
	private String email;
	private BigDecimal loginCount;
	private String activeStatus;
	private String createdBy;
	private Date createdDate;

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
	public BigDecimal getRole() {
		return role;
	}
	public void setRole(BigDecimal role) {
		this.role = role;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(BigDecimal loginCount) {
		this.loginCount = loginCount;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
