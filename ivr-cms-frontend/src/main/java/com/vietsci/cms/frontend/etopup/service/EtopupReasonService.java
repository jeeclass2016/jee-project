/**
 * 
 */
package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import com.vietsci.cms.frontend.etopup.dto.ReasonDTO;
import com.vietsci.cms.frontend.etopup.model.Reason;

/** 
 * Interface for communicating with eTopUp API to process with Reasons.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public interface EtopupReasonService {
  
  /**
  * Tim kiem danh muc ly do.
  * @param   reasonDTO       chua cac thong tin tim kiem: ma ly do, mo ta ly do hay trang thai.
  * @return  danh sach cac ly do thoa man
  */
  public List<Reason> findReasons(ReasonDTO reasonDTO);
  
  /**
  * Vo hieu hoa ly do.
  * @param    reasonId        id cua ly do can xoa
  * @return   null object     trong truong hop ko xoa thanh cong
  *           not null object trong truong hop xoa thanh cong
  */
  public void deleteReason(Long reasonId);
  
  /**
   * Them moi ly do.
   * 
   * @param reason
   * @return
   */
  public boolean createReason(Reason reason);
  
  /**
   * Sua thong tin ly do.
   * @param reason
   * @return
   */
  public boolean updateReason(Reason reason);
  
  /**
   * Tinh tong so ban ghi ly do theo dieu kien tim kiem.
   * @param reasonDTO
   * @return
   */
  public int countTotalRecord(ReasonDTO reasonDTO);
}
