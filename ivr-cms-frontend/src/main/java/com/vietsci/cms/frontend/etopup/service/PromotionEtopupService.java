package com.vietsci.cms.frontend.etopup.service;

import com.vietsci.cms.frontend.etopup.dto.BatchPromotionDTO;
import com.vietsci.cms.frontend.etopup.dto.PromotionDTO;
import com.vietsci.cms.frontend.etopup.model.Agent;
import com.vietsci.cms.frontend.etopup.model.PromotionEtopup;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for communicating with eTopUp API to process with PromotionEtopup.
 *
 * @author lam.lethanh
 *         Copyright © 2014 HTC
 *         $LastChangedRevision: $
 *         $LastChangedDate: $
 */
public interface PromotionEtopupService {
  /**
   * Tìm kiếm chương trình khuyến mại dựa trên search criterias.
   *
   * @param promotionDTO chứa các thông tin để tìm kiếm
   * @return List<PromotionEtopup> danh sách các chương trình khuyến mại thỏa mãn điều kiện tìm kiếm
   */
  public List<PromotionEtopup> doFindPromotions(PromotionDTO promotionSearchCriteria);

  /**
   * Thêm chương trình khuyến mại dựa trên dữ liệu mới.
   *
   * @param promotion chứa thông tin của chương trình khuyến mại mới sẽ được thêm vào database
   */
  public boolean doAddPromotion(PromotionEtopup promotion);

  /**
   * Cập nhật chương trình khuyến mại dựa trên dữ liệu mới.
   *
   * @param promotion chứa thông tin của chương trình khuyến mại mới sẽ được cập nhật trong database
   */
  public boolean updatePromotion(PromotionEtopup promotion);

  /**
   * Xóa chương trình khuyến mại dựa trên dữ liệu ID của chương trình khuyến mại.
   *
   * @param promotionId ID của chương trình khuyến mại.
   */
  public boolean deletePromotion(BigDecimal promotionId);
  
  
  /**
   * Tính tổng số bản ghi trong database có các trường dữ liệu phù hợp với các điều kiện tìm kiếm
   * @param promotionDTO
   * @return
   */
  public int countTotalRecord(PromotionDTO promotionSearchCriteria);

  /**
   * Tìm kiếm chương trình khuyến mại dang o trang thai hieu luc.
   *
   * @return List<PromotionEtopup> danh sách các chương trình khuyến mại
   */
  public List<PromotionEtopup> findActivePromotions();
  
  public Agent getOperatingActiveAgentByMSISDN(String msisdn);
  
  public Boolean processPromotionBatch(BatchPromotionDTO batchPromotionDTO);
}
