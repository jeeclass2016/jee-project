package com.vietsci.cms.frontend.etopup.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.AccountInfoSearchingDTO;
import com.vietsci.cms.frontend.etopup.dto.AccountLockingDTO;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentBatchDTO;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentDTO;
import com.vietsci.cms.frontend.etopup.model.AgentAccount;
import com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class BalanceManagementEtopupServiceImpl implements BalanceManagementEtopupService {

  /* logger */
  private static final Log logger = LogFactory.getLog(BalanceManagementEtopupService.class);

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService#adjustBalance(BalanceAdjustmentDTO)
  */
  @Override
  public boolean adjustBalance(BalanceAdjustmentDTO adjustmentInfo) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/adjust/balance", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(adjustmentInfo);
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
  public boolean createBalanceAdjustmentBatch(BalanceAdjustmentBatchDTO balanceAdjustmentBatchDTO) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/adjust/balance-batch", EnvironmentProperties.getData("etopup_api_url"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;

    try {
      data = mapper.writeValueAsString(balanceAdjustmentBatchDTO);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (IOException e) {
      logger.error(e.getMessage());
      return false;
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);
    boolean result = restTemplate.postForObject(restUrl, formData, Boolean.class);
    return result;
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService#findAccountInfo(AccountInfoSearchingDTO)
  */
  public AgentAccount findAccountInfo(AccountInfoSearchingDTO searchCriteria) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    logger.debug("Gui MSISDN den ETOPUP webservice de tim kiem thong tin tai khoan");

    try {
      String data = mapper.writeValueAsString(searchCriteria);
      String restUrl = String.format("%s/find/accountInfo/%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      return restTemplate.getForObject(restUrl, AgentAccount.class);

    } catch (JsonProcessingException e) {
        logger.error(e.getMessage());
        return new AgentAccount();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();
    }
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService#lockBalance(AccountLockingDTO)
  */
  @Override
  public AgentAccount lockBalance(AccountLockingDTO info) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/lock/balance", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(info);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, AgentAccount.class);
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService#unlockBalance(AccountLockingDTO)
  */
  @Override
  public AgentAccount unlockBalance(AccountLockingDTO info) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/unlock/balance", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(info);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, AgentAccount.class);
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.BalanceManagementEtopupService#recoverBalance(AccountLockingDTO)
  */
  @Override
  public AgentAccount recoverBalance(AccountLockingDTO info) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/recover/balance", EnvironmentProperties.getData("etopup_api_url"));

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(info);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new AgentAccount();
    }

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", encodedData);

    return restTemplate.postForObject(restUrl, formData, AgentAccount.class);
  }
}
