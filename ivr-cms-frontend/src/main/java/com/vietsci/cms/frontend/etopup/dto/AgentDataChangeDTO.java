package com.vietsci.cms.frontend.etopup.dto;

import java.util.Date;

public class AgentDataChangeDTO extends BaseDTO implements java.io.Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -1367401542552145556L;

  private String agentMSISDN;
  private String fieldName;
  private Date fromDate;
  private Date toDate;
  
  public String getAgentMSISDN() {
    return agentMSISDN;
  }
  public void setAgentMSISDN(String agentMSISDN) {
    this.agentMSISDN = agentMSISDN;
  }
  public String getFieldName() {
    return fieldName;
  }
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
  public Date getFromDate() {
    return fromDate;
  }
  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }
  public Date getToDate() {
    return toDate;
  }
  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public static enum FieldName {

    MSISDN("MSISDN", "MSISDN"),
    ICCID("ICCID", "ICCID"),
    TRADE_NAME("TRADE_NAME", "Tên thương mại"),
    OWNER_NAME("OWNER_NAME", "Tên chủ sở hữu"),
    BIRTH_DATE("BIRTH_DATE", "Ngày sinh"),
    CONTACT_NO("CONTACT_NO", "Số liên lạc"),
    OUTLET_ADDRESS("OUTLET_ADDRESS", "Địa chỉ kinh doanh"),
    EMAIL("EMAIL", "Email"),
    SAP_CODE("SAP_CODE", "Mã đại lý"),
    CREATE_DATE("CREATE_DATE", "Ngày tạo"),
    AGENT_STATUS("AGENT_STATUS", "Trạng thái đại lý"),
    ACTIVE_DATE("ACTIVE_DATE", "ACTIVE_DATE");

    private String value;
    private String name;


    private FieldName(String value, String name) {
      this.value = value;
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
    
    public static String getNameByValue(String value) {
      if (MSISDN.value.equals(value)) {
        return MSISDN.name;
      } else if (ICCID.value.equals(value)) {
        return ICCID.name;
      } else if (TRADE_NAME.value.equals(value)) {
        return TRADE_NAME.name;
      } else if (OWNER_NAME.value.equals(value)) {
        return OWNER_NAME.name;
      } else if (BIRTH_DATE.value.equals(value)) {
        return BIRTH_DATE.name;
      } else if (CONTACT_NO.value.equals(value)) {
        return CONTACT_NO.name;
      } else if (OUTLET_ADDRESS.value.equals(value)) {
        return OUTLET_ADDRESS.name;
      } else if (EMAIL.value.equals(value)) {
        return EMAIL.name;
      } else if (SAP_CODE.value.equals(value)) {
        return SAP_CODE.name;
      } else if (CREATE_DATE.value.equals(value)) {
        return CREATE_DATE.name;
      } else if (AGENT_STATUS.value.equals(value)) {
        return AGENT_STATUS.name;
      } else if (ACTIVE_DATE.value.equals(value)) {
        return ACTIVE_DATE.name;
      } else {
        return "";
      }
    }
  }
}
