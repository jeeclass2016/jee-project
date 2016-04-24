package com.vietsci.cms.frontend.main.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.main.model.Member;
import com.vietsci.cms.frontend.util.Constants;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;

/**
 * Base controller 
 * 
 */
public class BaseController implements Serializable {

  private static final long serialVersionUID = -5749111103652847936L;
  
  @Inject @Named LanguageController languageController;
  
  /**
   * Lấy member đang đăng nhập
   * 
   * @return thông tin member đang đăng nhập
   */
  protected Member getCurrentMember() {
    AuthorizationController auth = (AuthorizationController) (FacesContext.getCurrentInstance().getExternalContext())
        .getSessionMap().get("auth");
    if (auth != null) {
      return auth.getMember();
    }
    return null;
  }
  
  /**
   * Lấy nội dung của error message dựa vào key
   * 
   * @param key trong file *.properties
   * @return nội dung lỗi
   */
  protected String getErrorMessage(String key) {
    return getMessage(Constants.ERROR_MESSAGE, key);
  }
  
  /**
   * Lấy nội dung của  message dựa vào key
   * 
   * @param key trong file *.properties
   * @return nội dung message
   */
  protected String getMessage(String key) {
    return getMessage(Constants.MSG_MESSAGE, key);
  }
  
  /**
   * Lấy nội dung của message
   * 
   * @param messageType kiểu message ("Error", "Msg")
   * @param key trong file *.properties
   * @return nội dung message, trường hợp không tồn tại message sẽ trả lại key 
   */
  private String getMessage(String messageType, String key) {
    String result = "";
    if (Constants.ERROR_MESSAGE.equals(messageType)) {
      result = languageController.getErrorMap().get(key);
    } else if (Constants.MSG_MESSAGE.equals(messageType)) {
      result = languageController.getMsgMap().get(key);
    }
    
    if (StringUtils.isEmpty(result)) {
      result = key;
    }
    return result;
  }
  
  /**
   * Handle exception được trả về từ service and web-service layers 
   * @param e
   */
  @SuppressWarnings("rawtypes")
  protected void handleExceptionMessage(Exception e, Class clazz) {
    /**
     * logger.
     */
    final Log logger = LogFactory.getLog(clazz);
    final String MESSAGE_CODE = "messageCode";
    final String MESSAGE = "message";
    
    String errorMsg = null;
    if (!(e instanceof CmsRestException)) {
      logger.error("Có lỗi xảy ra khi thao tác với " + clazz);
      logger.error(e.getMessage());
      errorMsg = getErrorMessage(ErrorConstants.SERVER_ERROR05);
      FacesUtil.addErrorMsg(null, errorMsg, "");
      return;
    }

    String bodyMessage = e.getMessage();
    Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
    if (bodyMessageMap == null || bodyMessageMap.size() <= 0) {
      errorMsg = getErrorMessage(ErrorConstants.SERVER_ERROR05);
      FacesUtil.addErrorMsg(null, errorMsg, "");
      return;
    }

    String messageCode = bodyMessageMap.get(MESSAGE_CODE);
    String message = bodyMessageMap.get(MESSAGE);
    logger.error("messageCode:" + messageCode);
    logger.error("message:" + message);
    errorMsg = getErrorMessage(messageCode);
    FacesUtil.addErrorMsg(null, errorMsg, "");
  }
  
  /**
   * Cắt chuỗi 
   * 
   * @param text chuỗi gốc
   * @param size độ dài ký tự cần hiển thị
   * @return text chuỗi đã cắt nếu độ dài chuỗi gốc > size 
   */
  public String catStr(String text, int size) {
    if (!StringUtils.isEmpty(text)) {
      if (text.length() > size) {
        return String.format("%s...", text.substring(0, size));
      }
    }
    
    return text;
  }

  /**
   * TODO Override this method in each sub-class.
   * TODO change the signature of this method to abstract. 
   * @return
   */
  public long getModuleId() {
    throw new UnsupportedOperationException("please override this method in sub-class.");
  }
}
