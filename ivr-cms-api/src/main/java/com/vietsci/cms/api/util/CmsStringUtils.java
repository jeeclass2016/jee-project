package com.vietsci.cms.api.util;

public class CmsStringUtils {
  
  public static boolean isNullOrEmpty(String needCheckingString) {
    return needCheckingString == null || "".equals(needCheckingString);
  }

  public static boolean isNullOrBlank(String str) {
    return (str == null || str.trim().length() == 0);
  }

  public static boolean getBooleanValue(String booleanStr) {
    if("0".equals(booleanStr)) {
      return false;
    }
    return true;
  }

  /**
   * This method will change URL encoding characters to normal
   * @param data a string contains URL encoding characters
   * @return normal string
   *
   */
  public static String decodeGetData(String data) {
    return data.replace("%2E", ".");
  }

}
