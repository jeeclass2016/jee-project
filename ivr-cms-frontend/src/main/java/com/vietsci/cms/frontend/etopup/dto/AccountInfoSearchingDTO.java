package com.vietsci.cms.frontend.etopup.dto;

public class AccountInfoSearchingDTO {

  private String msisdn;

  public AccountInfoSearchingDTO() {

  }

  public AccountInfoSearchingDTO(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }
}
