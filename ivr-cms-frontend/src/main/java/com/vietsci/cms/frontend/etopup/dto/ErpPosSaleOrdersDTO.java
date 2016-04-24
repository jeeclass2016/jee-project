package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

public class ErpPosSaleOrdersDTO extends BaseDTO implements Serializable {
  private Long orderId;
  private Long shtId;
  private Integer status;
  private Date startDate;
  private Date endDate;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getShtId() {
    return shtId;
  }

  public void setShtId(Long shtId) {
    this.shtId = shtId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  @Override
  public String toString() {
    return "ErpPosSaleOrdersDTO{" +
            "orderId=" + orderId +
            ", shtId=" + shtId +
            ", status=" + status +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
  }
}
