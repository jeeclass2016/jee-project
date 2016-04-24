package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for AgentBatchUploading (UploadDaiLyTheoLo)
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */

public class AgentBatchUploadingDTO extends BaseDTO implements Serializable {

  private String phoneNumer;

  private String agentName;

  private Date startDate;

  private Date endDate;

  private String agentUploadedFileName;

  public AgentBatchUploadingDTO() {

  }

  public AgentBatchUploadingDTO(String phoneNumer, String fileName, Date startDate, Date endDate) {
    this.phoneNumer = phoneNumer;
    this.agentName = fileName;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getPhoneNumer() {
    return phoneNumer;
  }

  public void setPhoneNumer(String phoneNumer) {
    this.phoneNumer = phoneNumer;
  }

  public String getAgentName() {
    return agentName;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getAgentUploadedFileName() {
    return agentUploadedFileName;
  }

  public void setAgentUploadedFileName(String agentUploadedFileName) {
    this.agentUploadedFileName = agentUploadedFileName;
  }
}