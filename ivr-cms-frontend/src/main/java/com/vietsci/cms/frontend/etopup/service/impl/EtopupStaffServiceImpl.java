package com.vietsci.cms.frontend.etopup.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.model.EposStaff;
import com.vietsci.cms.frontend.etopup.service.EtopupStaffService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

@Service
public class EtopupStaffServiceImpl implements EtopupStaffService{
  final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Override
  public EposStaff getActiveStaffByStaffId(Long staffId) {
    final Logger logger = LoggerFactory.getLogger(getClass());
    
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    try {
      
      data = mapper.writeValueAsString(staffId);
      String restUrl = String.format("%s/find/active/staff/by/staff-id?data=%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      EposStaff result = restTemplate.getForObject(restUrl, EposStaff.class);
      if (result == null)
        return new EposStaff();
      else
        return result;
      
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new EposStaff();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new EposStaff();
    }
  }

  @Override
  public List<EposStaff> getAllActiveStaffLikeStaffCode(String staffCode) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    try {
      data = mapper.writeValueAsString(staffCode);
      String restUrl = String.format("%s/find/active/staff/like/staff-code?data=%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      EposStaff[] results = restTemplate.getForObject(restUrl, EposStaff[].class);
      if (results == null)
        return new ArrayList<EposStaff>();
      else
        return Arrays.asList(results);
      
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<EposStaff>();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<EposStaff>();
    }
  }

}
