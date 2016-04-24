package com.vietsci.cms.frontend.main.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is responsible for mapping token response in json format. Because
 * token in the following json format
 * 
 * <pre>
 * {
 *  "access_token": "4344bc08-1319-4fd4-8466-8648a33f5dd7",
 *  "token_type": "bearer",
 *  "refresh_token": "b7c6f2ee-988c-456a-8c4c-74540534b964",
 *  "expires_in": 1224,
 *  "user_id": 5281
 * }
 * </pre>
 * 
 * so java object does not follow naming convention (contains underline in field
 * names)
 * 
 */
public class PlatformToken implements Serializable {

  private static final long serialVersionUID = -9110479836186797707L;
  /**
   * Time in second before actual expire date
   */
  private static final int EARLY_EXPIRE_DATE = 30;
  private String access_token;
  private Integer expires_in;
  private Date expireDate;
  private String refresh_token;
  private String token_type;
  private Long user_id;

  /**
   * Calculate and set the expireDate based on the expiredIn field.<br>
   * <strong>NOTE:</strong> only call this method when a token has already been
   * got.
   */
  public void updateExpireDate() {
    if (access_token != null && expires_in != null) {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.SECOND, this.expires_in - EARLY_EXPIRE_DATE);
      this.expireDate = calendar.getTime();
    }
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public Integer getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(Integer expires_in) {
    this.expires_in = expires_in;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }

  public String getToken_type() {
    return token_type;
  }

  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

}
