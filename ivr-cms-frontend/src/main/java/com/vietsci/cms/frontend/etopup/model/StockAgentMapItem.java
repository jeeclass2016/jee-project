package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.vietsci.cms.frontend.etopup.common.util.Constants;

public class StockAgentMapItem implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8309499997962870716L;

  private String msisdn;
  private String iccid;
  private String shopCode;
  private String staffCode;
  private String errorMessage;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getIccid() {
    return iccid;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public String getShopCode() {
    return shopCode;
  }

  public void setShopCode(String shopCode) {
    this.shopCode = shopCode;
  }

  public String getStaffCode() {
    return staffCode;
  }

  public void setStaffCode(String staffCode) {
    this.staffCode = staffCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String toString() {
    String basicStr = msisdn + Constants.FileManagement.COMMA_DELIMITER + iccid
        + Constants.FileManagement.COMMA_DELIMITER + shopCode;
    if (StringUtils.isNotBlank(staffCode))
      basicStr += Constants.FileManagement.COMMA_DELIMITER + staffCode;
    return basicStr;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((iccid == null) ? 0 : iccid.hashCode());
    result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
    result = prime * result + ((shopCode == null) ? 0 : shopCode.hashCode());
    result = prime * result + ((staffCode == null) ? 0 : staffCode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    StockAgentMapItem other = (StockAgentMapItem) obj;
    if (iccid == null) {
      if (other.iccid != null)
        return false;
    } else if (!iccid.equals(other.iccid))
      return false;
    if (msisdn == null) {
      if (other.msisdn != null)
        return false;
    } else if (!msisdn.equals(other.msisdn))
      return false;
    if (shopCode == null) {
      if (other.shopCode != null)
        return false;
    } else if (!shopCode.equals(other.shopCode))
      return false;
    if (staffCode == null) {
      if (other.staffCode != null)
        return false;
    } else if (!staffCode.equals(other.staffCode))
      return false;
    return true;
  }

}
