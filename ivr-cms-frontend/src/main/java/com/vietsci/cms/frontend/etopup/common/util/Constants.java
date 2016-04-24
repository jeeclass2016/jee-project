package com.vietsci.cms.frontend.etopup.common.util;

import java.util.Arrays;
import java.util.List;

/** 
 * Constants Util class to use in the ETOPUP module.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class Constants {
  
  public static final Long VIETNAMOBILE_HEAD_NUMBER = 1L;

  /** -- Paging -- */
  public static final String CURRENT_PAGE = "currentPage";
  public static final int RECORDS_PER_PAGE = 15;
  public static final int PAGE_SIZE = 100;
  public static final String ASCENDING_SORT = "asc";
  public static final String DESCENDING_SORT = "desc";

  /** -- End Paging -- */

  public static final String MESSAGE_CODE = "messageCode";
  public static final String MESSAGE = "message";

  public static final String ENCODING_UTF8 = "UTF-8";

  public static final List<String> SPECIAL_CHARACTERS = Arrays.asList(new String[] { "+", "-", "&", "|", "!", "(", ")",
      "{", "}", "[", "]", "^", "~", "*", "?", ":", "\\", "/", "\"", "#", "@", "%", "$", "<", ">" });

  // Tiếng Việt - Dùng cho chuyển đổi locale
  public static final String LANGUAGE_VI = "vi";
  // Tiếng Anh - Dùng cho chuyển đổi locale
  public static final String LANGUAGE_EN = "en";
  // Dùng để làm key lấy thông tin error message
  public static final String ERROR_MESSAGE = "Error";
  // Dùng để làm key lấy thông tin message
  public static final String MSG_MESSAGE = "Msg";

  /**
   * Constants used for Reason management.
   * @author nguyen.phanvan
   */
  public interface ReasonManagement {

    public static final String ACTIVE_STATUS_LABEL = "Hiệu lực";
    public static final String INACTIVE_STATUS_LABEL = "Không hiệu lực";
    public static final String ACTIVE_STATUS_VALUE = "1";
    public static final String INACTIVE_STATUS_VALUE = "0";

    public static final String CREATE_REASON_FAILED_MESSAGE = "Thêm mới lý do thất bại";
    public static final String CREATE_REASON_SUCCESS_MESSAGE = "Thêm mới lý do thành công";

    public static final String EDIT_REASON_FAILED_MESSAGE = "Sửa thông tin lý do thất bại";
    public static final String EDIT_REASON_SUCCESS_MESSAGE = "Sửa thông tin lý do thành công";

    public static final String DELETE_REASON_SUCCESS_MESSAGE = "Xóa lý do thành công";
    public static final String DELETE_REASON_FAILED_MESSAGE = "Xóa lý do thất bại";

    public static final String OPERATION_FAILED_MESSAGE = "Tác vụ xử lý thất bại";
    public static final String CANNOT_CREATE_REASON_WITH_BLANK_DESC_MESSAGE = "Bạn phải nhập dữ liệu cho mô tả lý do";
    public static final String CANNOT_CREATE_REASON_WITH_INVALID_DESC_MESSAGE = "Không được nhập ký tự đặc biệt cho mô tả lý do";
    public static final String CANNOT_DELETE_ACTIVE_REASON_MESSAGE = "Không được xóa lý do đang ở trạng thái hiệu lực";
    public static final String CANNOT_DELETE_NULL_REASON_MESSAGE = "Bạn phải chọn một lý do để xóa";
    public static final String VALIDATOR_MESSAGE_REASON_CODE_LENGTH = "Độ dài nhập vào không được vượt quá 10 ký tự";
    public static final String VALIDATOR_MESSAGE_REASON_CODE_CHARS = "Chỉ được nhập vào các ký tự alphabe và chữ số";
    public static final String VALIDATOR_MESSAGE_REASON_FIELD_NULL = "Phải nhập ít nhất 1 ký tự khác dấu cách (khoảng trắng)";
    public static final String VALIDATOR_MESSAGE_REASON_DESC_CHARS = "Chỉ được nhập vào các ký tự alphabe và chữ số";
  }

  public interface CentreManagement {
    public static final String ACTIVE_STATUS_LABEL = "Hoạt động";
    public static final String INACTIVE_STATUS_LABEL = "Tạm khoá";
    public static final String ACTIVE_STATUS_VALUE = "1";
    public static final String INACTIVE_STATUS_VALUE = "0";
    public static final String MESSAGE_SUCCESS_STATUS = "Thành công";
    public static final String MESSAGE_ERROR_STATUS = "Lỗi";
    public static final String MESSAGE_UPDATE_CENTRE_SUCCESS = "Cập nhật thành công.";
    public static final String MESSAGE_ADD_NEW_CENTRE_SUCCESS = "Thêm mới thành công.";
    public static final String MESSAGE_CANNOT_ACCESS_SERVER = "Không truy cập được Server";
    public static final String MESSAGE_DELETE_CENTRE_SUCCESS = "Xoá trung tâm thành công.";
    public static final String MESSAGE_CANNOT_DELETE_ACTIVE_CENTRE = "Trung tâm đang hoạt động bạn không được phép xoá.";
    public static final String POPUP_TITLE_EDIT = "Chỉnh sửa trung tâm";
    public static final String POPUP_TITLE_ADD = "Thêm mới trung tâm";
    public static final String VALIDATOR_MESSAGE_CENTRE_CODE_LENGTH = "Độ dài nhập vào không được vượt quá 10 ký tự";
    public static final String VALIDATOR_MESSAGE_CENTRE_CODE_CHARS = "Chỉ được nhập vào các ký tự alphabe và chữ số";
    public static final String VALIDATOR_MESSAGE_CENTRE_FIELD_NULL = "Phải nhập ít nhất 1 ký tự khác dấu cách (khoảng trắng)";
  }

  public interface AgentManagement {
    public static final String RECEIVE_BY_EMAIL_VALUE_YES = "Y";
    public static final String RECEIVE_BY_EMAIL_VALUE_NO = "N";
    public static final int AGENT_BATCH_UPLOADING_NUMBER_OF_TOKENS = 22;
    public static final int AGENT_BATCH_MSISDN_INDEX = 1;
    public static final int AGENT_BATCH_ICCID_INDEX = 14;
  }

  public interface TransactionTypeManagement {
    // Hieu luc
    public static final String TRANSACTION_TYPE_STATUS_ACTIVE = "Y";
    // Khong hieu luc
    public static final String TRANSACTION_TYPE_STATUS_INACTIVE = "N";
    public static final String ACTIVE_STATUS_LABEL = "Hiệu lực";
    public static final String INACTIVE_STATUS_LABEL = "Không hiệu lực";
    public static final String ACTIVE_STATUS_VALUE = "1";
    public static final String INACTIVE_STATUS_VALUE = "0";

    public static final String CREATE_TRANSACTION_TYPE_FAILED_MESSAGE = "Thêm mới kiểu giao dịch thất bại";
    public static final String CREATE_TRANSACTION_TYPE_SUCCESS_MESSAGE = "Thêm mới kiểu giao dịch thành công";

    public static final String EDIT_TRANSACTION_TYPE_FAILED_MESSAGE = "Sửa thông tin kiểu giao dịch thất bại";
    public static final String EDIT_TRANSACTION_TYPE_SUCCESS_MESSAGE = "Sửa thông tin kiểu giao dịch thành công";

    public static final String DELETE_TRANSACTION_TYPE_SUCCESS_MESSAGE = "Xóa kiểu giao dịch thành công";
    public static final String DELETE_TRANSACTION_TYPE_FAILED_MESSAGE = "Xóa kiểu giao dịch thất bại";
    public static final String CANNOT_DELETE_ACTIVE_TRANSACTION_TYPE_FAILED_MESSAGE = "Không được xóa giao dịch đang ở trạng thái hiệu lực";
    public static final String VALIDATOR_MESSAGE_TRANS_TYPE_CODE_LENGTH = "Độ dài nhập vào không được vượt quá 6 ký tự";
    public static final String VALIDATOR_MESSAGE_TRANS_TYPE_CODE_CHARS = "Chỉ được nhập vào các ký tự alphabe và chữ số";
    public static final String VALIDATOR_MESSAGE_TRANS_TYPE_FIELD_NULL = "Phải nhập ít nhất 1 ký tự khác dấu cách (khoảng trắng)";
    public static final String VALIDATOR_MESSAGE_TRANS_TYPE_DESC_CHARS = "Chỉ được nhập vào các ký tự alphabe và chữ số";
  }

  /**
   * Constants used for Promotion management.
   * @author lam.lethanh
   */
  public interface PromotionManagement {
    public static final String PROMOTION_CODE_BLANK_MESSAGE = "Mã chương trình khuyến mại cần phải có";
    public static final String PROMOTION_DESCRIPTION_BLANK_MESSAGE = "Mô tả chương trình khuyến mại cần phải có";
    public static final String START_TIME_ERROR_MESSAGE = "Ngày bắt đầu của chương trình khuyến mại phải có giá trị";
    public static final String TIME_VALIDATION_FAILED_MESSAGE = "Ngày bắt đầu của chương trình khuyến mại phải trước ngày kết thúc";

    public static final String CREATE_PROMOTION_FAILED_MESSAGE = "Thêm mới chương trình khuyến mại thất bại";
    public static final String CREATE_PROMOTION_SUCCESS_MESSAGE = "Thêm mới chương trình khuyến mại thành công";

    public static final String UPDATE_PROMOTION_FAILED_MESSAGE = "Sửa thông tin chương trình khuyến mại thất bại";
    public static final String UPDATE_PROMOTION_SUCCESS_MESSAGE = "Sửa thông tin chương trình khuyến mại thành công";

    public static final String DELETE_PROMOTION_SUCCESS_MESSAGE = "Xóa chương trình khuyến mại thành công";
    public static final String DELETE_PROMOTION_FAILED_MESSAGE = "Xóa chương trình khuyến mại thất bại";

    public static final String OPERATION_FAILED_MESSAGE = "Xử lý tác vụ thất bại";
  }

  public interface FileManagement {
    public static final String TXT_EXTENSION = ".txt";
    public static final String COMMA_DELIMITER = ",";
    public static final String PERIOD_DELIMITER = ".";
    public static final String LINE_ERROR = "Co loi o dong ";
    public static final String ERROR_FILE_NAME_POSTFIX = "_Error";

  }

  /**
   * Constants used for Agent Address management.
   * @author nguyen.phanvan
   */
  public interface AgentAddressManagement {
    public static final String OPERATION_FAILED_MESSAGE = "Xử lý tác vụ thất bại";
    public static final String MSISDN_WITH_NO_ADDRESS_MESSAGE = "Đại lý chưa có địa bàn hoạt động nào";
    public static final String ACTIVE_STATUS_LABEL = "Hoạt động";
    public static final String INACTIVE_STATUS_LABEL = "Không hoạt động";
    public static final String ACTIVE_STATUS_VALUE = "1";
    public static final String INACTIVE_STATUS_VALUE = "0";
  }
  
  public interface StockAgentMappingByBatch{
    public static final String ITEM_DUPPLICATED = "error.stock.agent.mapping.batch.item.dupplicated";
    public static final String ITEM_WRONG_SYNTAX_OR_FIELD_MISSING = "error.stock.agent.mapping.batch.item.wrong.syntax.or.field.missing";
  }
  
  public static enum ChannelType {
    APP("APP", 1, "kenh etopup app"),
    SMS("SMS", 2, "Kenh SMS"),
    WEB("WEB", 3, "kenh soap"),
    MPOS("MPOS", 4, "Kenh mpos"),
    EPOS("EPOS", 5, "Kenh Epos"),
    EPOS_CHANGESIM("EPOS CHANGESIM", 7, "Kenh Epos doi sim"),
    EPOS_CCARD("EPOS CCARD", 8, "Kenh Epos doi card"),
    EPOS_SIMCARD("EPOS SIMCARD", 9, "Kenh Epos dat hang sim the");

    private String name;
    private Integer value;

    private ChannelType(String name, Integer value, String desc) {
      this.value = value;
      this.name = name;
    }

    public Integer getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
    
    public static String getNameByValue(Integer value) {
      if (value == null) {
        return "";
      }
      String result = null;
      switch (value) {
      case 1:
        result = APP.name;
        break;
      case 2:
        result = SMS.name;
        break;
      case 3:
        result = WEB.name;
        break;
      case 4:
        result = MPOS.name;
        break;  
      case 5:
        result = EPOS.name;
        break;
      case 7:
        result = EPOS_CHANGESIM.name;
        break;
      case 8:
        result = EPOS_CCARD.name;
        break; 
      case 9:
        result = EPOS_SIMCARD.name;
        break;   
        
      default:
        result = "";
        break;
      }
      return result;
    }
    
    
  }

}
