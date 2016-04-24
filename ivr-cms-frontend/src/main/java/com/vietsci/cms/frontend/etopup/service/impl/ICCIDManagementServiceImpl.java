package com.vietsci.cms.frontend.etopup.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.EtopupStringUtil;
import com.vietsci.cms.frontend.etopup.dto.ICCIDRangeDeletionDTO;
import com.vietsci.cms.frontend.etopup.dto.LoadedSIMDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.LoadedSim;
import com.vietsci.cms.frontend.etopup.service.ICCIDManagentService;
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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ICCIDManagementServiceImpl implements ICCIDManagentService {

  /* logger */
  private static final Log logger = LogFactory.getLog(ICCIDManagentService.class);

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.ICCIDManagentService#findICCIDs(ICCIDRangeDeletionDTO)
  */
  @Override
  public List<LoadedSim> findICCIDs(ICCIDRangeDeletionDTO iccidRangeDeletionDTO) throws RestClientException {

    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP webservice de tim kiem ICCID");

    try {
      String data = objectMapper.writeValueAsString(iccidRangeDeletionDTO);
      data = EtopupStringUtil.enCodeGetData(data);
      String restUrl = String.format("%s/find/iccid/iccidRangeDeletion/%s",
              EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, LoadedSim[].class));

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return new ArrayList<LoadedSim>();

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return new ArrayList<LoadedSim>();
    }
  }

  /*
  * (non-Javadoc)
  * @see com.vietsci.cms.frontend.etopup.service.ICCIDManagentService#countICCIDs(ICCIDRangeDeletionDTO)
  */
  @Override
  public Long countICCIDs(ICCIDRangeDeletionDTO iccidRangeDeletionDTO) throws RestClientException {

    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    logger.debug("Gui du lieu den ETOPUP webservice de tinh tong so cac ICCID");

    try {
      String data = objectMapper.writeValueAsString(iccidRangeDeletionDTO);
      data = EtopupStringUtil.enCodeGetData(data);
      String restUrl = String.format("%s/count/iccid/iccidRangeDeletion/%s",
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
  * @see com.vietsci.cms.frontend.etopup.service.ICCIDManagentService#deleteICCIDs(List<Long>)
  */
  public boolean deleteICCIDs(List<Long> iccidIdList) throws RestClientException {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    ObjectMapper objectMapper = new ObjectMapper();

    logger.debug("Gui du lieu den ETOPUP web service de xoa dai ICCID");

    try {
      String data = objectMapper.writeValueAsString(iccidIdList);
      String restUrl = String.format("%s/delete/iccid/iccidRangeDeletion/%s",
              EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      restTemplate.delete(restUrl);
      return true;

    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;

    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }
  }

  /* (non-Javadoc)
   * @see com.vietsci.cms.frontend.etopup.service.AgentManagementService#changeIccidAndMsisdnOfAgent(com.vietsci.cms.frontend.etopup.model.Agent)
   */
  public Boolean changeIccidOfAgent(Agent agent)throws RestClientException, ResourceAccessException {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/changeIccId/agent/", EnvironmentProperties.getData("etopup_api_url"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(agent);
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
