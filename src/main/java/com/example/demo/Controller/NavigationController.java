package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.DTO.ItemDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Item;
import com.example.demo.Repository.ItemRepo;

@Controller
public class NavigationController {
    @Autowired
    private ItemRepo itemRepo;

    private List<Item> itemList;

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

    
    @GetMapping(value = "/Customer")
    public String goToCustomerHome(Model model){
        return "Costumer";
    }

    @GetMapping(value = "/Admin")
    public String goToAdminHome(Model model){
        return "Admin_HomePage";
    }

    @GetMapping(value = "/Manager")
    public String goToManagerHome(Model model){
        itemList = itemRepo.findAll();
        model.addAttribute("items", itemList);
        return "Manager_Homepage";
    }

    @GetMapping(value = "/Add-Item")
    public String goToAddItemPage(Model model){
        model.addAttribute("item", new ItemDTO());
        return "Manager/Additem";
    }

    @GetMapping(value = "/adduser")
    public String goToAddUserPage(Model model){
        model.addAttribute("user" , new UserDTO());
        return "Admin/adduser";
    }
}
