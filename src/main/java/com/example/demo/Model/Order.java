package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ordersPlaced")
public class Order {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  private float price;
  public float getPrice() {
    return price;
  }
  public void setPhone(float price) {
    this.price = price;
  }

  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  private int quantity;
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  private int deliveredTime;
  public int getDeliveredTime() {
    return deliveredTime;
  }
  public void setDeliveredTime(int deliveredTime) {
    this.deliveredTime = deliveredTime;
  }

  private String imageUrl;
  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
