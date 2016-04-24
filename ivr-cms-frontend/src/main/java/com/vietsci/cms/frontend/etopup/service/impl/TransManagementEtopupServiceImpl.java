package com.vietsci.cms.frontend.etopup.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupStringUtil;
import com.vietsci.cms.frontend.etopup.dto.AccountLockingDTO;
import com.vietsci.cms.frontend.etopup.dto.EtopupTransLookupDTO;
import com.vietsci.cms.frontend.etopup.dto.TransDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransManagementEtopupServiceImpl implements TransManagementEtopupService {

  /* logger */
  private static final Log logger = LogFactory.getLog(TransManagementEtopupService.class);

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService#findTrans(AccountLockingDTO)
  */
  @Override
  public List<Trans> findTrans(AccountLockingDTO searchCriteria) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP web service de tim kiem cac giao dich");

    try {
      String data = objectMapper.writeValueAsString(searchCriteria);
      String restUrl = String.format("%s/find/trans/accountLocking/%s",
          EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Trans[].class));

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<Trans>();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<Trans>();
    }
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService#countTrans(AccountLockingDTO)
  */
  @Override
  public Long countTrans(AccountLockingDTO searchCriteria) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP web service de tinh tong so cac giao dich");

    try {
      String data = objectMapper.writeValueAsString(searchCriteria);
      data = EtopupStringUtil.enCodeGetData(data);
      String restUrl = String.format("%s/count/trans/accountLocking/%s",
          EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return restTemplate.getForObject(restUrl, Long.class);

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return 0L;

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return 0L;
    }
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService#findTrans(EtopupTransLookupDTO)
  */
  @Override
  public List<Trans> findTrans(EtopupTransLookupDTO searchCriteria) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP web service de tim kiem cac giao dich");

    try {
      String data = objectMapper.writeValueAsString(searchCriteria);
      String restUrl = String.format("%s/find/trans/etopupTransLookup/%s",
          EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, Trans[].class));

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<Trans>();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<Trans>();
    }
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService#countTrans(EtopupTransLookupDTO)
  */
  @Override
  public Long countTrans(EtopupTransLookupDTO searchCriteria) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP web service de tinh tong so cac giao dich");

    try {
      String data = objectMapper.writeValueAsString(searchCriteria);
      data = EtopupStringUtil.enCodeGetData(data);
      String restUrl = String.format("%s/count/trans/etopupTransLookup/%s",
          EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return restTemplate.getForObject(restUrl, Long.class);

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return 0L;

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return 0L;
    }
  }
  
  /* (non-Javadoc)
   * @see com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService#transferMoney(com.vietsci.cms.frontend.etopup.dto.TransDTO)
   */
  public Boolean transferMoneyBetweenSHTs(TransDTO transDTO) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    // Call etopup service
    String restUrl = String.format("%s/transfer/trans/money/", EnvironmentProperties.getData("etopup_api_url"));
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(transDTO);
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
}
