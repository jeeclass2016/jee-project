package com.vietsci.cms.api.util;


/**
* Constants used for CMS-IVR.
* 
*/
public class Constants {

  // encoding của URL
  public static final String ENCODING_UTF8 = "UTF-8";

  // Status
  public static final String ACTIVE_STATUS = "1";
  public static final String INACTIVE_STATUS = "0";

  public static final String YES = "1";
  public static final String NO = "0";

  public static final String SYSTEM_ERROR_CODE = "0001";
  public static final String REASON_EMPTY_ERROR_CODE = "0100";
  public static final String MISSING_REASON_CODE_ERROR_CODE = "0101";
  public static final String REASON_CODE_EXISTED_ERROR_CODE = "0102";
  public static final String REASON_NOT_EXISTED_ERROR_CODE = "0103";
  public static final String REASON_DELETED_ERROR_CODE = "0104";
  public static final String INVALID_REASON_ERROR_CODE = "0104";

  public static final String MISSING_TRANSACTION_TYPE_CODE_ERROR_CODE = "0201";
  public static final String MISSING_TRANSACTION_TYPE_DESCRIPTION_ERROR_CODE = "0202";
  public static final String MISSING_TRANSACTION_TYPE_STATUS_ERROR_CODE = "0203";
  public static final String TRANSACTION_TYPE_CODE_EXISTED_ERROR_CODE = "0204";
  public static final String TRANSACTION_TYPE_NOT_EXISTED_ERROR_CODE = "0205";
  public static final String TRANSACTION_TYPE_DELETED_ERROR_CODE = "0206";
  public static final String INVALID_TRANSACTION_TYPE_ERROR_CODE = "0207";
  
  /* DATE FORMAT TO CONVERT TO STRING */
  public static final String DATE_FORMAT = "yyyyMMddHHmmss";
}
