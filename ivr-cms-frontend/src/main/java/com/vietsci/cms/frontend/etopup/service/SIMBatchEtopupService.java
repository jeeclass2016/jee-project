package com.vietsci.cms.frontend.etopup.service;

import java.util.List;

import org.primefaces.model.UploadedFile;

import com.vietsci.cms.frontend.etopup.dto.SIMBatchDTO;
import com.vietsci.cms.frontend.etopup.model.SimBatch;

/** 
 * Interface for communicating with eTopUp API to process with SIMBatch.
 * 
 * @author XXX
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $ 
 */
public interface SIMBatchEtopupService {

  /**
  * Tim kiem danh muc SimBatch.
  * @param   simBundleUploadingDTO       chua cac thong tin tim kiem
  * @return  danh sach cac SimBatch thoa man
  */
  public List<SimBatch> findSIMbatchs(SIMBatchDTO simBatchDTO);

  /**
   * Them moi SimBatch.
   * 
   * @param   simBatch
   * @return  false     trong truong hop tao moi khong thanh cong
  *           true trong truong hop tao moi thanh cong
   */
  public boolean createSIMBatch(UploadedFile uploadedFile);

  /**
   * Tinh tong so ban ghi SimBatch theo dieu kien tim kiem.
   * @param simBatchDTO
   * @return so luong ban ghi thoa man dieu kien tim kiem
   */
  public int countTotalRecord(SIMBatchDTO simBatchDTO);
}
