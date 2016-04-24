package com.vietsci.cms.api.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Web application initialization class
 * 
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[0];
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { WebAppConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Configuration
  @EnableWebMvc
  @ComponentScan(basePackages = { "com.vietsci.cms.api" })
  public static class WebAppConfig {
  }
}
