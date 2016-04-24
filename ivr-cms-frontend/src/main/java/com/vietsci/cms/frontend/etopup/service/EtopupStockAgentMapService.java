package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import com.vietsci.cms.frontend.etopup.dto.StockAgentMapDTO;
import com.vietsci.cms.frontend.etopup.model.StockAgentMap;

public interface EtopupStockAgentMapService {
  /**
   * Todo: create new StockAgentMap
   * Return type:StockAgentMap
   * Created by: quanglh
   * Created date: Apr 23, 2014 6:00:46 PM
   */
  public StockAgentMap create(StockAgentMapDTO newStockAgentMap) throws RestClientException, ResourceAccessException;
  
  /**
   * Todo: update an existed StockAgentMap
   * Return type:Boolean
   * Created by: quanglh
   * Created date: Apr 23, 2014 6:00:59 PM
   */
  public Boolean update(StockAgentMapDTO newStockAgentMap) throws RestClientException, ResourceAccessException;
  /**
   * Todo: delete an existed StockAgentMap
   * Return type:Boolean
   * Created by: quanglh
   * Created date: Apr 23, 2014 6:01:22 PM
   */
  public Boolean delete(StockAgentMapDTO stockAgentMap) throws RestClientException, ResourceAccessException;
  /**
   * Todo: get all StockAgentMap by agentId
   * Return type:List<StockAgentMap>
   * Created by: quanglh
   * Created date: Apr 23, 2014 6:01:41 PM
   */
  public List<StockAgentMap> getAllStockAgentMapByAgentId(StockAgentMapDTO stockAgentMapDTO) throws RestClientException, ResourceAccessException;
  
  public Long countAllStockAgentMapByAgentId(StockAgentMapDTO agentId) throws RestClientException, ResourceAccessException;
}
