package com.vietsci.cms.frontend.etopup.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;
import com.vietsci.cms.frontend.etopup.service.GeographicService;
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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeographicServiceImpl implements GeographicService, Serializable {

  private static final long serialVersionUID = -4124568436143396721L;
  final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Override
  public List<TblMapProvince> getAllProvince() throws RestClientException, ResourceAccessException{
    RestTemplate restTemplate = new CmsRestTemplate();
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String restUrl = String.format("%s/find/all/province", EnvironmentProperties.getData("etopup_api_url"));
    TblMapProvince[] results = restTemplate.getForObject(restUrl, TblMapProvince[].class);
     
    return (results==null)? (new ArrayList<TblMapProvince>()) : Arrays.asList(results);
  }
  
  

  @Override
  public List<TblMapDistrict> getAllDistrictByProvince(String provinceId) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    try {
      String data = mapper.writeValueAsString(provinceId);
      String restUrl = String.format("%s/find/all/district/by/province?data=%s", EnvironmentProperties.getData("etopup_api_url"),URLEncoder.encode(data, Constants.ENCODING_UTF8));
      
      TblMapDistrict[] results = restTemplate.getForObject(restUrl, TblMapDistrict[].class);
      return (results==null)? (new ArrayList<TblMapDistrict>()) : Arrays.asList(results);
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      logger.error(null, e);
      return new ArrayList<TblMapDistrict>();
    }
  }

}
