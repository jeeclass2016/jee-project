package com.vietsci.cms.frontend.util;


/**
 * Error constant
 * 
 */
public final class ErrorConstants {
  
  /* Common */
  // For uploading file - start
  
  public static final String CMS_COMMON_UPLOAD_FILE_NOT_SELECTED = "error.cms.common.upload.file.not.selected";

  public static final String CMS_COMMON_FILE_EXTENSION_TXT = "error.cms.common.file.extension.txt";

  public static final String CMS_COMMON_FILE_EMPTY = "error.cms.common.file.empty";

  public static final String CMS_COMMON_UPLOAD_FILE_FAILED = "error.cms.common.upload.file.failed";
 
  // For uploading file - end

  /**
   * Xảy ra lỗi trong quá trình lưu dữ liệu vào CSDL. Vui lòng thực hiện lại hoặc liên hệ với Quản trị hệ thống để được trợ giúp.
   */
  public static final String SERVER_ERROR02="server.error02";
  /**
   * Lưu dữ liệu không thành công. Dữ liệu không đúng định dạng.
   */
  public static final String SERVER_ERROR03="server.error03";
  /**
   * Không thể kết nối với server. Vui lòng thực hiện lại hoặc liên hệ với Quản trị viên hệ thống để được trợ giúp.
   */
  public static final String SERVER_ERROR04="server.error04";
  /**
   * Có lỗi xảy ra trong quá trình xử lý
   */
  public static final String SERVER_ERROR05="server.error05";
  
  public static final String STOCK_IMPORTFROMCENTRAL_INVALIDSAVING = "error.stock.importfromcentral.invalidsaving";

  public static final String STOCK_IMPORTFROMCENTRAL_INVALIDDATETIME ="error.stock.importfromcentral.invaliddatetime";
  
  public static final String STOCK_IMPORTFROMCENTRAL_INVALIDSTOCK = "error.stock.importfromcentral.invalidstock";
  
  public static final String STOCK_EXPORTFORCUSTOMERBYPARCEL_UPLOAD ="error.stock.exportforcustomerbyparcel.upload";
  
  public static final String STOCK_IMPORTFROMCENTRAL_INVALIDFROMDATE ="error.stock.importfromcentral.fromdatebiggerthantodate";
  
  /* ======== Etopup ======== */
  /* Common */
  public static final String ETOPUP_COMMON_ERROR_MARK_FOR_UPLOADED_FILE = " --> Error";
  
  public static final String ETOPUP_OPERATION_FAILED = "error.etopup.operation.failed";

  public static final String ETOPUP_COMMON_TIME_RANGE = "error.etopup.common.time.range";
  
  public static final String ETOPUP_COMMON_SUBSCRIBER_NOT_EXIST = "error.etopup.common.subscriber.not.exist";
  
  public static final String ETOPUP_COMMON_ICCID_MSISDN_NOT_EXIST = "error.etopup.common.iccid.msisdn.not.exist";
  
  public static final String ETOPUP_COMMON_ICCID_MSISDN_USED = "error.etopup.common.iccid.msisdn.used";

  public static final String ETOPUP_COMMON_ICCID_NOT_EXIST = "error.etopup.common.iccid.not.exist";

  public static final String ETOPUP_COMMON_ICCID_USED = "error.etopup.common.iccid.used";

  // For uploading file - start
  
  public static final String ETOPUP_COMMON_UPLOAD_FILE_NOT_SELECTED = "error.etopup.common.upload.file.not.selected";

  public static final String ETOPUP_COMMON_FILE_EXTENSION_TXT = "error.etopup.common.file.extension.txt";

  public static final String ETOPUP_COMMON_FILE_EMPTY = "error.etopup.common.file.empty";

  public static final String ETOPUP_COMMON_UPLOAD_FILE_FAILED = "error.etopup.common.upload.file.failed";
  
  public static final String ETOPUP_COMMON_BLANK_LINE = "error.etopup.common.blank.line";
  
  public static final String ETOPUP_COMMON_DELIMITER_ERROR_LINE = "error.etopup.common.delimiter.error.line";
  
  public static final String ETOPUP_COMMON_START_WITH_ZERO_LINE = "error.etopup.common.start.with.zero.line";
  
  public static final String ETOPUP_COMMON_CONTAIN_NUMBER_LINE = "error.etopup.common.contain.number.line";
  
  public static final String ETOPUP_COMMON_LENGTH_LINE = "error.etopup.common.length.line";
  
  public static final String ETOPUP_COMMON_DUPLICATE_LINE = "error.etopup.common.duplicate.line";
  // For uploading file - end
  
  /* Ma loi cho chuong trinh khuyen mai */
  public static final String ETOPUP_PROMOTION_EMPTY = "error.etopup.promotion.empty";
  
