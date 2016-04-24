package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;

public class LoadedSIMDTO extends BaseDTO implements Serializable {
  private String msisdn;

  private String newICCID;

  private String status;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getNewICCID() {
    return newICCID;
  }

  public void setNewICCID(String newICCID) {
    this.newICCID = newICCID;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "LoadedSIMDTO{" +
            "msisdn='" + msisdn + '\'' +
            ", newICCID='" + newICCID + '\'' +
            ", status='" + status + '\'' +
            '}';
  }
}
