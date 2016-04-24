/**
 * 
 */
package com.vietsci.cms.frontend.etopup.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.vietsci.cms.frontend.etopup.common.util.Constants;
import com.vietsci.cms.frontend.etopup.common.util.Constants.FileManagement;
import com.vietsci.cms.frontend.etopup.common.util.EtopupUtil;
import com.vietsci.cms.frontend.etopup.dto.ShopStaffDTO;
import com.vietsci.cms.frontend.etopup.dto.StaffDTO;
import com.vietsci.cms.frontend.etopup.model.Trans;
import com.vietsci.cms.frontend.etopup.model.TransLog;
import com.vietsci.cms.frontend.exception.CmsRestException;
import com.vietsci.cms.frontend.main.controller.AuthorizationController;
import com.vietsci.cms.frontend.main.controller.BaseController;
import com.vietsci.cms.frontend.main.model.Member;
import com.vietsci.cms.frontend.util.ErrorConstants;
import com.vietsci.cms.frontend.util.FacesUtil;

/**
 * @author NguyenPV
 *
 */
public class EtopupBaseController extends BaseController {

  private static final long serialVersionUID = 2180652407967985086L;

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
      errorMsg = getErrorMessage(ErrorConstants.ETOPUP_OPERATION_FAILED);
      FacesUtil.addErrorMsg(null, errorMsg, "");
      return;
    }

    String bodyMessage = e.getMessage();
    Map<String, String> bodyMessageMap = EtopupUtil.read(bodyMessage);
    if (bodyMessageMap == null || bodyMessageMap.size() <= 0) {
      errorMsg = getErrorMessage(ErrorConstants.ETOPUP_OPERATION_FAILED);
      FacesUtil.addErrorMsg(null, errorMsg, "");
      return;
    }

    String messageCode = bodyMessageMap.get(MESSAGE_CODE);
    String message = bodyMessageMap.get(MESSAGE);
    logger.error("messageCode:" + messageCode);
    logger.error("message:" + message);
    errorMsg = getErrorMessage(messageCode);
    if (StringUtils.isBlank(errorMsg)) {
      errorMsg = message;
    }
    FacesUtil.addErrorMsg(null, errorMsg, "");
  }
  
  /**
   * Xây dựng nội dung dòng lỗi từ dòng ban đầu cộng thêm đánh dấu lỗi: "--> Error"
   * @param check kết quả kiểm tra nội dung dòng ban đầu là có lỗi hay không.
   * @param originLine nội dung dòng ban đầu
   * @return nội dung dòng lỗi
   */
  protected String buildErrorLineContent(boolean check, String originLine) {
    StringBuffer errorLine = new StringBuffer();
    errorLine.append(originLine);
    
    if (!check) {
      errorLine.append(ErrorConstants.ETOPUP_COMMON_ERROR_MARK_FOR_UPLOADED_FILE);
    }
    
    return errorLine.toString();
  }

  /**
   * Tạo file lỗi với nội dung từ uploaded file và đánh dấu các dòng lỗi.
   * Áp dụng cho các file .txt
   * @param errorFileContent list chứa các dòng của file lỗi được tạo từ uploaded file và đánh dấu lỗi
   * @return StreamedContent của file lỗi
   * @throws IOException
   */
  @SuppressWarnings("rawtypes")
  protected StreamedContent createErrorFileForUploadedFileTXT(String uploadedFileName, List<String> errorFileContent,
                                                              Class clazz) throws IOException {
    /**
     * logger.
     */
    final Log logger = LogFactory.getLog(clazz);
    String errorFileName = uploadedFileName;
    errorFileName = errorFileName.substring(0, errorFileName.lastIndexOf(FileManagement.TXT_EXTENSION));
    errorFileName = errorFileName.concat(FileManagement.ERROR_FILE_NAME_POSTFIX).concat(FileManagement.TXT_EXTENSION);

    // Creating a new file
    Path newFile = Paths.get(errorFileName);
    try {
      Files.deleteIfExists(newFile);
      newFile = Files.createFile(newFile);
    } catch (IOException ex) {
      logger.error("Error creating file: " + errorFileName);
      return null;
    }

    logger.debug(Files.exists(newFile));

    // Writing to file
    try (BufferedWriter writer = Files.newBufferedWriter(newFile, StandardCharsets.UTF_8)) {
      for (String line : errorFileContent) {
        writer.append(line);
        writer.newLine();
      }
      writer.flush();
    } catch (IOException exception) {
      logger.error("Error writing to file: " + errorFileName);
      return null;
    }

    InputStream stream = Files.newInputStream(newFile);
    return new DefaultStreamedContent(stream, "text/plain", errorFileName);
  }
  
  /**
   * Check if error file input stream is closed or not so that reopen if it was closed 
   * @param errorFileStream
   * @param clazz
   * @return
   */
  @SuppressWarnings("rawtypes")
  protected StreamedContent checkToReopenErrorFile(StreamedContent errorFileStream, Class clazz) {
    final Log logger = LogFactory.getLog(clazz);
    StreamedContent reopenStream = null;
    try {
      int check = errorFileStream.getStream().available();
      if (check > 0) {
        reopenStream = errorFileStream;
      }
    } catch (java.nio.channels.ClosedChannelException e) {
      reopenStream = reopenErrorFile(errorFileStream.getName(), SIMBatchEtopupController.class);
    } catch (Exception e) {
      logger.equals("Failed in check error file for: " + errorFileStream.getName() + ". Error: " + e.getMessage());
    }
    return reopenStream;
  }
  
  @SuppressWarnings("rawtypes")
  protected StreamedContent reopenErrorFile(String errorFileName, Class clazz) {
    final Log logger = LogFactory.getLog(clazz);
    
    Path newFile = Paths.get(errorFileName);
    InputStream stream;
    try {
      stream = Files.newInputStream(newFile);
    } catch (IOException e) {
      logger.error("Error reopen file: " + errorFileName);
      return null;
    }
    return new DefaultStreamedContent(stream, "text/plain", errorFileName);
  }
  
  public long getCurrentUserId() {
    AuthorizationController authorizationController = (AuthorizationController) (FacesContext.getCurrentInstance().getExternalContext()).
        getSessionMap().get("auth");
    Member member = authorizationController.getMember();
//    return member.getStaffId();
    return 0L;
  }

  public String getCurrentUserName() {
    AuthorizationController authorizationController = (AuthorizationController) (FacesContext.getCurrentInstance().getExternalContext()).
        getSessionMap().get("auth");
    return authorizationController.getUserName();
  }
  
  public long getCurrentStockStaffId() {
    AuthorizationController authorizationController = (AuthorizationController) (FacesContext.getCurrentInstance().getExternalContext()).
        getSessionMap().get("auth");
    Member member = authorizationController.getMember();
//  return member.getStaffId();
    return 0L;
  }
  
  public TimeZone getCurrentTimeZone() {
    TimeZone currentTimeZone = TimeZone.getTimeZone("Asia/Bangkok");
    if (currentTimeZone == null) {
      return TimeZone.getDefault();
    }
    return currentTimeZone;
  }
  
  public String showTransChannel(Trans trans) {
    if (trans == null) {
      return "";
    }
    Long channel = trans.getChannel();
    if (channel == null || channel == 0L) {
      return "";
    }
    String channelName = Constants.ChannelType.getNameByValue(channel.intValue());
    return channelName;
  }
  
  public String showTransLogChannel(TransLog transLog) {
    if (transLog == null) {
      return "";
    }
    Long channel = transLog.getChannelId();
    if (channel == null || channel == 0L) {
      return "";
    }
    String channelName = Constants.ChannelType.getNameByValue(channel.intValue());
    return channelName;
  }
}
