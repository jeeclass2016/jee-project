package com.vietsci.cms.frontend.etopup.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.NoSuchElementException;
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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.model.StockAgentMapBatch;
import com.vietsci.cms.frontend.etopup.model.StockAgentMapItem;
import com.vietsci.cms.frontend.etopup.service.EtopupStockAgentMappingBatchService;
import com.vietsci.cms.frontend.exception.CmsRestExceptionHandler;
import com.vietsci.cms.frontend.properties.EnvironmentProperties;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

@Service
public class EtopupStockAgentMappingBatchServiceImpl implements EtopupStockAgentMappingBatchService {

  private static final Log logger = LogFactory.getLog(EtopupStockAgentMappingBatchService.class);

  @Override
  public StockAgentMapBatch createStockAgentMappingBatch(UploadedFile uploadedFile) throws RestClientException, ResourceAccessException{
    RestTemplate restTemplate = new CmsRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String restUrl = String.format("%s/create/stock-agent-mapping/batch",
        EnvironmentProperties.getData("etopup_api_url"));

    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new CmsRestExceptionHandler());

    StockAgentMapBatch stockAgentBatch = null;
    try{
      stockAgentBatch = readFileToExtractData(uploadedFile);
      if (stockAgentBatch.hasErrorInReading()) {
        return stockAgentBatch;
      }
    }catch (IOException e) {
      logger.error(e.getMessage());
      return stockAgentBatch;
    }
    try {
      String data = mapper.writeValueAsString(stockAgentBatch);
      String encodedData = URLEncoder.encode(data, Constants.ENCODING_UTF8);

      MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
      formData.add("data", encodedData);
      StockAgentMapBatch errorItems = restTemplate.postForObject(restUrl, formData, StockAgentMapBatch.class);
      return errorItems;
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
      return null;
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage());
      return null;
    } 
  }

  private StockAgentMapBatch readFileToExtractData(UploadedFile uploadedFile) throws IOException {
    StockAgentMapBatch stockAgentMapBatch = new StockAgentMapBatch();

    Set<StockAgentMapItem> goodStockAgentMapItems = new HashSet<StockAgentMapItem>(0);
    Set<StockAgentMapBatch.BadItemInReading> badItemsInReading = new HashSet<StockAgentMapBatch.BadItemInReading>(0);
    String currentLine = null;

    try (Scanner scanner = new Scanner(uploadedFile.getInputstream(), StandardCharsets.UTF_8.name())) {
      // above the first line (header line)
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }
      while (scanner.hasNextLine()) {
        currentLine = scanner.nextLine();
        try {
          StockAgentMapItem stockAgentMapItem = processLine(currentLine);
          if (stockAgentMapItem != null) {
            if (!goodStockAgentMapItems.contains(stockAgentMapItem)) {
              goodStockAgentMapItems.add(stockAgentMapItem);
            } else {
              badItemsInReading.add(stockAgentMapBatch.new BadItemInReading(currentLine,
                  Constants.StockAgentMappingByBatch.ITEM_DUPPLICATED));
            }
          }
        } catch (NoSuchElementException e) {
          badItemsInReading.add(stockAgentMapBatch.new BadItemInReading(currentLine,
              Constants.StockAgentMappingByBatch.ITEM_WRONG_SYNTAX_OR_FIELD_MISSING));
        }
      }
    }
    stockAgentMapBatch.setBadItemsInReading(badItemsInReading);
    stockAgentMapBatch.setGoodStockAgentMapItems(goodStockAgentMapItems);
    return stockAgentMapBatch;
  }

  @SuppressWarnings("resource")
  private StockAgentMapItem processLine(String aLine) throws NoSuchElementException {
    StockAgentMapItem stockAgentMapItem = null;
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(Constants.FileManagement.COMMA_DELIMITER);

    if (scanner.hasNext()) {
      String msisdn = scanner.next().trim();
      String iccid = scanner.next().trim();
      String shopCode = scanner.next().trim();
      String staffCode = null;
      if (scanner.hasNext()) {
        staffCode = scanner.next().trim();
      }
      stockAgentMapItem = new StockAgentMapItem();
      stockAgentMapItem.setMsisdn(msisdn);
      stockAgentMapItem.setIccid(iccid);
      stockAgentMapItem.setShopCode(shopCode);
      stockAgentMapItem.setStaffCode(staffCode);
    }
    return stockAgentMapItem;
  }

}
