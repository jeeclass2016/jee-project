package com.vietsci.cms.api.util;

import org.springframework.web.context.request.WebRequest;

/**
 * Ajax Utils class, this is implementing methods would be check everything
 * related to AJAX request
 * 
 * Source code from https://github.com/spring-projects/spring-mvc-showcase/blob/master/src/main/java/org/springframework/mvc/extensions/ajax/AjaxUtils.java
 * 
 * @author spring-project
 * 
 * $LastChangedRevision $
 * $LastChangedDate $
 * $LastChangedBy $
 *
 */
public class AjaxUtils {
  private AjaxUtils() {
  }
  
  public static boolean isAjaxRequest(WebRequest webRequest) {
    String requestedWith = webRequest.getHeader("X-Requested-With");
    return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
  }

  public static boolean isAjaxUploadRequest(WebRequest webRequest) {
    return webRequest.getParameter("ajaxUpload") != null;
  }

}
