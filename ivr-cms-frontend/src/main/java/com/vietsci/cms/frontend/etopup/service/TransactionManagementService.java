package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import org.springframework.web.client.ResourceAccessException;

import com.vietsci.cms.frontend.etopup.dto.TLDTransBatchDTO;
import com.vietsci.cms.frontend.etopup.dto.TLDTransDTO;
import com.vietsci.cms.frontend.etopup.dto.TransactionManagementDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;

/** 
 * Interface for communicating with eTopUp API to process with transaction managment.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public interface TransactionManagementService {

  /**
  * Tim kiem thong tin giao dich.
  * @param   transactionManagementDTO    chua thong tin tim kiem.
  * @return  danh sach cac giao dich thoa man
  */
  public List<Trans> getTransactionList(TransactionManagementDTO transactionManagementDTO) throws ResourceAccessException;
  


  /**
   * Tinh tong so ban ghi theo dieu kien tim kiem.
   * @param transactionManagementDTO
   * @return
   */
  public int countTotalRecord(TransactionManagementDTO transactionManagementDTO);
  
  
  /**
   * Todo: find MPOS trans
   * Return type:List<Trans>
   * Created by: quanglh
   * Created date: May 8, 2014 5:19:10 PM
   */
  public List<Trans> findTransForMposLookup(TransactionManagementDTO dto) throws ResourceAccessException;
  
  
  
  /**
   * Todo: count MPOS trans
   * Return type:Long
   * Created by: quanglh
   * Created date: May 8, 2014 5:20:03 PM
   */
  public Long countTransForMposLookup(TransactionManagementDTO dto) throws ResourceAccessException;


  public List<Trans> findBonusStatus(TransactionManagementDTO dto) throws ResourceAccessException;
  
  public List<Trans> findTLDPTrans(TransactionManagementDTO dto);
  
  public int countTLDPTrans(TransactionManagementDTO dto);
  
  public Boolean createTLDTrans(TLDTransDTO tldTransDTO);
  
  public Boolean completeTLDTransBatch(TLDTransBatchDTO batchDTO);
  
  public Boolean rejectTLDTransBatch(TLDTransBatchDTO batchDTO);
}
