package com.vietsci.cms.frontend.etopup.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.CentreDTO;
import com.vietsci.cms.frontend.etopup.model.Centre;
import com.vietsci.cms.frontend.etopup.service.CentreService;
import com.vietsci.cms.frontend.exception.CmsRestException;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * For processing all core business task related to Centre Management
 * @author lehuyquang
 *
 */
@Service
public class CentreServiceImpl implements CentreService{

  final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Override
  public List<Centre> findCentre(CentreDTO searchCriteriaDTO) throws RestClientException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    try {
      String data = mapper.writeValueAsString(searchCriteriaDTO);
      String restUrl = String.format("%s/find-centre/by?data=%s", EnvironmentProperties.getData("etopup_api_url"),URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Centre[].class));
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      logger.error(null, e);
    }
    
    return new ArrayList<Centre>();
  }
  
  @Override
  public List<Centre> findAllActiveCentre() throws RestClientException, ResourceAccessException{
    RestTemplate restTemplate = new CmsRestTemplate();
    String restUrl = String.format("%s/find/all/active/centre/", EnvironmentProperties.getData("etopup_api_url"));
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    return Arrays.asList(restTemplate.getForObject(restUrl, Centre[].class));
  }

  @Override
  public Centre updateCentre(Centre newCentre) throws CmsRestException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/centre/update", EnvironmentProperties.getData("etopup_api_url"));
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(newCentre);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new Centre();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new Centre();
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("centreData", encodedData);

    return restTemplate.postForObject(restUrl, formData, Centre.class);

  }
  
  @Override
  public void deleteCentre(Long centreId) throws CmsRestException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    String restUrl = String.format("%s/centre/delete", EnvironmentProperties.getData("etopup_api_url"));
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();

    formData.add("centreId", centreId.toString()); 

    restTemplate.postForObject(restUrl, formData, Centre.class);
  }

  @Override
  public int countTotalRecord(CentreDTO centreSearchCriteria) throws CmsRestException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    try {
      String data = mapper.writeValueAsString(centreSearchCriteria);
      String restUrl = String.format("%s/count-centre/by?data=%s", EnvironmentProperties.getData("etopup_api_url")
          ,URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return restTemplate.getForObject(restUrl, Long.class).intValue();
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      logger.error(null, e);
      return 0;
    }
  }

}
