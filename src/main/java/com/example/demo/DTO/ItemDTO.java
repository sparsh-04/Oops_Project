package com.example.demo.DTO;

import com.example.demo.Model.ProductCategory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  @NotEmpty(message = "Name cannot be empty")
  private String name;

  @Positive(message = "Price cannot be zero")
  private float price;

  private boolean available;

  @NotEmpty(message = "Image cannot be empty")
  private String imageUrl;

  private ProductCategory category;

  private int stock;

  private String offer;

  private String description;
}