  public static final String ETOPUP_PROMOTION_NULL = "error.etopup.promotion.null";
  
  public static final String ETOPUP_PROMOTION_MISSING_CODE = "error.etopup.promotion.missing.code";
  
  public static final String ETOPUP_PROMOTION_TIME = "error.etopup.promotion.time";
  
  public static final String ETOPUP_PROMOTION_CODE_EXISTED = "error.etopup.promotion.code.existed";
  
  public static final String ETOPUP_PROMOTION_NOT_EXISTED = "error.etopup.promotion.not.existed";
  
  public static final String ETOPUP_PROMOTION_DELETED = "error.etopup.promotion.deleted";
  
  public static final String ETOPUP_PROMOTION_INVALID = "error.etopup.promotion.invalid";
  
  public static final String ETOPUP_PROMOTION_CREATE = "error.etopup.promotion.create";
  
  public static final String ETOPUP_PROMOTION_UPDATE = "error.etopup.promotion.update";
  
  public static final String ETOPUP_PROMOTION_DELETE = "error.etopup.promotion.delete";
  
  /* Ma loi cho upload SIM theo lo */
  public static final String ETOPUP_SIM_BATCH_SEARCH_TIME = "error.etopup.sim.batch.search.time";
  
  public static final String ETOPUP_SIM_BATCH_UPLOADING_NO_SIM_CONTENT = "error.etopup.sim.batch.uploading.no.sim.content";
  
  public static final String ETOPUP_SIM_BATCH_UPLOADING_SUBCRIBLE_EXITING = "error.etopup.sim.batch.uploading.subcrible.exiting";
  
  public static final String ETOPUP_SIM_BATCH_UPLOADING_EXISTED = "error.etopup.sim.batch.uploading.existed";

  /* ICCID Management */
  public static final String ETOPUP_ICCID_MANAGEMENT_DELETE = "error.etopup.iccid.management.delete";
  public static final String ETOPUP_ICCID_MANAGEMENT_ICCID_START_GREATER_ICCID_END = "error.etopup.iccid.management" +
      ".iccid.start.greater.iccid.end";

  /* Password Reset for Agent */
  public static final String PASSWORD_RESET_MSISDN_NOT_MATCHED = "error.etopup.resetpassword.msisdn.notmatched";
  
  /* ICCID - MSISDN change*/
  public static final String ETOPUP_ICCID_MSISDN_CHANGE= "error.etopup.iccid.msisdn.change";
  
  
  /* Money Transfer Transaction */
  public static final String ETOPUP_MONEY_TRANSFER = "error.etopup.money.transfer";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_MSISDN_AND_TARGET_MSISDN_SAME = "error.etopup.money.transaction.source.msisdn.and.target.msisdn.same";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_MSISDN_NOT_EXIST = "error.etopup.money.transaction.source.msisdn.not.exist";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_DELETED = "error.etopup.money.transaction.source.agent.deleted";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_TEMPORAL_LOCKED = "error.etopup.money.transaction.source.agent.temporal.locked";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_NOT_ACTIVE = "error.etopup.money.transaction.source.agent.not.active";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_NOT_IN_PASS_AUDIT = "error.etopup.money.transaction.source.agent.not.in.pass.audit";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_INVALID = "error.etopup.money.transaction.source.agent.invalid";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_MPIN_INCORRECT = "error.etopup.money.transaction.source.agent.mpin.incorrect";
  
  public static final String ETOPUP_MONEY_TRANSACTION_SOURCE_AGENT_MPIN_INACTIVE = "error.etopup.money.transaction.source.agent.mpin.inactive";
  
  public static final String ETOPUP_MONEY_TRANSACTION_TARGET_MSISDN_NOT_EXIST = "error.etopup.money.transaction.target.msisdn.not.exist";
  
  public static final String ETOPUP_MONEY_TRANSACTION_TARGET_AGENT_DELETED = "error.etopup.money.transaction.target.agent.deleted";
  
  public static final String ETOPUP_MONEY_TRANSACTION_TARGET_AGENT_TEMPORAL_LOCKED = "error.etopup.money.transaction.target.agent.temporal.locked";
  
  public static final String ETOPUP_MONEY_TRANSACTION_TARGET_AGENT_NOT_ACTIVE = "error.etopup.money.transaction.taget.agent.not.active";
  
  public static final String ETOPUP_MONEY_TRANSACTION_TARGET_AGENT_NOT_IN_PASS_AUDIT = "error.etopup.money.transaction.target.agent.not.in.pass.audit";
  
  public static final String ETOPUP_MONEY_TRANSACTION_TARGET_AGENT_INVALID = "error.etopup.money.transaction.target.agent.invalid";
  
  public static final String ETOPUP_MONEY_TRANSACTION_AMOUNT_INVALID = "error.etopup.money.transaction.amount.invalid";
  
