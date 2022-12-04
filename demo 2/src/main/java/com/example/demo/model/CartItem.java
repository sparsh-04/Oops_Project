package com.example.demo.model;

public class CartItem {
  public CartItem(){
    this.quantity = 0;
  }
  public CartItem(String[] lst){
    this.id = Long.parseLong(lst[0]);
    this.quantity = Integer.parseInt(lst[1]);
  }

  private long id;
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  private int quantity;
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
