package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.customuserdetails;
import com.example.demo.model.user;
import com.example.demo.repository.userrepo;

public class userdetails implements UserDetailsService{
    
    @Autowired
    private userrepo repo;

    @Override
    public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        user user = repo.findByemail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new customuserdetails(user);
    }
}