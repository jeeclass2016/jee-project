/**
 * 
 */
package com.vietsci.cms.frontend.etopup.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CuongPV
 *
 */
public class SearchCriterialUserDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -9176539452610974676L;
  private String userName;
  private Integer status;
  private Date fromDate;
  private Date toDate;
  private Integer pageIndex;
  private Integer pageSize;
  private String sortField;
  private String sortOrder;
  
  public SearchCriterialUserDTO() {
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
  /**
   * @return the status
   */
  public Integer getStatus() {
    return status;
  }
  /**
   * @param status the status to set
   */
  public void setStatus(Integer status) {
    this.status = status;
  }
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
  /**
   * @return the sortField
   */
  public String getSortField() {
    return sortField;
  }
  /**
   * @param sortField the sortField to set
   */
  public void setSortField(String sortField) {
    this.sortField = sortField;
  }
  /**
   * @return the sortOrder
   */
  public String getSortOrder() {
    return sortOrder;
  }
  /**
   * @param sortOrder the sortOrder to set
   */
  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  
}
