package com.vietsci.cms.api.exception;

/**
 * Wrapped class for exception occurred in our system.
 * 
 */
public class BusinessException extends Exception {

  /**
   * Serial Version UID. 
   */
  private static final long serialVersionUID = 3359273830449311046L;
  
  private String msgKey = null;
  private String location = null;
  
  /**
   * Constructor to instantiate this exception with a description and a message key.
   * 
   * @param description   description detail
   * @param msgKey        message key
   */
  public BusinessException(String description, String msgKey) {
    super(description);
    this.msgKey = msgKey;
  }
  
  /**
   * Constructor to instantiate this exception with a description and a throwable object.
   * 
   * @param description   description detail
   * @param throwable     throwable object
   */
  public BusinessException(String description, Throwable throwable) {
    super(description, throwable);
  }
  
  /**
   * Constructor to instantiate this exception with a description, a Throwable object and a message key.
   * 
   * @param description   description detail
   * @param throwable     Throwable object
   * @param msgKey        message key
   */
  public BusinessException(String description, Throwable throwable, String msgKey) {
    super(description, throwable);
    this.msgKey = msgKey;
  }
  
  /**
   * Constructor to instantiate this exception with a location, a description and a message key.
   * 
   * @param location      where the exception happens
   * @param description   description detail
   * @param msgKey        message key
   */
  public BusinessException(String location, String description, String msgKey) {
    super(description);
    this.msgKey = msgKey;
    this.location = location;
  }

  /**
   * @return the msgKey
   */
  public String getMsgKey() {
    return msgKey;
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }
  
}