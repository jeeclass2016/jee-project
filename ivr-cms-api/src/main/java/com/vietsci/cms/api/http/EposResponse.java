package com.vietsci.cms.api.http;

/**
 * Cms response entity, this is using for json converting
 * This would be used in controller exception handler
 *
 */
public class EposResponse {
  private int statusCode;
  private String messageCode;
  private String message;
  
  public EposResponse() {
  }
  
  public EposResponse(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message; 
  }
  
  public EposResponse(int statusCode, String messageCode, String message) {
    this.statusCode = statusCode;
    this.messageCode = messageCode;
    this.message = message; 
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessageCode() {
    return messageCode;
  }

  public void setMessageCode(String messageCode) {
    this.messageCode = messageCode;
  }
  
  
}
