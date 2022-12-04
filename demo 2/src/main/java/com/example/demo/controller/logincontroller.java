package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Login;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@SessionAttributes
public class logincontroller {
    
@RequestMapping(value = "/signin", method = RequestMethod.GET) 
public String displayLogin(Model model) { 
    model.addAttribute("login", new Login()); 
    return "signin"; 
}

@PostMapping(value="/process_login")
public String postMethodName(@ModelAttribute("login") Login login,
BindingResult result) {
    
    return "index";
}

}
