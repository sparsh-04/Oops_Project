package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long code;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(nullable = false)
  private float price;

  @Column(name = "offer", nullable = true)
  private String offer;

  private int stock;

  @Column(nullable = false)
  private boolean available;

  private String description;

  private String imageUrl;

  @Enumerated(EnumType.STRING)
  private ProductCategory category;
}
