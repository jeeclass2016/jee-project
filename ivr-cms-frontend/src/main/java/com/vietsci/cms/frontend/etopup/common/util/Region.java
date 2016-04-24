package com.vietsci.cms.frontend.etopup.common.util;


public enum Region {
  NONE(0, ""),
  NORTHERN(1, "Miền Bắc"),
  CENTRAL(2, "Miền Trung"),
  SOUTHERN(3, "Miền Nam");

  private int value;
  private String name;


  private Region(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public static Region getRegionFromValue(int value) {
    if (NORTHERN.getValue() == value) {
      return NORTHERN;
    } else if (CENTRAL.getValue() == value) {
      return CENTRAL;
    } else if (SOUTHERN.getValue() == value) {
      return SOUTHERN;
    } else {
      return NONE;
    }
  }
  
  public static int getRegionIdFromName(String name) {
    if (NORTHERN.getName().equals(name)) {
      return NORTHERN.value;
    } else if (CENTRAL.getName().equals(name)) {
      return CENTRAL.value;
    } else if (SOUTHERN.getName().equals(name)) {
      return SOUTHERN.value;
    } else {
      return NONE.value;
    }
  }
}
