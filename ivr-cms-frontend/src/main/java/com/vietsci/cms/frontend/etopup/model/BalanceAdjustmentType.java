package com.vietsci.cms.frontend.etopup.model;

public enum BalanceAdjustmentType {
  POSITIVE(1, "Điều chỉnh dương"),
  NEGATIVE(0, "Điều chỉnh âm");

  private int value;
  private String name;

  BalanceAdjustmentType(int value, String name) {
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
