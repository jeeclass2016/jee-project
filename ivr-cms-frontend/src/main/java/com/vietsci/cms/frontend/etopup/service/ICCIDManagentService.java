package com.vietsci.cms.frontend.etopup.service;

import com.vietsci.cms.frontend.etopup.dto.ICCIDRangeDeletionDTO;
import com.vietsci.cms.frontend.etopup.dto.LoadedSIMDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.LoadedSim;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Service class for ICCID Management
 *
 * @author hong.nguyenmai
 *         <p/>
 *         Copyright © 2014 HTC
 *         $LastChangedRevision: $
 *         $LastChangedDate: $
 *         $LastChangedBy: $
 */
public interface ICCIDManagentService {

  /**
   * Find ICCIDs with some criteria
   *
   * @param iccidRangeDeletionDTO search criteria
   * @return a list of ICCIDs (one page) that satisfy the search criteria
   * @throws RestClientException
   * @author hong.nguyenmai
   */
  public List<LoadedSim> findICCIDs(ICCIDRangeDeletionDTO iccidRangeDeletionDTO) throws RestClientException;

  /**
   * Count the total number of ICCIDs that satisfy the search criteria
   *
   * @param iccidRangeDeletionDTO search criteria
   * @return the total number of records
   * @throws RestClientException
   * @author hong.nguyenmai
   */
  public Long countICCIDs(ICCIDRangeDeletionDTO iccidRangeDeletionDTO) throws RestClientException;

  /**
   * Delete a list of ICCIDs
   *
   * @param iccidIdList list of ICCID Ids
   * @return result after deletion, SUCCESS or FAIL
   * @throws RestClientException
   * @author hong.nguyenmai
   */
  public boolean deleteICCIDs(List<Long> iccidIdList) throws RestClientException;

  public Boolean changeIccidOfAgent(Agent agent)throws RestClientException, ResourceAccessException;
}
