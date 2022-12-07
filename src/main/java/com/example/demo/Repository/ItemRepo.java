package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Item;
import com.example.demo.Model.ProductCategory;

public interface ItemRepo extends JpaRepository<Item, Long>{
  @Query("SELECT x FROM Item x WHERE x.name = ?1")
  Item findByName(String name);

  @Query("SELECT x FROM Item x WHERE x.available = 1")
  List<Item> getAvailableList();

  @Query("SELECT x From Item x WHERE x.category = ?1")
  List<Item> getItemsbyCategory(ProductCategory category);
}