  /* Balance Management */
  public static final String ETOPUP_BALANCE_MANAGEMENT_ADJUST = "error.etopup.balance.management.adjust";
  public static final String ETOPUP_BALANCE_BATCH_UPLOADING_NO_CONTENT = "error.etopup.balance.batch.uploading.no.balance.content";

  /* Promotion Batch */
  public static final String ETOPUP_PROMOTION_BATCH_UPLOADING_NO_CONTENT = "error.etopup.promotion.batch.uploading.no.content";
  
  /* Agent Address Management */
  public static final String ETOPUP_AGENT_ADDRESS_BATCH_UPLOADING_NO_CONTENT = "error.etopup.agent.address.management.batch.uploading.no.address.content";
  public static final String ETOPUP_AGENT_MANAGEMENT_AGENT_TEMPORAL_LOCKED = "etopup.agent.management.agent.temporal.locked";
  public static final String ETOPUP_AGENT_MANAGEMENT_AGENT_DELETED = "etopup.agent.management.agent.deleted";
  
  /* Agent Traversing Batch */
  public static final String ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH_FAIL ="error.etopup.agent.management.traverse" +
      ".batch.fail";
  public static final String ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH_NO_AGENT_VALID = "error.etopup.agent.management" +
      ".traverse.batch.no.agent.valid";
  public static final String ETOPUP_AGENT_MANAGEMENT_TRAVERSE_BATCH_UPLOAD_FILE = "error.etopup.agent.management" +
      ".traverse.batch.upload.file";
  
  /* User management */
  public static final String ETOPUP_USER_MANAGEMENT_USER_NOT_EXIST = "error.user.management.user.not.exist";

  /* ======== Customer Care ======== */
  /* Common */
  
  public static final String CUSTOMER_CARE_OPERATION_FAILED = "error.customer.care.operation.failed";

 
  //Ma loi cho thu hoi hoa don

  /**
   * Không tìm thấy hóa đơn nào
   */
  public static final String INVOICE_NOTFIND = "error.invoice.notfind";
  /**
   * Giá trị dải hóa đơn nhập vào nằm ngoài khoảng hóa đơn của dải chưa giao
   */
  public static final String INVOICE_REVOKEINVOICE_ERR01 = "error.invoice.revokeinvoice.err01";
  /**
   * Giá trị đến hóa đơn nhập vào nhỏ hơn số hóa đơn của dải chưa giao
   */
  public static final String INVOICE_REVOKEINVOICE_ERR02 = "error.invoice.revokeinvoice.err02";
  /**
   * Giá trị từ hóa đơn nhập vào lớn hơn số hóa đơn của dải chưa giao
   */
  public static final String INVOICE_REVOKEINVOICE_ERR03 = "error.invoice.revokeinvoice.err03";
  /**
   * Không tìm thấy hóa đơn nào chưa giao
   */
  public static final String INVOICE_REVOKEINVOICE_ERR04 = "error.invoice.revokeinvoice.err04";

  /**
   * Giá trị dải hóa đơn nhập vào nằm trong khoảng dải hóa đơn đã được sử dụng
   */
  public static final String INVOICE_REVOKEINVOICE_ERR05 = "error.invoice.revokeinvoice.err05";

  /**
   * Từ số hóa đơn lớn hơn đến số hóa đơn
   */
  public static final String INVOICE_REVOKEINVOICE_ERR06 = "error.invoice.revokeinvoice.err06";
  
  public static final String INVOICE_REVOKE = "error.invoice.revoke";
  
  /**
   * Xóa không thành công.
   */
  public static final String INVOICE_ERROR01 = "invoice.error01";
  
  /**
   * Giá trị không đúng định dạng.
   */
  public static final String INVOICE_ERROR02 = "invoice.error02";
  /**
   * Không tồn tại dải hóa đơn.
   */
  public static final String INVOICE_ERROR03 = "invoice.error03";
  
  /**
   * Dải hóa đơn từ
   */
  public static final String INVOICE_ERROR04_01 = "invoice.error04.01";
  /**
   *  đến 
   */
  public static final String INVOICE_ERROR04_02 = "invoice.error04.02";
  /**
   *  đã được sử dụng.
   */
  public static final String INVOICE_ERROR04_03 = "invoice.error04.03";
  
  /**
   * Hãy nhập serial theo định dạng 'XX/YYYYX-XX', trong đó X là kí tự và Y là chữ số.
   */
  public static final String INVOICE_ERROR05 ="invoice.error05";
  
  /*****************NHẬP HÓA ĐƠN ĐƠN LẺ*******************/
  /**
   * "Từ số hóa đơn" không được lớn hơn "Đến số hóa đơn"
   */
  public static final String INVOICE_INPUTSINGLE_ERR01="invoice.inputsingle.err01";
  
