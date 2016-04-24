package com.vietsci.cms.frontend.etopup.model;

/**
 * ApParam generated by hbm2java
 */
public class ApParam implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1527493194332220496L;
  private ApParamId id;
  private String parValue;
  private String description;

  public ApParam() {
  }

  public ApParam(ApParamId id) {
    this.id = id;
  }

  public ApParam(ApParamId id, String parValue, String description) {
    this.id = id;
    this.parValue = parValue;
    this.description = description;
  }

  public ApParamId getId() {
    return this.id;
  }

  public void setId(ApParamId id) {
    this.id = id;
  }

  public String getParValue() {
    return this.parValue;
  }

  public void setParValue(String parValue) {
    this.parValue = parValue;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  
  public static enum ParamType {
    CHANNEL("CHANNEL");

    private String name;

    private ParamType(String name) {
      this.name = name;
    }
   
    public String getName() {
      return name;
    }
  }
  
  
  public static enum ChannelType {
    APP("APP", 1L, "kenh etopup app"),
    SMS("SMS", 2L, "Kenh SMS"),
    WEB("WEB", 3L, "kenh soap"),
    MPOS("MPOS", 4L, "Kenh mpos"),
    EPOS("EPOS", 5L, "Kenh Epos"),
    EPOS_CHANGESIM("EPOS CHANGESIM", 7L, "Kenh Epos doi sim"),
    EPOS_CCARD("EPOS CCARD", 8L, "Kenh Epos doi card"),
    EPOS_SIMCARD("EPOS SIMCARD", 9L, "Kenh Epos dat hang sim the");

    private String name;
    private Long value;

    private ChannelType(String name, Long value, String desc) {
      this.value = value;
      this.name = name;
    }

    public Long getValue() {
      return value;
    }

    public String getName() {
      return name;
    }
  }

}