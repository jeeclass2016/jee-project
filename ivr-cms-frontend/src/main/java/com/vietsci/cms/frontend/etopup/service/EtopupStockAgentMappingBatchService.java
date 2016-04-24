package com.vietsci.cms.frontend.etopup.service;

import org.primefaces.model.UploadedFile;

import com.vietsci.cms.frontend.etopup.model.StockAgentMapBatch;

/** 
 * Interface for communicating with eTopUp API to process with SIMBatch.
 * 
 * @author quanglh
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $ 
 */
public interface EtopupStockAgentMappingBatchService {

  /**
   * Todo: create StockAgentMappingBatch and upload that batch server
   * Return type:boolean
   * Created by: quanglh
   * Created date: Apr 26, 2014 3:19:18 PM
   */
  public StockAgentMapBatch createStockAgentMappingBatch(UploadedFile uploadedFile);
}
