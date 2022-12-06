package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.DTO.UserDTO;

@Controller
public class NavigationController {
    @GetMapping("")
    public String viewHomePage(Model model){
        return "index";
    }

    @GetMapping("/signup")
    public String viewSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup" ;
    }

    @GetMapping(value = "/signin") 
    public String viewLogin(Model model) { 
        return "signin"; 
    }

    
    @GetMapping(value = "/Customer-Home")
    public String goToCustomerHome(){
        return "Costumer";
    }

    @GetMapping(value = "/Admin-Home")
    public String goToAdminHome(){
        return "Admin_HomePage";
    }

    @GetMapping(value = "/Manager-Home")
    public String goToManagerHome(){
        return "Manager_Homepage";
    }
}
