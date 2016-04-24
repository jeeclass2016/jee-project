package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;

/** 
 * Model object for district information.
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class TblMapDistrict implements Serializable {
  
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 235106709005089460L;
  
  private String districtId;
  private String district;
  private String provinceId;
  private String province;
  
  public TblMapDistrict() {
  }

  public TblMapDistrict(String districtId) {
    this.districtId = districtId;
  }

  public String getDistrictId() {
    return districtId;
  }

  public void setDistrictId(String districtId) {
    this.districtId = districtId;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(String provinceId) {
    this.provinceId = provinceId;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (districtId != null ? districtId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not
    // set
    if (!(object instanceof TblMapDistrict)) {
      return false;
    }
    TblMapDistrict other = (TblMapDistrict) object;
    if ((this.districtId == null && other.districtId != null)
        || (this.districtId != null && !this.districtId.equals(other.districtId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.htc.epos.api.etopup.model.TblMapDistrict[ districtId=" + districtId + " ]";
  }

}
