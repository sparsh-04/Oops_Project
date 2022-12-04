package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.user;
import com.example.demo.repository.userrepo;

@Controller
public class appcontroller {

    @Autowired
    private userrepo repo;
    
    @GetMapping("")
    public String viewhomepage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new user());
        return "signup" ;
    }

    @PostMapping("/process_register")
    public String processRegistration (user user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedpassword = encoder.encode(user.getpassword());
        user.setpassword(encodedpassword);
        repo.save(user);
        return "registration_success";
        }
}