  /**
   * "Đến số hóa đơn" không được lớn hơn 
   */
  public static final String INVOICE_INPUTSINGLE_ERR03="invoice.inputsingle.err03";
  /**
   * Trùng với dải hóa đơn: 
   */
  public static final String INVOICE_INPUTSINGLE_ERR04 = "invoice.inputsingle.err04";
  
  /*****************NHẬP HÓA ĐƠN ĐƠN THEO DẢI*******************/
  /**
   * "Từ số block" không được lớn hơn "Đến số block"
   */
  public static final String INVOICE_SPLIT_ERR02="invoice.split.err02";
  /**
   * "Đến số block" không được lớn hơn 
   */
  public static final String INVOICE_SPLIT_ERR03="invoice.split.err03";
  
  /**
   * Trùng với block: 
   */
  public static final String INVOICE_SPLIT_ERR04="invoice.split.err04";
  /**
   * Bạn phải chọn dải hóa đơn muốn tách.
   */
  public static final String INVOICE_SPLIT_ERR05="invoice.split.err05";
  /**
   * Không thể tách dải thành dải nhỏ hơn được nữa.
   */
  public static final String INVOICE_SPLIT_ERR06="invoice.split.err06";
  /**
   * Hãy nhập "Hóa đơn tách"
   */
  public static final String INVOICE_SPLIT_ERR07="invoice.split.err07";
  /**
   * "Hóa đơn tách"  phải nằm trong khoảng từ 
   */
  public static final String INVOICE_SPLIT_ERR08_01="invoice.split.err08.01";
  /**
   * \ đến 
   */
  public static final String INVOICE_SPLIT_ERR08_02="invoice.split.err08.02";
  /**
   * Không tìm thấy dải hóa đơn.
   */
  public static final String INVOICE_SPLIT_ERR09="invoice.split.err09";
  /**
   * Ngoài khoảng có thể tách.
   */
  public static final String INVOICE_SPLIT_ERR10="invoice.split.err10";
  /**
   * Hệ thống đang không ổn định.
   */
  public static final String INVOICE_SPLIT_ERR11="invoice.split.err11";
  
  /****************XÁC NHẬN DẢI HÓA ĐƠN*******************/
  /**
   * "Đến ngày" không thể lớn hơn ngày hiện tại.
   */
  public static final String INVOICE_APPROVEINVOICE_ERR01="invoice.approveinvoice.err01";
  /**
   * "Đến ngày" không thể nhỏ hơn "Từ ngày".
   */
  public static final String INVOICE_APPROVEINVOICE_ERR02="invoice.approveinvoice.err02";
  /**
   * "Từ ngày" không thể lớn hơn ngày hiện tại.
   */
  public static final String INVOICE_APPROVEINVOICE_ERR03="invoice.approveinvoice.err03";
  /**
   * "Từ ngày" không thể lớn hơn "Đến ngày".
   */
  public static final String INVOICE_APPROVEINVOICE_ERR04="invoice.approveinvoice.err04";
  /**
   *Bạn phải chọn dải hóa đơn cần xác nhận. 
   */
  public static final String INVOICE_APPROVEINVOICE_ERR05="invoice.approveinvoice.err05";
  /**
   * Không có hóa đơn để xác nhận.
   */
  public static final String INVOICE_APPROVEINVOICE_ERR06="invoice.approveinvoice.err06";
  /**
   * Không tìm thấy dải hóa đơn.
   */
  public static final String INVOICE_APPROVEINVOICE_ERR07="invoice.approveinvoice.err07";
  /**
   * Dải hóa đơn không còn hiệu lực.
   */
  public static final String INVOICE_APPROVEINVOICE_ERR08="invoice.approveinvoice.err08";
  
  /**
   * Sv Field và Sv Object đã tồn tại, vui lòng nhập giá trị khác
   */
  public final static String CMPARAMDETAIL_UNIQUE = "error.catalog.cmparamdetail.unique";
  /**
   * Độ dài vượt quá 100 byte.
   */
  public final static String CMPARAMDETAIL_STRING_MAX_100Byte="error.catalog.cmParamDetail.max100byte";
  /**
   * Tham số, Loại khách hàng và Sv Value đã tồn tại, vui lòng nhập giá trị khác
   */
  public final static String CMPARAMDEFAULT_UNIQUE = "error.catalog.cmparamdefault.unique";
  
  /**
   * Tên tham số, tên VN và tên EN đã tồn tại, vui lòng nhập giá trị khác
   */
  public final static String CMPARAM_UNIQUE = "error.catalog.cmparam.unique";

  /** CSKH */
  public final static String CC_PREPAID_CREATE = "error.cc.prepaid.create";
  public final static String CC_PREPAID_UPDATE = "error.cc.prepaid.update";
  public final static String CC_INVOICE_PRINT_BEFORE = "error.cc.invoice.print.before";
}
