package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

public class SIMBatchDTO extends BaseDTO implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 5360908955273444775L;

  // Số thuê bao
  private String msisdn;

  // Tên file
  private String fileName;

  // Từ ngày
  private Date fromDate;

  // Đến ngày
  private Date toDate;

  /**
   * Default constructor. 
   */
  public SIMBatchDTO() {

  }

  /**
   * @param msisdn
   * @param tradeName
   * @param ownerName
   * @param iccid
   */
  public SIMBatchDTO(String msisdn, String fileName, Date fromDate, Date toDate) {
    this.msisdn = msisdn;
    this.fileName = fileName;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder searchContent = new StringBuilder();
    if (msisdn != null) {
      searchContent.append("/msisdn:").append(msisdn);
    }
    if (fileName != null) {
      searchContent.append("/fileName:").append(fileName);
    }
    if (fromDate != null) {
      searchContent.append("/fromDate:").append(fromDate);
    }
    if (toDate != null) {
      searchContent.append("/toDate:").append(toDate);
    }
    return searchContent.toString();
  }

}
