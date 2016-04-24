package com.vietsci.cms.frontend.etopup.dto;


import java.io.Serializable;
import java.util.Date;

public class PromotionDTO extends BaseDTO implements Serializable {


  private static final long serialVersionUID = -5415176756264413349L;

  private String code;

  private String description;

  private Date staDate;

  private Date endDate;

  public PromotionDTO() {

  }

  public PromotionDTO(String code, String description, Date staDate, Date endDate) {
    this.code = code;
    this.description = description;
    this.staDate = staDate;
    this.endDate = endDate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStaDate() {
    return staDate;
  }

  public void setStaDate(Date staDate) {
    this.staDate = staDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}

