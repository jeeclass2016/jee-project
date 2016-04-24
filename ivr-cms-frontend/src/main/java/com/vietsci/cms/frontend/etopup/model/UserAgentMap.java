package com.vietsci.cms.frontend.etopup.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserAgentMap implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -536708172081974521L;

  private BigDecimal id;
  private Integer userId;
  private Long agentId;

  public UserAgentMap() {
  }

  public UserAgentMap(BigDecimal id) {
    this.id = id;
  }

  public UserAgentMap(BigDecimal id, Integer userId, Long agentId) {
    this.id = id;
    this.userId = userId;
    this.agentId = agentId;
  }

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Long getAgentId() {
    return agentId;
  }

  public void setAgentId(Long agentId) {
    this.agentId = agentId;
  }

}
