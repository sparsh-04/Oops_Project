package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
  
}
