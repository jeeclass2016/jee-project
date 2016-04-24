package com.vietsci.cms.frontend.etopup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class LoadedSim implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -7498763200085657202L;
  private long id;
  private SimBatch simBatch;
  private String iccid;
  private String msisdn;
  private Date created;
  private Date lastModified;
  private String status;
  private Boolean delStatus;

  public LoadedSim() {
  }

  public LoadedSim(long id, SimBatch simBatch, String iccid) {
    this.id = id;
    this.simBatch = simBatch;
    this.iccid = iccid;
  }

  public LoadedSim(long id, SimBatch simBatch, String iccid, String msisdn, Date created, Date lastModified,
      String status, Boolean delStatus) {
    this.id = id;
    this.simBatch = simBatch;
    this.iccid = iccid;
    this.msisdn = msisdn;
    this.created = created;
    this.lastModified = lastModified;
    this.status = status;
    this.delStatus = delStatus;
  }
  
  public LoadedSim(long id, String iccid) {
    this.id = id;
    this.iccid = iccid;
  }

  public LoadedSim(long id, String iccid, String msisdn, Date created, Date lastModified,
      String status, Boolean delStatus) {
    this.id = id;
    this.iccid = iccid;
    this.msisdn = msisdn;
    this.created = created;
    this.lastModified = lastModified;
    this.status = status;
    this.delStatus = delStatus;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public SimBatch getSimBatch() {
    return simBatch;
  }

  public void setSimBatch(SimBatch simBatch) {
    this.simBatch = simBatch;
  }

  public String getIccid() {
    return iccid;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
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
