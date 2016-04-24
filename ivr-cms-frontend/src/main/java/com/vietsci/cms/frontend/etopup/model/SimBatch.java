package com.vietsci.cms.frontend.etopup.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class SimBatch implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 673068154529322604L;

  private Long batchId;
  private String fileName;
  private Date createDate;
  private Long entries;
  private byte[] loadedsim;
  private Set<LoadedSim> loadedSims = new HashSet<LoadedSim>(0);

  public SimBatch() {
  }

  public SimBatch(long batchId, String fileName, Date createDate) {
    this.batchId = batchId;
    this.fileName = fileName;
    this.createDate = createDate;
  }

  public SimBatch(long batchId, String fileName, Date createDate, Long entries) {
    this.batchId = batchId;
    this.fileName = fileName;
    this.createDate = createDate;
    this.entries = entries;
  }

  public Long getBatchId() {
    return batchId;
  }

  public void setBatchId(Long batchId) {
    this.batchId = batchId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Long getEntries() {
    return entries;
  }

  public void setEntries(Long entries) {
    this.entries = entries;
  }

  public byte[] getLoadedsim() {
    return loadedsim;
  }

  public void setLoadedsim(byte[] loadedsim) {
    this.loadedsim = loadedsim;
  }

  public Set<LoadedSim> getLoadedSims() {
    return loadedSims;
  }

  public void setLoadedSims(Set<LoadedSim> loadedSims) {
    this.loadedSims = loadedSims;
  }

}
