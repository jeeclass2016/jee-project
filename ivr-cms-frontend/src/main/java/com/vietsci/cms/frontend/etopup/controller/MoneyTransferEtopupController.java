package com.vietsci.cms.frontend.etopup.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.etopup.dto.TransDTO;
import com.vietsci.cms.frontend.etopup.service.TransManagementEtopupService;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;
import com.vietsci.cms.frontend.util.MessageConstants;

@Named
@Scope(value="session")
public class MoneyTransferEtopupController extends EtopupBaseController implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4147720339980150577L;
  
  /* logger */
  private static final Log logger = LogFactory.getLog(MoneyTransferEtopupController.class);

  @Inject
  private TransManagementEtopupService transManagemenService;
  
  private TransDTO transDTO;
  /* ============= Getter and setter methods ============= */

  public TransDTO getTransDTO() {
    return transDTO;
  }

  public void setTransDTO(TransDTO transDTO) {
    this.transDTO = transDTO;
  }
  
  /* ============= Business methods ============= */
  @PostConstruct
  public void init() {
    logger.debug("Prepare to init MoneyTransferEtopupController members");
    transDTO = new TransDTO();
    logger.debug("Init MoneyTransferEtopupController members successfully");
  }

  /**
   * Thực hiện giao dịch chuyển tiền
   */
  public void doTransferMoney() {
    logger.debug("Transfer money from: " + transDTO.getSourceMsisdn());
    logger.debug("To: " + transDTO.getTargetMsisdn());
    logger.debug("Transfer Amount: " + transDTO.getTransAmount());
    
    boolean rs = false;
    if (transDTO.getSourceMsisdn().equals(transDTO.getTargetMsisdn())) {
      displayResultMessage(rs, null, ErrorConstants.ETOPUP_MONEY_TRANSACTION_SOURCE_MSISDN_AND_TARGET_MSISDN_SAME);
      return;
    }
    
    try {
      rs = transManagemenService.transferMoneyBetweenSHTs(transDTO);
    } catch (Exception e) {
      logger.error("Chuyển tiền thất bại: "+ e.getMessage());

      handleExceptionMessage(e, MoneyTransferEtopupController.class);
      return;
    }
    
    displayResultMessage(rs, MessageConstants.ETOPUP_MONEY_TRANSFER, ErrorConstants.ETOPUP_MONEY_TRANSFER);
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
    } else {
      message = getErrorMessage(failedKey);
      FacesUtil.addErrorMsg(null, message, "");
    }
  }


}
