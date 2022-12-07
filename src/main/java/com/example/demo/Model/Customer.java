package com.example.demo.Model;

import com.example.demo.Converters.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Customer {

  public Customer(){
    this.cart = new ArrayList<CartItem>();
    this.orderHistory = new ArrayList<Long>();
    this.wishlist = new ArrayList<Long>();
  }

  public Customer(long id){
    this.id = id;
    this.cart = new ArrayList<CartItem>();
    this.orderHistory = new ArrayList<Long>();
    this.wishlist = new ArrayList<Long>();
  }

  @Id
  private Long id;

  @Convert(converter = CartItemConverter.class)
  private List<CartItem> cart;

  // After an order, the order is stored in the oreders table and its id is copied
  // and added here
  @Convert(converter = LongListConverter.class)
  private List<Long> orderHistory;

  @Convert(converter = LongListConverter.class)
  private List<Long> wishlist;

  private long walletAmount;
}
