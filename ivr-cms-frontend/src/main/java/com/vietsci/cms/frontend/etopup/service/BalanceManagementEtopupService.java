package com.vietsci.cms.frontend.etopup.service;

import org.primefaces.model.UploadedFile;

import com.vietsci.cms.frontend.etopup.dto.AccountInfoSearchingDTO;
import com.vietsci.cms.frontend.etopup.dto.AccountLockingDTO;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentBatchDTO;
import com.vietsci.cms.frontend.etopup.dto.BalanceAdjustmentDTO;
import com.vietsci.cms.frontend.etopup.model.AgentAccount;

import org.springframework.web.client.RestClientException;

/**
 * Service class for Balance Management
 *
 * @author hong.nguyenmai
 *
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $
 */
public interface BalanceManagementEtopupService {

  /**
   * Adjust balance
   * @param adjustmentInfo balance adjustment info
   * @return true if SUCCESS, false if FAIL
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public boolean adjustBalance(BalanceAdjustmentDTO adjustmentInfo) throws RestClientException;

  public boolean createBalanceAdjustmentBatch(BalanceAdjustmentBatchDTO balanceAdjustmentBatchDTO) throws RestClientException;

  /**
   * Find Account info
   * @param searchCriteria search criteria to find
   * @return agent account info
   * @throws RestClientException
   *
   * @author hong.nguyenmai
   */
  public AgentAccount findAccountInfo(AccountInfoSearchingDTO searchCriteria) throws RestClientException;

  public AgentAccount lockBalance(AccountLockingDTO info) throws RestClientException;

  public AgentAccount unlockBalance(AccountLockingDTO info) throws RestClientException;

  public AgentAccount recoverBalance(AccountLockingDTO info) throws RestClientException;
}
