package com.vietsci.cms.frontend.etopup.common.util;

public class EtopupStringUtil {

  public static boolean isNullOrBlank(String str) {
    return (str == null || str.trim().length() == 0);
  }

  /**
   * This method will change special characters to URL encoding
   * @param data normal string
   * @return a string that changed to URL encoding
   *
   * @author hong.nguyenmai
   */
   public static String enCodeGetData(String data) {
    return data.replace(".", "%2E");
  }

  public static String preprocessQueryToSearchLikeInDatabase(String query) {
    String preprocessedQuery = "";

    if (!isNullOrBlank(query)) {
      String trimmedQuery = query.trim();
      preprocessedQuery = trimmedQuery.replace('*', '%');
    }

    return preprocessedQuery;
  }

  public static String preprocessQueryToSearchLikeInUI(String query) {
    StringBuilder preprocessedQuery = new StringBuilder(".*");

    if (!isNullOrBlank(query)) {
      String trimmedQuery = query.trim();
      preprocessedQuery.append(trimmedQuery.replace("*", ".*"));
    }

    preprocessedQuery.append(".*");

    return preprocessedQuery.toString();
  }
}
