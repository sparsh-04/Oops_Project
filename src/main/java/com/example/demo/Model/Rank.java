package com.example.demo.Model;

public enum Rank {
  CUSTOMER("Customer"),
  MANAGER("Manager"),
  ADMIN("Admin");

  private final String displayValue;

  private Rank(String displayValue) {
    this.displayValue = displayValue;
  }

  public String getDisplayValue() {
    return displayValue;
  }
}
