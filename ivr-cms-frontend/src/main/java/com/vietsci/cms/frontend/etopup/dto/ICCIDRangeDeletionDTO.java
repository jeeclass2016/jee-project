package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO class for ICCIDRangeDeletion
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public class ICCIDRangeDeletionDTO extends BaseDTO implements Serializable {

  private String fileName;

  private Date createdDate;

  private String iccidStart;

  private String iccidEnd;

  public ICCIDRangeDeletionDTO() {
  }

  public ICCIDRangeDeletionDTO(String fileName, Date createdDate, String iccidStart, String iccidEnd) {
    this.fileName = fileName;
    this.createdDate = createdDate;
    this.iccidStart = iccidStart;
    this.iccidEnd = iccidEnd;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getIccidStart() {
    return iccidStart;
  }

  public void setIccidStart(String iccidStart) {
    this.iccidStart = iccidStart;
  }

  public String getIccidEnd() {
    return iccidEnd;
  }

  public void setIccidEnd(String iccidEnd) {
    this.iccidEnd = iccidEnd;
  }
}
