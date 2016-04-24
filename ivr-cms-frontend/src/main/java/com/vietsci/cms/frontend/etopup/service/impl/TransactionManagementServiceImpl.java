/**
 * 
 */
package com.vietsci.cms.frontend.etopup.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.TLDTransBatchDTO;
import com.vietsci.cms.frontend.etopup.dto.TLDTransDTO;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.service.TransactionManagementService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

/** 
 * Implementation details for communicating with eTopUp API to process with Transaction Management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Service
public class TransactionManagementServiceImpl implements TransactionManagementService {
  
  private static final Log logger = LogFactory.getLog(TransactionManagementService.class);

  @Override
  public List<Trans> getTransactionList(TransactionManagementDTO transactionManagementDTO)
      throws ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(transactionManagementDTO);
      String restUrl = String.format("%s/find-trans/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Trans[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<Trans>();
    }
  }

  @Override
  public int countTotalRecord(TransactionManagementDTO transactionManagementDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(transactionManagementDTO);
      String restUrl = String.format("%s/count-trans/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }

  @Override
  public List<Trans> findTransForMposLookup(TransactionManagementDTO dto) throws ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(dto);
      String restUrl = String.format("%s/find/trans/mpos/by/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Trans[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<Trans>();
    }
  }

  @Override
  public Long countTransForMposLookup(TransactionManagementDTO dto) throws ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(dto);
      String restUrl = String.format("%s/count/trans/mpos/by/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs;
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0L;
    }
  }

  @Override
  public List<Trans> findBonusStatus(TransactionManagementDTO transactionManagementDTO) throws ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(transactionManagementDTO);
      String restUrl = String.format("%s/find-bonus-status/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Trans[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<Trans>();
    }
  }

  @Override
  public List<Trans> findTLDPTrans(TransactionManagementDTO dto) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(dto);
      String restUrl = String.format("%s/find-tldp-trans/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Trans[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<Trans>();
    }
  }

  @Override
  public int countTLDPTrans(TransactionManagementDTO dto) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(dto);
      String restUrl = String.format("%s/count-tldp-trans/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }

  @Override
  public Boolean createTLDTrans(TLDTransDTO tldTransDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    
    String restUrl = String.format("%s/create-tld-trans", EnvironmentProperties.getData("etopup_api_url"));
    Boolean rs = (Boolean) restTemplate.postForObject(restUrl, tldTransDTO, Boolean.class);
    return rs;
  }

  @Override
  public Boolean completeTLDTransBatch(TLDTransBatchDTO batchDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    
    String restUrl = String.format("%s/complete-tld-trans", EnvironmentProperties.getData("etopup_api_url"));
    Boolean rs = (Boolean) restTemplate.postForObject(restUrl, batchDTO, Boolean.class);
    return rs;
  }

  @Override
  public Boolean rejectTLDTransBatch(TLDTransBatchDTO batchDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    
    String restUrl = String.format("%s/reject-tld-trans", EnvironmentProperties.getData("etopup_api_url"));
    Boolean rs = (Boolean) restTemplate.postForObject(restUrl, batchDTO, Boolean.class);
    return rs;
  }

}
