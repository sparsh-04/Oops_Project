package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.User;

public interface UserRepo extends JpaRepository<User , Long> {
    @Query("SELECT x FROM User x WHERE x.email = ?1")
    User findByEmail(String email);
}
