/**
 * 
 */
package com.vietsci.cms.frontend.etopup.dto;

import java.util.List;

/**
 * @author NguyenPV
 *
 */
public class TLDTransBatchDTO extends BaseDTO {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -3464885851800388529L;

  private List<Long> transIdList;
  private Long userId;
  
  public List<Long> getTransIdList() {
    return transIdList;
  }
  public void setTransIdList(List<Long> transIdList) {
    this.transIdList = transIdList;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  
}
