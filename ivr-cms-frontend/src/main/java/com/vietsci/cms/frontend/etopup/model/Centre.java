package com.vietsci.cms.frontend.etopup.model;


public class Centre implements java.io.Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 4485719594166122207L;

  
  private Long centreId;
  private String name;
  private String code;
  private Integer status;
  private Integer delStatus;
  public Long getCentreId() {
    return centreId;
  }
  public void setCentreId(Long centreId) {
    this.centreId = centreId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public Integer getStatus() {
    return status;
  }
  public void setStatus(Integer status) {
    this.status = status;
  }
  public Integer getDelStatus() {
    return delStatus;
  }
  public void setDelStatus(Integer delStatus) {
    this.delStatus = delStatus;
  }
  

  
}
