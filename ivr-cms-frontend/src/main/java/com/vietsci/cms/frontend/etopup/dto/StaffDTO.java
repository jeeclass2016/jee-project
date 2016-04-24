package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffDTO implements Serializable {
  private static final long serialVersionUID = -8560361895146483835L;
  private Long staffId;
  private String staffCode;
  private String name;
  private String status;
  private String resourceCode;
  private Set<ShopStaffDTO> shopStaffs = new HashSet<ShopStaffDTO>(0);
  
  public StaffDTO() {
    super();
  }
  
  public StaffDTO(Long staffId, String staffCode, String name, String status, String resourceCode,
      Set<ShopStaffDTO> shopStaffs) {
    super();
    this.staffId = staffId;
    this.staffCode = staffCode;
    this.name = name;
    this.status = status;
    this.resourceCode = resourceCode;
    this.shopStaffs = shopStaffs;
  }

  public Long getStaffId() {
    return staffId;
  }
  public void setStaffId(Long staffId) {
    this.staffId = staffId;
  }
  public String getStaffCode() {
    return staffCode;
  }
  public void setStaffCode(String staffCode) {
    this.staffCode = staffCode;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getResourceCode() {
    return resourceCode;
  }
  public void setResourceCode(String resourceCode) {
    this.resourceCode = resourceCode;
  }
  public Set<ShopStaffDTO> getShopStaffs() {
    return shopStaffs;
  }
  public void setShopStaffs(Set<ShopStaffDTO> shopStaffs) {
    this.shopStaffs = shopStaffs;
  }
}
