package com.vietsci.cms.frontend.etopup.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJson")
public class Trans implements java.io.Serializable {

  private static final long serialVersionUID = -7457969506934754426L;
  private Long transId;
  private String transType;
  private Integer processStatus;
  private Long channel;
  private Date createDate;
  private Date lastModified;
  private String sourceMsisdn;
  private Long sourceAccountId;
  private BigDecimal sourceCurrBalance;
  private String targetMsisdn;
  private Long targetAccountId;
  private BigDecimal targetCurrBalance;
  private BigDecimal transAmount;
  private String errCode;
  private String proCode;
  private BigDecimal sourceAfTransBalance;
  private BigDecimal targetAfTransBalance;
  private Long sourceAgentId;
  private Long targetAgentId;
  private Long preTransId;
  private Long stockTransId;
  private Long saleTransId;
  private Long emTransId;
  private String isdn;
  private String serial;
  private String transPackage;
  private String goodCode;
  private String amount;
  private String message;  
  private Set<TransLog> transLogs = new HashSet<>(0);
  private Set<TransExt> transExts = new HashSet<>(0);
  
  private String channelName;

  
  
  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public Trans() {
  }

  public Long getTransId() {
    return this.transId;
  }

  public void setTransId(Long transId) {
    this.transId = transId;
  }

  public String getTransType() {
    return this.transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
  }

  public Long getStockTransId() {
    return stockTransId;
  }

  public void setStockTransId(Long stockTransId) {
    this.stockTransId = stockTransId;
  }

  public Long getSaleTransId() {
    return saleTransId;
  }

  public void setSaleTransId(Long saleTransId) {
    this.saleTransId = saleTransId;
  }

  public Long getEmTransId() {
    return emTransId;
  }

  public void setEmTransId(Long emTransId) {
    this.emTransId = emTransId;
  }

  public String getIsdn() {
    return isdn;
  }

  public void setIsdn(String isdn) {
    this.isdn = isdn;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getTransPackage() {
    return transPackage;
  }

  public void setTransPackage(String transPackage) {
    this.transPackage = transPackage;
  }

  public String getGoodCode() {
    return goodCode;
  }

  public void setGoodCode(String goodCode) {
    this.goodCode = goodCode;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getProcessStatus() {
    return processStatus;
  }
  
  public void setProcessStatus(Integer processStatus) {
    this.processStatus = processStatus;
  }

  public Long getChannel() {
    return this.channel;
  }

  public void setChannel(Long channel) {
    this.channel = channel;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastModified() {
    return this.lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public String getSourceMsisdn() {
    return this.sourceMsisdn;
  }

  public void setSourceMsisdn(String sourceMsisdn) {
    this.sourceMsisdn = sourceMsisdn;
  }

  public Long getSourceAccountId() {
    return this.sourceAccountId;
  }

  public void setSourceAccountId(Long sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }

  public BigDecimal getSourceCurrBalance() {
    return this.sourceCurrBalance;
  }

  public void setSourceCurrBalance(BigDecimal sourceCurrBalance) {
    this.sourceCurrBalance = sourceCurrBalance;
  }

  public String getTargetMsisdn() {
    return this.targetMsisdn;
  }

  public void setTargetMsisdn(String targetMsisdn) {
    this.targetMsisdn = targetMsisdn;
  }

  public Long getTargetAccountId() {
    return this.targetAccountId;
  }

  public void setTargetAccountId(Long targetAccountId) {
    this.targetAccountId = targetAccountId;
  }

  public BigDecimal getTargetCurrBalance() {
    return this.targetCurrBalance;
  }

  public void setTargetCurrBalance(BigDecimal targetCurrBalance) {
    this.targetCurrBalance = targetCurrBalance;
  }

  public BigDecimal getTransAmount() {
    return this.transAmount;
  }

  public void setTransAmount(BigDecimal transAmount) {
    this.transAmount = transAmount;
  }

  public String getErrCode() {
    return this.errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getProCode() {
    return this.proCode;
  }

  public void setProCode(String proCode) {
    this.proCode = proCode;
  }

  public BigDecimal getSourceAfTransBalance() {
    return this.sourceAfTransBalance;
  }

  public void setSourceAfTransBalance(BigDecimal sourceAfTransBalance) {
    this.sourceAfTransBalance = sourceAfTransBalance;
  }

  public BigDecimal getTargetAfTransBalance() {
    return this.targetAfTransBalance;
  }

  public void setTargetAfTransBalance(BigDecimal targetAfTransBalance) {
    this.targetAfTransBalance = targetAfTransBalance;
  }

  public Long getSourceAgentId() {
    return this.sourceAgentId;
  }

  public void setSourceAgentId(Long sourceAgentId) {
    this.sourceAgentId = sourceAgentId;
  }

  public Long getTargetAgentId() {
    return this.targetAgentId;
  }

  public void setTargetAgentId(Long targetAgentId) {
    this.targetAgentId = targetAgentId;
  }

  public Long getPreTransId() {
    return this.preTransId;
  }

  public void setPreTransId(Long preTransId) {
    this.preTransId = preTransId;
  }

  public Set<TransLog> getTransLogs() {
    return this.transLogs;
  }

  public void setTransLogs(Set<TransLog> transLogs) {
    this.transLogs = transLogs;
  }

  public Set<TransExt> getTransExts() {
    return transExts;
  }

  public void setTransExts(Set<TransExt> transExts) {
    this.transExts = transExts;
  }

  public static enum TransType {
    ADM("ADM", "ADM"),
    RSMPIN("RSMPIN", "RSMPIN"),
    TRANL("TRANL", "TRANL"),
    COMM("COMM", "COMM");

    private String value;
    private String name;


    private TransType(String value, String name) {
      this.value = value;
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }

  public static enum TransChannel {
    CHANNEL_WEB(1, "Web"),
    CHANNEL_SMS(2, "SMS");

    private int value;
    private String name;


    private TransChannel(int value, String name) {
      this.value = value;
      this.name = name;
    }

    public int getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }
  
  public static enum ProcessStatus {
    TRANS_PENDING(1, "Chưa duyệt"),
    TRANS_INPROGRESS(2, "Đang xử lý"),
    TRANS_COMPLETE(3, "Hoàn thành"),
    TRANS_REJECT(9, "Hủy bỏ");

    private Integer value;
    private String name;


    private ProcessStatus(Integer value, String name) {
      this.value = value;
      this.name = name;
    }

    public Integer getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }

  public String getProcessStatusName() {
    if (processStatus == ProcessStatus.TRANS_PENDING.getValue()) {
      return ProcessStatus.TRANS_PENDING.getName();
    } else if (processStatus == ProcessStatus.TRANS_INPROGRESS.getValue()) {
      return ProcessStatus.TRANS_INPROGRESS.getName();
    } else if (processStatus == ProcessStatus.TRANS_COMPLETE.getValue()) {
      return ProcessStatus.TRANS_COMPLETE.getName();
    } else if (processStatus == ProcessStatus.TRANS_REJECT.getValue()) {
      return ProcessStatus.TRANS_REJECT.getName();
    } else {
      return "";
    }
  }
}
