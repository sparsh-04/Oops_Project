package com.example.demo.DTO;

import com.example.demo.Model.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartDTO {
  Item item;

  Integer quantity;

  Float price;

  Float totalPrice;
  
}
