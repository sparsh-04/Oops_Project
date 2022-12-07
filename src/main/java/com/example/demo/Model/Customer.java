package com.example.demo.Model;

import com.example.demo.Converters.*;
import java.util.List;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

  public Customer(long id){
    this.id = id;
  }

  @Id
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Convert(converter = CartItemConverter.class)
  private List<CartItem> cart;

  public List<CartItem> getCart() {
    return cart;
  }

  public void setCart(List<CartItem> cart) {
    this.cart = cart;
  }

  // After an order, the order is stored in the oreders table and its id is copied
  // and added here
  @Convert(converter = LongListConverter.class)
  private List<Long> orderHistory;

  public List<Long> getOrderHistory() {
    return orderHistory;
  }

  public void setcart(List<Long> orderHistory) {
    this.orderHistory = orderHistory;
  }

  @Convert(converter = LongListConverter.class)
  private List<Long> wishlist;

  public List<Long> getWishlist() {
    return wishlist;
  }

  public void setWishlist(List<Long> wishlist) {
    this.wishlist = wishlist;
  }

  private long walletAmount;

  public long getWalletAmount() {
    return walletAmount;
  }

  public void setWalletAmount(long walletAmount) {
    this.walletAmount = walletAmount;
  }
}
