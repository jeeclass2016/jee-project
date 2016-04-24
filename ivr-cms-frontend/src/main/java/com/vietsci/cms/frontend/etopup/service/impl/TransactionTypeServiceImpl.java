package com.vietsci.cms.frontend.etopup.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.TransactionTypeDTO;
import com.vietsci.cms.frontend.etopup.model.TransactionType;
import com.vietsci.cms.frontend.etopup.service.TransactionTypeService;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
  
  final Logger logger = LoggerFactory.getLogger(getClass());
	
  public TransactionType getTransactionType(long id) throws ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    String restUrl = String.format("%s/transtype/%s", EnvironmentProperties.getData("etopup_api_url"), id);
    return restTemplate.getForObject(restUrl, TransactionType.class);
  }	
  
  public List<TransactionType> getTransactionTypeList(TransactionTypeDTO transactionTypeDTO) throws ResourceAccessException{
    List<TransactionType> transactionTypeList = new ArrayList<TransactionType>();
    RestTemplate restTemplate = new CmsRestTemplate();
  	try {
  	  ObjectMapper mapper = new ObjectMapper();
      String data = mapper.writeValueAsString(transactionTypeDTO);
      String restUrl = String.format("%s/transtypes?%s", EnvironmentProperties.getData("etopup_api_url"), "param=" + URLEncoder.encode(data, Constants.ENCODING_UTF8));
      transactionTypeList = Arrays.asList(restTemplate.getForObject(restUrl, TransactionType[].class));
  	} catch (UnsupportedEncodingException e) {
  		logger.error(e.getMessage());
  	} catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return transactionTypeList;
  }

  public TransactionType saveUpdateTransactionType(TransactionType transType) throws ResourceAccessException, CmsRestException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    String restUrl = null;
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(transType);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new TransactionType();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new TransactionType();
    }
    
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("transtypeData", encodedData);
    
    if (transType.getId() == 0){
      restUrl = String.format("%s/transtypes", EnvironmentProperties.getData("etopup_api_url"));
    }
    else {
      restUrl = String.format("%s/transtype/%s", EnvironmentProperties.getData("etopup_api_url"), transType.getId());
    }
   	return restTemplate.postForObject(restUrl, formData, TransactionType.class);
  }
  
  public void deleteTransactionType(long id) throws CmsRestException, ResourceAccessException{
	RestTemplate restTemplate = new CmsRestTemplate();
	restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    String restUrl = String.format("%s/transtype/%s/delete", EnvironmentProperties.getData("etopup_api_url"), id);
    restTemplate.postForObject(restUrl, null, TransactionType.class);	
  }

  @Override
  public int countTotalRecord(TransactionTypeDTO transactionTypeDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    
    try {
      String data = mapper.writeValueAsString(transactionTypeDTO);
      String restUrl = String.format("%s/transtypes/count?%s", EnvironmentProperties.getData("etopup_api_url"), "param=" + URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return 0;
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return 0;
    }
  }
}
