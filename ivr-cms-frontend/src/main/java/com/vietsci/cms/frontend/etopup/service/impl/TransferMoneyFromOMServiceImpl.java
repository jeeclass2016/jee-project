package com.vietsci.cms.frontend.etopup.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.ErpPosSaleOrdersDTO;
import com.vietsci.cms.frontend.etopup.model.ErpPosSaleOrders;
import com.vietsci.cms.frontend.etopup.service.TransferMoneyFromOMService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransferMoneyFromOMServiceImpl implements TransferMoneyFromOMService {
  final Logger logger = LoggerFactory.getLogger(TransferMoneyFromOMService.class);

  @Override
  public List<ErpPosSaleOrders> findOrdersByFilter(ErpPosSaleOrdersDTO ordersDTO) throws RestClientException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/order/find-by-filter", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(ordersDTO);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return Collections.emptyList();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return Collections.emptyList();
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return Arrays.asList(restTemplate.postForObject(restUrl, formData, ErpPosSaleOrders[].class));
  }

  @Override
  public List<ErpPosSaleOrders> getAllErpPosSaleOrders() throws RestClientException, ResourceAccessException{
    RestTemplate restTemplate = new CmsRestTemplate();
    String restUrl = String.format("%s/order/find-all", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    try {
      return Arrays.asList(restTemplate.getForObject(restUrl, ErpPosSaleOrders[].class));
    } catch (Exception e) {
      logger.error(e.getMessage());
      return Collections.emptyList();
    }
  }

  @Override
  public boolean transferMoney(ErpPosSaleOrders orders, Integer staffId) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/order/transfer", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String encodedData = null;
    String encodedStaffId = null;

    try {
      encodedData = URLEncoder.encode(mapper.writeValueAsString(orders), Constants.ENCODING_UTF8);
      encodedStaffId = URLEncoder.encode(mapper.writeValueAsString(staffId), Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);
    formData.add("staffId", encodedStaffId);

    return restTemplate.postForObject(restUrl, formData, Boolean.class);
  }

  @Override
  public boolean reject(ErpPosSaleOrders orders, Integer staffId) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/order/reject", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String encodedData = null;
    String encodedStaffId = null;

    try {
      encodedData = URLEncoder.encode(mapper.writeValueAsString(orders), Constants.ENCODING_UTF8);
      encodedStaffId = URLEncoder.encode(mapper.writeValueAsString(staffId), Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);
    formData.add("staffId", encodedStaffId);

    return restTemplate.postForObject(restUrl, formData, Boolean.class);
  }
}
