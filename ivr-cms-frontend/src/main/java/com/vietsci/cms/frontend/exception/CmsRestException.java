package com.vietsci.cms.frontend.exception;

public class CmsRestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public CmsRestException() {
    super();
  }

  public CmsRestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public CmsRestException(String message, Throwable cause) {
    super(message, cause);
  }

  public CmsRestException(String message) {
    super(message);
  }

  public CmsRestException(Throwable cause) {
    super(cause);
  }
}
