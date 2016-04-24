package com.vietsci.cms.frontend.main.model;

import java.io.Serializable;

/**
 * Member model
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 896806308727038278L;

	private String userName;
	private String fullName;
	private String role;
	private String companyId;

	public Member() {
		super();
	}

	public Member(String userName, String fullName, String role, String companyId) {
		super();
		this.role = role;
		this.userName = userName;
		this.fullName = fullName;
		this.companyId = companyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
