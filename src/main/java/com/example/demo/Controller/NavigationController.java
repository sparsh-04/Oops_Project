package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.ItemDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Item;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.ItemRepo;
import com.example.demo.Repository.UserRepo;

@Controller
public class NavigationController {
    @Autowired
    private ItemRepo itemRepo;

    private List<Item> itemList;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

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
        itemList = itemRepo.findAll();
        model.addAttribute("items", itemList);
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

    @GetMapping(value = "/Manager/Add-Item")
    public String goToAddItemPage(Model model){
        model.addAttribute("item", new ItemDTO());
        return "Manager/Additem";
    }

    @GetMapping(value = "/Admin/Add-User")
    public String goToAddUserPage(Model model){
        model.addAttribute("user" , new UserDTO());
        return "adduser";
    }

    @GetMapping(value = "/Customer-Support")
    public String goToCustomerSupport(Model model){
        return "Customerservice";
    }
    
  @GetMapping("/Admin/Users")
  public String showUsersList(Model model){
    List<User> users = userRepo.findAll();
    model.addAttribute("users", users);
    return "Userlist";
  }
  
  @GetMapping("/Customer/Item")
  public String showItemDescCustomer(Model model, @RequestParam(required = false, value="id") Long id){
    Item item;
    if(id != null){
    item = itemRepo.findById(id).orElse(null);
    } else {
        item = null;
    }
    model.addAttribute("item", item);
    return "iteminfocustomer";
  }

  @GetMapping("/Manager/Item")
  public String showItemDescManager(Model model, @RequestParam(required = false, value="id") Long id){
    Item item;
    if(id != null){
    item = itemRepo.findById(id).orElse(null);
    } else {
        item = null;
    }
    model.addAttribute("item", item);
    return "iteminfomanager";
  }

  @GetMapping("/Admin/User")
  public String showUserDesc(Model model, @RequestParam(required = false, value="id") Long id){
    User user;
    if(id != null){
    user = userRepo.findById(id).orElse(null);
    } else {
        user = null;
    }
    model.addAttribute("user", user);

    return "userinfo";
  }

  @GetMapping("/Customer/Cart")
  public String showCart(Model model){
    return "cart";
  }

  @GetMapping("/wallet")
  public String showwallet(Model model){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userRepo.findByEmail(auth.getName());
    Customer customer = customerRepo.findById(user.getId()).orElse(null);
    model.addAttribute("wallet", customer.getWalletAmount());
    return "wallet";
  }
}
