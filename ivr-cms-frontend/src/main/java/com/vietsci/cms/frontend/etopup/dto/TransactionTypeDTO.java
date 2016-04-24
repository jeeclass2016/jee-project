package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;

/** 
 * DTO dung cho viec tra cuu danh muc giao dich.
 * @author trung.doduc
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class TransactionTypeDTO extends BaseDTO implements Serializable {
  /**
  * Serial Version UID. 
  */
  private static final long serialVersionUID = -1352623770256460026L;
  private String code;
  private String description;
  private int delStatus;

  private String status;

  /**
   * @return ma GD
   */ 
  public String getCode() {
    return code;
  }

  /**
   * @param code ma GD
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return mo ta
   */
  public String getDescription() {
	return description;
  }
  
  /**
   * @param description mo ta giao dich
   */
  public void setDescription(String description) {
	this.description = description;
  }

  /**
   * @return trang thai giao dich
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status trang thai giao dich
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return trang thai giao dich
   */
  public int getDelStatus() {
    return delStatus;
  }

  /**
   * @param delStatus trang thai xoa
  */
  public void setDelStatus(int delStatus) {
    this.delStatus = delStatus;
  }

}
