package com.vietsci.cms.frontend.resttemplate;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vietsci.cms.frontend.main.controller.AuthorizationController;

/**
 * This interceptor adds "Authorization" header to each request to authenticate against servers
 * 
 */
public class CmsClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(CmsClientHttpRequestInterceptor.class);

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.http.client.ClientHttpRequestInterceptor#intercept(
   * org.springframework.http.HttpRequest, byte[],
   * org.springframework.http.client.ClientHttpRequestExecution)
   */
  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {
    HttpHeaders headers = request.getHeaders();
    headers.add("Authorization", "Bearer " + getAccessToken());
    return execution.execute(request, body);
  }

  /**
   * Get access token from the current session
   *  
   * @return access token
   */
  protected String getAccessToken() {
    /*ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession session = attr.getRequest().getSession();
    String token = null;
    if (session != null) {
      AuthorizationController auth = (AuthorizationController) session.getAttribute(AuthorizationController.BEAN_NAME);
      if (auth != null) {
        token = auth.getAccessToken();
      }
    }
    if (token == null || token.trim().length() == 0) {
      LOGGER.warn("Could not find a token from session!");
    }
    return token;*/
    return null;
  }
}
