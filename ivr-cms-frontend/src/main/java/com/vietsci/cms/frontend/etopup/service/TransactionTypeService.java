package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vietsci.cms.frontend.etopup.dto.TransactionTypeDTO;
import com.vietsci.cms.frontend.etopup.model.TransactionType;
import com.vietsci.cms.frontend.exception.CmsRestException;

/** 
 * Interface for communicating with eTopUp API to process with TransactionType.
 * 
 * @author trung.doduc
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public interface TransactionTypeService {

  /**
  * Tim kiem danh muc giao dich.
  * @param   transactionTypeDTO       chua cac thong tin tim kiem: ma GD, mo ta GD hay trang thai.
  * @return  danh sach cac giao dich thoa man
  */
  public List<TransactionType> getTransactionTypeList(TransactionTypeDTO transactionTypeDTO) throws ResourceAccessException;
  
  /**
   * Tao moi hoac cap nhat danh muc giao dich.
   * @param   transactionType       chua cac thong tin tim kiem: ma GD, mo ta GD hay trang thai.
   * @return  giao dich thoa man
   */
  public TransactionType saveUpdateTransactionType(TransactionType transType) throws ResourceAccessException, CmsRestException;
  
  /**
   * Tim kiem danh muc giao dich theo id
   * @param   id       id cua GD
   * @return  giao dich thoa man
   */
  public TransactionType getTransactionType(long id) throws ResourceAccessException;
  
  /**
   * Vo hieu hoa giao dich.
   * @param    reasonId        id cua giao dich can xoa
   * @return   null object     trong truong hop ko xoa thanh cong
   *           not null object trong truong hop xoa thanh cong
   */
  public void deleteTransactionType(long id) throws CmsRestException, ResourceAccessException;

  /**
   * Tinh tong so ban ghi ly do theo dieu kien tim kiem.
   * @param transactionTypeDTO
   * @return
   */
  public int countTotalRecord(TransactionTypeDTO transactionTypeDTO);
}
