package com.example.demo.Model;

public enum ProductCategory {
  BOOKS("Books"),
  GROCERY("Grocery"),
  ELECTRONICS("Electronics"),
  FASHION("Fashion"),
  GENERAL("General");

  private final String displayValue;
    
  private ProductCategory(String displayValue) {
    this.displayValue = displayValue;
  }
    
  public String getDisplayValue() {
    return displayValue;
  }
}
