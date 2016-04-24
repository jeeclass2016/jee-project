package com.vietsci.cms.frontend.etopup.service;

import com.vietsci.cms.frontend.etopup.dto.AccountLockingDTO;
import com.vietsci.cms.frontend.etopup.dto.EtopupTransLookupDTO;
import com.vietsci.cms.frontend.etopup.dto.TransDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;

import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Service for Trans Management
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public interface TransManagementEtopupService {

  /**
   * Find trans with some criteria
   * @param searchCriteria
   * @return a list of trans which satisfy criteria
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public List<Trans> findTrans(AccountLockingDTO searchCriteria) throws RestClientException;

  /**
   * Count the total number of trans with some criteria
   * @param searchCriteria
   * @return the total number
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public Long countTrans(AccountLockingDTO searchCriteria) throws RestClientException;

  /**
   * Find trans with some criteria from Etopup Transaction Look up page
   * @param searchCriteria
   * @return a list of trans which satisfy criteria
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public List<Trans> findTrans(EtopupTransLookupDTO searchCriteria) throws RestClientException;

  /**
   * Count the total number of trans with some criteria from Etopup Transaction Look up page
   * @param searchCriteria
   * @return the total number
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public Long countTrans(EtopupTransLookupDTO searchCriteria) throws RestClientException;
  
  /**
   * Transfer money between SHTs
   * @param transDTO
   * @return
   */
  public Boolean transferMoneyBetweenSHTs(TransDTO transDTO) throws RestClientException;
}
