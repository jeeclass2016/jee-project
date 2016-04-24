package com.vietsci.cms.frontend.etopup.service;

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
import com.vietsci.cms.frontend.etopup.model.McSubscriber;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

@Service
public class EtopupMcSubscriberServiceImpl implements EtopupMcSubscriberService{

  final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Override
  public McSubscriber findActiveMcSubscriberByMDN(String mdn) throws RestClientException, ResourceAccessException{

    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    try {
      String data = mapper.writeValueAsString(mdn);
      String restUrl = String.format("%s/find/active/mc-sub/msisdn/by?data=%s", EnvironmentProperties.getData("etopup_api_url"),
        URLEncoder.encode(data, Constants.ENCODING_UTF8));
      McSubscriber result = restTemplate.getForObject(restUrl, McSubscriber.class);

      return result == null ? new McSubscriber() : result;

    } catch (UnsupportedEncodingException | JsonProcessingException e) {
      // TODO Auto-generated catch block
      logger.error(null, e);
      return new McSubscriber();
    }
  }

  public List<McSubscriber> findListActiveMcSubscriberByListMDN(List<String> mdn) throws RestClientException, ResourceAccessException{

    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    try {
      String data = mapper.writeValueAsString(mdn);
      String restUrl = String.format("%s/find/active/list/mc-sub/msisdn/by?data=%s", EnvironmentProperties.getData("etopup_api_url"),
              URLEncoder.encode(data, Constants.ENCODING_UTF8));
      List<McSubscriber> result = Arrays.asList(restTemplate.getForObject(restUrl, McSubscriber[].class));

      return result == null ? new ArrayList<McSubscriber>() : result;

    } catch (UnsupportedEncodingException | JsonProcessingException e) {
      // TODO Auto-generated catch block
      logger.error(null, e);
      return new ArrayList<McSubscriber>();
    }
  }

}
