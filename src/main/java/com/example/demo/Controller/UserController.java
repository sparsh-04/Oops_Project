package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  
  @GetMapping("/Admin/users")
  public String users(Model model){
    List<User> users = userService.findAllUsers();
    model.addAttribute("users", users);
    return "users";
  }
}
