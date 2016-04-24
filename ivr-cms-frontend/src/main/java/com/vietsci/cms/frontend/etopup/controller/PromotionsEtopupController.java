package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.vietsci.cms.frontend.etopup.common.util.Constants.PromotionManagement;
import com.vietsci.cms.frontend.etopup.dto.PromotionDTO;
import com.vietsci.cms.frontend.etopup.model.PromotionEtopup;
import com.vietsci.cms.frontend.etopup.primefaces.PromotionEtopupDataModel;
import com.vietsci.cms.frontend.etopup.primefaces.PromotionEtopupLazyDataModel;
import com.vietsci.cms.frontend.etopup.service.PromotionEtopupService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

@Named
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class PromotionsEtopupController extends EtopupBaseController implements Serializable {

  private static final long serialVersionUID = 8100651247624187055L;

  /**
   * logger.
   */
  private static final Log logger = LogFactory.getLog(PromotionsEtopupController.class);

  @Inject
  PromotionEtopupService promotionService;

  private PromotionDTO promotionDTO = new PromotionDTO();
  private PromotionEtopupDataModel promotions;
  private PromotionEtopupLazyDataModel promotionsLazyDataModel;
  private PromotionEtopup promotion = new PromotionEtopup();
  private boolean stillShowAddPopup = true;
  private boolean isUpdatePromotion = false;
  private PromotionEtopup selectedPromotion;

  public boolean isStillShowAddPopup() {
    return stillShowAddPopup;
  }

  public void setStillShowAddPopup(boolean stillShowAddPopup) {
    this.stillShowAddPopup = stillShowAddPopup;
  }

  public PromotionDTO getPromotionDTO() {
    return promotionDTO;
  }

  public void setPromotionDTO(PromotionDTO promotionDTO) {
    this.promotionDTO = promotionDTO;
  }

  public PromotionEtopupDataModel getPromotions() {
    return promotions;
  }

  public void setPromotions(PromotionEtopupDataModel promotions) {
    this.promotions = promotions;
  }

  public PromotionEtopupLazyDataModel getPromotionsLazyDataModel() {
    return promotionsLazyDataModel;
  }

  public void setPromotionsLazyDataModel(PromotionEtopupLazyDataModel promotionsLazyDataModel) {
    this.promotionsLazyDataModel = promotionsLazyDataModel;
  }

  public PromotionEtopup getPromotion() {
    return promotion;
  }

  public void setPromotion(PromotionEtopup promotion) {
    this.promotion = promotion;
  }

  public boolean isUpdatePromotion() {
    return isUpdatePromotion;
  }

  public void setUpdatePromotion(boolean updatePromotion) {
    isUpdatePromotion = updatePromotion;
  }

  public PromotionEtopup getSelectedPromotion() {
    return selectedPromotion;
  }

  public void setSelectedPromotion(PromotionEtopup selectedPromotion) {
    this.selectedPromotion = selectedPromotion;
  }

/* ============= Business methods ============= */

  @PostConstruct
  public void init() {
    logger.debug("Prepare to init PromotionsEtopupController members");

    promotionDTO = new PromotionDTO();
    promotions = new PromotionEtopupDataModel(new ArrayList<PromotionEtopup>());
    promotionsLazyDataModel = new PromotionEtopupLazyDataModel(promotionService, promotionDTO);
    promotion = new PromotionEtopup();
    selectedPromotion = new PromotionEtopup();

    logger.debug("Init PromotionsEtopupController members successfully");
  }

  /**
   * Tìm kiếm các chương trình khuyến mại thỏa mãn điều kiện đầu vào
   */
  public void doFindPromotions() {
    logger.debug("Start doFind promotions");

    if (validatePromotionTimeForFinding()) {
      promotions = new PromotionEtopupDataModel(new ArrayList<PromotionEtopup>());
      promotionsLazyDataModel = new PromotionEtopupLazyDataModel(promotionService, promotionDTO);
      selectedPromotion = new PromotionEtopup();
    }

    logger.debug("End doFind promotions");
  }

  /**
   * Xử lí sự kiện người dùng click vào nút "Chấp nhận" trên màn hình Thêm mới/Chỉnh sửa
   * chương trình khuyến mại để lưu thông tin. Gọi đến hành động tương ứng là cập nhật hay
   * tạo mới chương trình khuyến mại.
   */
  public void doAcceptPromotionChanging() {
    if (isUpdatePromotion) {
      doUpdatePromotion();
    } else {
      doAddPromotion();
    }
  }

  /**
   * Thêm mới chương trình khuyến mại
   */
  private void doAddPromotion() {
    if (!validatePromotionForSaving()) {
      return;
    }

    boolean rs;
    try {
      rs = promotionService.doAddPromotion(promotion);
      resetPromotionAfterAdding();
    } catch (Exception e) {
      logger.error("Thêm mới chương trình khuyến mại với code/desc: (" + promotion.getCode()
        + "," + promotion.getDescription() + ") thất bại vì: " + e.getMessage());

      handleExceptionMessage(e, PromotionsEtopupController.class);
      return;
    }
    
    displayResultMessage(rs, MessageConstants.ETOPUP_PROMOTION_CREATE, ErrorConstants.ETOPUP_PROMOTION_CREATE);
  }

  private void resetPromotionAfterAdding(){
    this.promotion = new PromotionEtopup();
  }

  /**
   * Cập nhật chương trình khuyến mại
   */
  private void doUpdatePromotion() {
    if (!validatePromotionForSaving()) {
      return;
    }

    boolean rs;
    try {
      rs = promotionService.updatePromotion(promotion);
    } catch (Exception e) {
      logger.error("Cập nhật chương trình khuyến mại với id/code/desc: (" + promotion.getId() + "," + promotion.getCode()
        + "," + promotion.getDescription() + ") thất bại vì: " + e.getMessage());

      handleExceptionMessage(e, PromotionsEtopupController.class);
      return;
    }
    
    displayResultMessage(rs, MessageConstants.ETOPUP_PROMOTION_UPDATE, ErrorConstants.ETOPUP_PROMOTION_UPDATE);
  }

  /**
   * Xóa chương trình khuyến mại
   */
  public void doDeletePromotion() {
    boolean rs;
    try {
      rs = promotionService.deletePromotion(selectedPromotion.getId());
    } catch (Exception e) {
      logger.error("Xóa chương trình khuyến mại có id (" + selectedPromotion.getId() + ") thất bại vì lỗi: " + e.getMessage());
      handleExceptionMessage(e, PromotionsEtopupController.class);
      return;
    }

    displayResultMessage(rs, MessageConstants.ETOPUP_PROMOTION_DELETE, ErrorConstants.ETOPUP_PROMOTION_DELETE);
  }

  public void onBeforeUpdatePromotion(PromotionEtopup promotion) {
    this.promotion = promotion;
    this.isUpdatePromotion = true;
    this.stillShowAddPopup = false;
  }

  public void onBeforeAddPromotion() {
    this.promotion = new PromotionEtopup();
    this.isUpdatePromotion = false;
    this.stillShowAddPopup = true;
  }

  public void onBeforeDeletePromotion(PromotionEtopup selectedPromotion) {
    this.selectedPromotion = selectedPromotion;
  }

  private boolean validatePromotionTimeForFinding() {
    if (promotionDTO.getStaDate() != null
      && promotionDTO.getEndDate() != null
      && promotionDTO.getStaDate().after(promotionDTO.getEndDate())) {
      String message = getErrorMessage(ErrorConstants.ETOPUP_PROMOTION_TIME);
      FacesUtil.addErrorMsg(null, message, "");
      return false;
    }

    return true;
  }

  private boolean validatePromotionForSaving() {
    if (promotion == null) {
      logger.error("Đối tượng chương trình khuyến mại có giá trị null");
      return false;
    }

    if (StringUtils.isBlank(promotion.getCode())) {
      FacesUtil.addErrorMessage("newPromotionCode", PromotionManagement.PROMOTION_CODE_BLANK_MESSAGE);
      return false;
    } else if (StringUtils.isBlank(promotion.getDescription())) {
      FacesUtil.addErrorMessage("newDescription", PromotionManagement.PROMOTION_DESCRIPTION_BLANK_MESSAGE);
      return false;
    } else if (promotion.getStaDate() == null) {
      FacesUtil.addErrorMessage("newStartDate", PromotionManagement.START_TIME_ERROR_MESSAGE);
      return false;
    } else if (promotion.getEndDate() != null &&
      promotion.getStaDate().after(promotion.getEndDate())) {
      FacesUtil.addErrorMessage("newEndDate", PromotionManagement.TIME_VALIDATION_FAILED_MESSAGE);
      return false;
    }

    return true;
  }

  /**
   * Hiển thị thông báo thực hiện thao tác thành công hay thất bại lên màn hình
   * @param rs
   * @param successKey
   * @param failedKey
   */
  private void displayResultMessage(boolean rs, String successKey, String failedKey) {
    String message = null;
    if (rs) {
      message = getMessage(successKey);
      FacesUtil.addSuccessMessage(message);
      doFindPromotions();
    } else {
      message = getErrorMessage(failedKey);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }

}
