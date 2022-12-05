package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long code;
  public Long getCode() {
    return code;
  }
  public void setCode(Long code) {
    this.code = code;
  }

  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  private float price;
  public float getPrice() {
    return price;
  }
  public void setPhone(float price) {
    this.price = price;
  }

  @Column(name = "offer", nullable = true)
  private String offer;
  public String getOffer() {
    return offer;
  }
  public void setOffer(String offer) {
    this.offer = offer;
  }

  private int stock;
  public int getStock() {
    return stock;
  }
  public void setStock(int stock) {
    this.stock = stock;
  }

  private int deliveryTime;
  public int getDeliveryTime() {
    return deliveryTime;
  }
  public void setDeliveryTime(int deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  private boolean available;
  public boolean getAvailable() {
    return available;
  }
  public void setAvailable(boolean available) {
    this.available = available;
  }

  private String imageUrl;
  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Enumerated(EnumType.STRING)
  private ProductCatagory catagory;
  public ProductCatagory getCatagory() {
    return catagory;
  }
  public void setCatagory(ProductCatagory catagory) {
    this.catagory = catagory;
  }
}
