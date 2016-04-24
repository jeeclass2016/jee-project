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
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.dto.SIMBatchDTO;
import com.vietsci.cms.frontend.etopup.model.LoadedSim;
import com.vietsci.cms.frontend.etopup.model.SimBatch;
import com.vietsci.cms.frontend.etopup.service.SIMBatchEtopupService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

/** 
 * Implementation details for communicating with eTopUp API to process with SimBatch.
 * 
 * @author lam.lethanh
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
@Service
public class SIMBatchEtopupServiceImpl implements SIMBatchEtopupService {

  private static final Log logger = LogFactory.getLog(SIMBatchEtopupService.class);

  /**
   * {@inheritDoc}
   */
  public List<SimBatch> findSIMbatchs(SIMBatchDTO searchCriteria) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();

    try {
      String data = mapper.writeValueAsString(searchCriteria);
      String restUrl = String.format("%s/find-simBatchs/%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));
      return Arrays.asList(restTemplate.getForObject(restUrl, SimBatch[].class));
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return new ArrayList<SimBatch>();
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean createSIMBatch(UploadedFile uploadedFile) {
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/create-simbatch", EnvironmentProperties.getData("etopup_api_url"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    String data;
    String encodedData = null;
    SimBatch simBatch;
    
    try {
      simBatch = readFileToExtractData(uploadedFile);
      data = mapper.writeValueAsString(simBatch);
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
    formData.add("simBatchData", encodedData);
    boolean result = restTemplate.postForObject(restUrl, formData, Boolean.class);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public int countTotalRecord(SIMBatchDTO searchCriteria) {
    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());
    ObjectMapper mapper = new ObjectMapper();

    try {
      String data = mapper.writeValueAsString(searchCriteria);
      String restUrl = String.format("%s/count-simBatchs/%s", EnvironmentProperties.getData("etopup_api_url"),
          URLEncoder.encode(data, Constants.ENCODING_UTF8));

      Long rs = restTemplate.getForObject(restUrl, Long.class);
      return rs.intValue();

    } catch (Exception ex) {
      logger.error(ex.getMessage());
      return 0;
    }
  }
  
  private SimBatch readFileToExtractData(UploadedFile uploadedFile) throws IOException {
    Long entries = 0L;
    Date createdDate = new Date();
    SimBatch simBatch = new SimBatch();
    simBatch.setFileName(uploadedFile.getFileName());
    simBatch.setCreateDate(createdDate);
    
    Set<LoadedSim> loadedSims = new HashSet<LoadedSim>();
    LoadedSim loadedSim;
    
    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name()))
    {
      // Bỏ qua dòng đầu tiên vì có nội dung là tiêu đề của các dòng tiếp theo
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()){
        loadedSim = processLine(scanner.nextLine());
        loadedSims.add(loadedSim);
        entries ++;
      }
    }
    simBatch.setLoadedSims(loadedSims);
    simBatch.setEntries(entries);
    return simBatch;
  }
  
  @SuppressWarnings("resource")
  private LoadedSim processLine(String aLine){
    LoadedSim loadedSim = null;
    Date createdDate = new Date();
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);
    
    if (scanner.hasNext()){
      String iccid = scanner.next();
      String msisdn = scanner.next();
      
      loadedSim = new LoadedSim();
      loadedSim.setIccid(iccid);
      loadedSim.setMsisdn(msisdn);
      loadedSim.setCreated(createdDate);
    } 
    
    return loadedSim;
  }

}
