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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.StockAgentMapDTO;
import com.vietsci.cms.frontend.etopup.model.StockAgentMap;
import com.vietsci.cms.frontend.etopup.model.StockAgentMapId;
import com.vietsci.cms.frontend.etopup.service.EtopupStockAgentMapService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

@Service
public class EtopupStockAgentMapServiceImpl implements EtopupStockAgentMapService {
  final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public StockAgentMap create(StockAgentMapDTO newStockAgentMap) throws RestClientException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/create/stock-agent-map/by", EnvironmentProperties.getData("etopup_api_url"));
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(newStockAgentMap);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new StockAgentMap(new StockAgentMapId(), null, null);
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new StockAgentMap(new StockAgentMapId(), null, null);
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, StockAgentMap.class);
  }

  @Override
  public Boolean update(StockAgentMapDTO newStockAgentMap) throws RestClientException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/update/stock-agent-map/by", EnvironmentProperties.getData("etopup_api_url"));
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(newStockAgentMap);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, Boolean.class);
  }

  @Override
  public Boolean delete(StockAgentMapDTO stockAgentMap) throws RestClientException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/delete/stock-agent-map/by", EnvironmentProperties.getData("etopup_api_url"));
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(stockAgentMap);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return Boolean.FALSE;
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, Boolean.class);
  }

  @Override
  public List<StockAgentMap> getAllStockAgentMapByAgentId(StockAgentMapDTO stockAgentMapDTO) throws RestClientException,
      ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    try {
      data = mapper.writeValueAsString(stockAgentMapDTO);
      String restUrl = String.format("%s/find/stock-agent-map/by/agent-id/?data=%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      StockAgentMap[] results = restTemplate.getForObject(restUrl, StockAgentMap[].class);
      if (results == null)
        return new ArrayList<StockAgentMap>();
      else
        return Arrays.asList(results);
      
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<StockAgentMap>();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<StockAgentMap>();
    }
  }
  
  @Override
  public Long countAllStockAgentMapByAgentId(StockAgentMapDTO stockAgentMapDTO) throws RestClientException,
      ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    try {
      data = mapper.writeValueAsString(stockAgentMapDTO);
      String restUrl = String.format("%s/count/stock-agent-map/by/agent-id/?data=%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      Long result = restTemplate.getForObject(restUrl, Long.class);
      if (result == null)
        return 0L;
      else
        return result;
      
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return 0L;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return 0L;
    }
  }

}
