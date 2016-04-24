package com.vietsci.cms.frontend.resttemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * Extends default RestTemple of Spring Web, and adds {@link CmsClientHttpRequestInterceptor}
 * 
 * @see org.springframework.web.client.RestTemple
 */
public class CmsRestTemplate extends RestTemplate {

  /**
   * Create an instance and set an interceptor which attaches authorization header
   */
  public CmsRestTemplate() {
    super();
    /*List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    interceptors.add(new CmsClientHttpRequestInterceptor());
    setInterceptors(interceptors);*/
  }
}
