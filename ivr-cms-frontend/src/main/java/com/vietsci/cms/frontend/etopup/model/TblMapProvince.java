package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;
import java.math.BigInteger;

/** 
 * Model object for province information.
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class TblMapProvince implements Serializable {
  
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -2957248745083574340L;
  
  private String provinceId;
  private String province;
  private String region;
  private BigInteger mccId;

  public TblMapProvince() {
  }

  public TblMapProvince(String provinceId) {
    this.provinceId = provinceId;
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

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public BigInteger getMccId() {
    return mccId;
  }

  public void setMccId(BigInteger mccId) {
    this.mccId = mccId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (provinceId != null ? provinceId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not
    // set
    if (!(object instanceof TblMapProvince)) {
      return false;
    }
    TblMapProvince other = (TblMapProvince) object;
    if ((this.provinceId == null && other.provinceId != null)
        || (this.provinceId != null && !this.provinceId.equals(other.provinceId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.htc.epos.api.etopup.model.TblMapProvince[ provinceId=" + provinceId + " ]";
  }

}
