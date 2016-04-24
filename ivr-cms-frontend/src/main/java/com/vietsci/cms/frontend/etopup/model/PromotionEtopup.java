package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PromotionEtopup implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -845965342858760205L;

  private BigDecimal id;
  private String code;
  private String description;
  private Date staDate;
  private Date endDate;
  private String status;
  private Boolean delStatus;

  public PromotionEtopup() {
  }

  public PromotionEtopup(BigDecimal id, String code, String description) {
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public PromotionEtopup(BigDecimal id, String code, String description, Date staDate, Date endDate, String status,
                         Boolean delStatus) {
    this.id = id;
    this.code = code;
    this.description = description;
    this.staDate = staDate;
    this.endDate = endDate;
    this.status = status;
    this.delStatus = delStatus;
  }

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Boolean getDelStatus() {
    return delStatus;
  }

  public void setDelStatus(Boolean delStatus) {
    this.delStatus = delStatus;
  }

}
