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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.AgentDataChangeDTO;
import com.vietsci.cms.frontend.etopup.model.AgentDataChange;
import com.vietsci.cms.frontend.etopup.service.AgentHistService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;


@Service
public class AgentHistServiceImpl implements AgentHistService{
  final Logger logger = LoggerFactory.getLogger(getClass());
  @Override
  public List<AgentDataChange> getAllAgentChanges(AgentDataChangeDTO agentDataChangeDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    try {
      data = mapper.writeValueAsString(agentDataChangeDTO);
      String restUrl = String.format("%s/find/all/agent/changes?data=%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      AgentDataChange[] results = restTemplate.getForObject(restUrl, AgentDataChange[].class);
      if (results == null)
        return new ArrayList<AgentDataChange>();
      else
        return Arrays.asList(results);
      
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<AgentDataChange>();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<AgentDataChange>();
    }
  }
  
  @Override
  public Long countAllAgentChanges(AgentDataChangeDTO agentDataChangeDTO) throws RestClientException, ResourceAccessException{
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    try {
      data = mapper.writeValueAsString(agentDataChangeDTO);
      String restUrl = String.format("%s/count/all/agent/changes?data=%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return restTemplate.getForObject(restUrl, Long.class);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return 0L;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return 0L;
    }
  }

}
