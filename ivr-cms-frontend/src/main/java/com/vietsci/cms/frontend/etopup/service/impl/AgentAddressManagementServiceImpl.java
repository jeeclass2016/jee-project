package com.vietsci.cms.frontend.etopup.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.UploadedFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.AgentAddressMgmtDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.AgentAddressManagement;
import com.vietsci.cms.frontend.etopup.model.TblMapDistrict;
import com.vietsci.cms.frontend.etopup.model.TblMapProvince;
import com.vietsci.cms.frontend.etopup.service.AgentAddressManagementService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

/** 
 * Implementation details for communicating with eTopUp API to process with Agent Address Management.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Service
public class AgentAddressManagementServiceImpl implements AgentAddressManagementService {
  
  private static final Log logger = LogFactory.getLog(AgentAddressManagementService.class);

  @Override
  public List<AgentAddressManagement> findAgentAddressList(AgentAddressMgmtDTO agentAddressMgmtDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(agentAddressMgmtDTO);
      String restUrl = String.format("%s/find-agentAddressList/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, AgentAddressManagement[].class));
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return null;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return null;
    }
    
  }

  @Override
  public int countTotalRecord(AgentAddressMgmtDTO agentAddressMgmtDTO) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(agentAddressMgmtDTO);
      String restUrl = String.format("%s/count-agentAddressList/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }
  
  @Override
  public int countAgentByMSISDN(String msisdn) {
    RestTemplate restTemplate = new CmsRestTemplate();
    try {
      String restUrl = String.format("%s/count-agentByMSISDN/%s", EnvironmentProperties.getData("etopup_api_url"), msisdn);
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }

  
  @Override
  public List<TblMapProvince> getAvailableProvinceList(String msisdn) {
    RestTemplate restTemplate = new CmsRestTemplate();
    try {
      String restUrl = String.format("%s/find-provinceList/%s", EnvironmentProperties.getData("etopup_api_url"), msisdn);
      return Arrays.asList(restTemplate.getForObject(restUrl, TblMapProvince[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<TblMapProvince>();
    }
  }
  
  @Override
  public List<TblMapProvince> getAvailableProvinceList(Long parentId) {
    RestTemplate restTemplate = new CmsRestTemplate();
    try {
      parentId = (parentId == null) ? 0L : parentId;
      String restUrl = String.format("%s/find-provinceListByParent/%s", EnvironmentProperties.getData("etopup_api_url"), parentId);
      return Arrays.asList(restTemplate.getForObject(restUrl, TblMapProvince[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<TblMapProvince>();
    }
  }

  @Override
  public List<TblMapDistrict> getAvailableDistrictList(String msisdn) {
    RestTemplate restTemplate = new CmsRestTemplate();
    try {
      String restUrl = String.format("%s/find-districtList/%s", EnvironmentProperties.getData("etopup_api_url"), msisdn);
      return Arrays.asList(restTemplate.getForObject(restUrl, TblMapDistrict[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<TblMapDistrict>();
    }
  }
  
  @Override
  public List<TblMapDistrict> getAvailableDistrictList() {
    RestTemplate restTemplate = new CmsRestTemplate();
    try {
      String restUrl = String.format("%s/find-all-districts", EnvironmentProperties.getData("etopup_api_url"));
      return Arrays.asList(restTemplate.getForObject(restUrl, TblMapDistrict[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<TblMapDistrict>();
    }
  }
  
  @Override
  public List<TblMapDistrict> getAvailableDistrictList(List<String> provinceIdList) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String data = mapper.writeValueAsString(provinceIdList);
      String restUrl = String.format("%s/find-all-districts/%s", EnvironmentProperties.getData("etopup_api_url"), data);
      return Arrays.asList(restTemplate.getForObject(restUrl, TblMapDistrict[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<TblMapDistrict>();
    }
  }

  @Override
  public List<Agent> getAgentByMSISDN(String msisdn) {
    RestTemplate restTemplate = new CmsRestTemplate();
    String restUrl = String.format("%s/find-agentByMSISDN/%s", EnvironmentProperties.getData("etopup_api_url"), msisdn);
    List<Agent> rs = Arrays.asList(restTemplate.getForObject(restUrl, Agent[].class));
    return rs;
  }

  @Override
  public Boolean createAgentAddress(AgentAddressManagement agentAddressManagement) {
    RestTemplate restTemplate = new CmsRestTemplate();
    
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/create-agentAddress", EnvironmentProperties.getData("etopup_api_url"));
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));
    
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(agentAddressManagement);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }
      
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("objectData", encodedData);
    
    Boolean rs = restTemplate.postForObject(restUrl, formData, Boolean.class);
    return rs;
  }

  @Override
  public Boolean updateAgentAddress(AgentAddressManagement agentAddressManagement) {
    RestTemplate restTemplate = new CmsRestTemplate();
    
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/update-agentAddress", EnvironmentProperties.getData("etopup_api_url"));
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));
    
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    
    String data;
    String encodedData = null;
    try {
      data = mapper.writeValueAsString(agentAddressManagement);
      encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return false;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return false;
    }
      
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("objectData", encodedData);
    
    Boolean rs = restTemplate.postForObject(restUrl, formData, Boolean.class);
    return rs;
  }

  @Override
  public Boolean createAgentAddressBatch(UploadedFile uploadedFile) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/create-agentAddressBatch", EnvironmentProperties.getData("etopup_api_url"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    Set<AgentAddressManagement> agentAddressSet;
    
    try {
      agentAddressSet = readFileToExtractData(uploadedFile);
      data = mapper.writeValueAsString(agentAddressSet);
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
    formData.add("batchData", encodedData);
    Boolean result = restTemplate.postForObject(restUrl, formData, Boolean.class);
    return result;
  }

  
  private Set<AgentAddressManagement> readFileToExtractData(UploadedFile uploadedFile) throws IOException {
    Set<AgentAddressManagement> agentAddressSet = new HashSet<AgentAddressManagement>();
    AgentAddressManagement agentAddressManagement;
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name())) {
      // Skip first line
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()){
        agentAddressManagement = processLine(scanner.nextLine());
        agentAddressSet.add(agentAddressManagement);
      }
    }
    return agentAddressSet;
  }
  
  @SuppressWarnings("resource")
  private AgentAddressManagement processLine(String aLine){
    AgentAddressManagement agentAddressManagement = null;
    Date createdDate = new Date();
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
    
    if (scanner.hasNext()){
      String msisdn = scanner.next();
      String province = scanner.next();
      String district = scanner.next();
      String map = "0";
      if (scanner.hasNext()) {
        map = scanner.next();
      }
      
      agentAddressManagement = new AgentAddressManagement();
      agentAddressManagement.setMsisdn(msisdn);
      agentAddressManagement.setDistrict(district.trim());
      agentAddressManagement.setProvince(province.trim());
      agentAddressManagement.setCreateDate(createdDate);
      agentAddressManagement.setStatus(map);
    } 
    
    return agentAddressManagement;
  }

  @Override
  public int countAddressData(String province, String district) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();
    try {
      AgentAddressMgmtDTO agentAddressMgmtDTO = new AgentAddressMgmtDTO();
      agentAddressMgmtDTO.setProvince(province.trim());
      agentAddressMgmtDTO.setDistrict(district.trim());
      String data = mapper.writeValueAsString(agentAddressMgmtDTO);
      String restUrl = String.format("%s/count-addressData/%s", EnvironmentProperties.getData("etopup_api_url"), URLEncoder.encode(data, Constants.ENCODING_UTF8));
      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }

}
