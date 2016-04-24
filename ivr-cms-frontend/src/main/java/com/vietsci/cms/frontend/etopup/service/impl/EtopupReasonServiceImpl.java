package com.vietsci.cms.frontend.etopup.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.ReasonDTO;
import com.vietsci.cms.frontend.etopup.model.Reason;
import com.vietsci.cms.frontend.etopup.service.EtopupReasonService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

/** 
 * Implementation details for communicating with eTopUp API to process with Reasons.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Service
public class EtopupReasonServiceImpl implements EtopupReasonService {
  
  private static final Log logger = LogFactory.getLog(EtopupReasonService.class);

  /**
   * {@inheritDoc}
   */
  public List<Reason> findReasons(ReasonDTO reasonSearchCriteria) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String reasonStatusNumberValue = reasonSearchCriteria.getStatusNumberValue();
      if (Constants.ReasonManagement.ACTIVE_STATUS_VALUE.equals(reasonStatusNumberValue)) {
        reasonSearchCriteria.setStatus(Boolean.TRUE);
      } else if (Constants.ReasonManagement.INACTIVE_STATUS_VALUE.equals(reasonStatusNumberValue)) {
        reasonSearchCriteria.setStatus(Boolean.FALSE);
      } else {
        reasonSearchCriteria.setStatus(null);
      }
      String data = mapper.writeValueAsString(reasonSearchCriteria);
      String restUrl = String.format("%s/find-reasons/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Reason[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<Reason>();
    }
  }

  /**
   * {@inheritDoc}
   */
  public void deleteReason(Long reasonId) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    String restUrl = String.format("%s/delete-reason/%s", EnvironmentProperties.getData("etopup_api_url"), reasonId);
    restTemplate.delete(restUrl);
  }

  /**
   * {@inheritDoc}
   */
  public boolean createReason(Reason reason) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/create-reason", EnvironmentProperties.getData("etopup_api_url"));
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));
    
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(reason);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("reasonData", encodedData);
    boolean result = restTemplate.postForObject(restUrl, formData, Boolean.class);
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean updateReason(Reason reason) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/update-reason", EnvironmentProperties.getData("etopup_api_url"));

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(reason);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }
    
    restTemplate.put(restUrl, encodedData);
    return true;
    
  }

  /**
   * {@inheritDoc}
   */
  public int countTotalRecord(ReasonDTO reasonSearchCriteria) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      String reasonStatusNumberValue = reasonSearchCriteria.getStatusNumberValue();
      if (Constants.ReasonManagement.ACTIVE_STATUS_VALUE.equals(reasonStatusNumberValue)) {
        reasonSearchCriteria.setStatus(Boolean.TRUE);
      } else if (Constants.ReasonManagement.INACTIVE_STATUS_VALUE.equals(reasonStatusNumberValue)) {
        reasonSearchCriteria.setStatus(Boolean.FALSE);
      } else {
        reasonSearchCriteria.setStatus(null);
      }
      String data = mapper.writeValueAsString(reasonSearchCriteria);
      String restUrl = String.format("%s/count-reasons/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }

}
