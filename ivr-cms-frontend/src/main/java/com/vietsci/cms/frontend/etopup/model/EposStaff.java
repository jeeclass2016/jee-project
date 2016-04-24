package com.vietsci.cms.frontend.etopup.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class EposStaff {

  private long staffId;
  private String staffCode;
  private String name;
  private String status;
  private Date birthday;
  private String idNo;
  private String idIssuePlace;
  private Date idIssueDate;
  private String tel;
  private String resourceCode;

  public EposStaff() {
  }

  public EposStaff(long staffId, String staffCode, String name, String status) {
    this.staffId = staffId;
    this.staffCode = staffCode;
    this.name = name;
    this.status = status;
  }

  public EposStaff(long staffId, String staffCode, String name, String status, Date birthday, String idNo,
      String idIssuePlace, Date idIssueDate, String tel, String resourceCode) {
    this.staffId = staffId;
    this.staffCode = staffCode;
    this.name = name;
    this.status = status;
    this.birthday = birthday;
    this.idNo = idNo;
    this.idIssuePlace = idIssuePlace;
    this.idIssueDate = idIssueDate;
    this.tel = tel;
    this.resourceCode = resourceCode;
  }

  public long getStaffId() {
    return this.staffId;
  }

  public void setStaffId(long staffId) {
    this.staffId = staffId;
  }

  public String getStaffCode() {
    return this.staffCode;
  }

  public void setStaffCode(String staffCode) {
    this.staffCode = staffCode;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getIdNo() {
    return this.idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getIdIssuePlace() {
    return this.idIssuePlace;
  }

  public void setIdIssuePlace(String idIssuePlace) {
    this.idIssuePlace = idIssuePlace;
  }

  public Date getIdIssueDate() {
    return this.idIssueDate;
  }

  public void setIdIssueDate(Date idIssueDate) {
    this.idIssueDate = idIssueDate;
  }

  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getResourceCode() {
    return this.resourceCode;
  }

  public void setResourceCode(String resourceCode) {
    this.resourceCode = resourceCode;
  }

}
