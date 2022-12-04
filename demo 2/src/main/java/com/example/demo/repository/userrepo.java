package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.user;

public interface userrepo extends JpaRepository<user , Long > {
    @Query("SELECT x FROM user x WHERE x.email = ?1")
    user findByemail(String email);
}
