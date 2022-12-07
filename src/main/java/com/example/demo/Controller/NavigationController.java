package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.DTO.ItemDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Item;
import com.example.demo.Model.User;
import com.example.demo.Repository.ItemRepo;
import com.example.demo.Repository.UserRepo;

@Controller
public class NavigationController {
    @Autowired
    private ItemRepo itemRepo;

    private List<Item> itemList;

    @Autowired
    private UserRepo userRepo;

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

    @GetMapping(value = "Manager/Add-Item")
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
  
  @GetMapping("/Manager/Item/{ItemId}")
  public String showItemDesc(Model model, @PathVariable(value="itemId") long id){
    User user = userRepo.findById(id).orElse(null);
    if(user == null){
        return "userinfo?noexist";
    }
    model.addAttribute("user", user);

    return "userinfo";
  }

  @GetMapping("/Admin/User/{UserId}")
  public String showDesc(Model model, @PathVariable(value="itemId") long id){
    Item item = itemRepo.findById(id).orElse(null);
    if(item == null){
        return "iteminfo?noexist";
    }
    model.addAttribute("item", item);

    return "iteminfo";
  }
}
