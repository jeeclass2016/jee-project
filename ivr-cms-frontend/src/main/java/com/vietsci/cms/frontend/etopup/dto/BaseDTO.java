/**
 * 
 */
package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;

/**
 * @author nguyenpv
 *
 */
public class BaseDTO implements Serializable {
  
  /**
  * Serial Version UID.
  */
  private static final long serialVersionUID = 2595136854335119590L;
  
  private Integer pageIndex = 1;
  private Integer pageSize = 1000;
  private String sortField;
  private String sortOrder;
  
  /**
   * @return the pageIndex
   */
  public Integer getPageIndex() {
    return pageIndex;
  }
  /**
   * @param pageIndex the pageIndex to set
   */
  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }
  /**
   * @return the pageSize
   */
  public Integer getPageSize() {
    return pageSize;
  }
  /**
   * @param pageSize the pageSize to set
   */
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
  public String getSortField() {
    return sortField;
  }
  public void setSortField(String sortField) {
    this.sortField = sortField;
  }
  public String getSortOrder() {
    return sortOrder;
  }
  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }
  
}
