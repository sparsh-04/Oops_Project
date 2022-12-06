package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Item;

public interface ItemRepo extends JpaRepository<Item, Long>{
  @Query("SELECT x FROM Item x WHERE x.name = ?1")
  Item findByName(String name);
}
