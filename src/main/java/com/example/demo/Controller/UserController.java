package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Rank;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepo repo;
    
    @PostMapping(value = "/Admin/AddUser")
        public String adduser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model){
        User existingUser = repo.findByEmail(userDTO.getEmail());

    if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
        result.rejectValue("name", null, "There is already an Admin registered with the same email ID");
      }
  
      if(result.hasErrors()){
        model.addAttribute("user", userDTO);
        return "/Admin/AddUser";
      }

      User newUser = new User();

      newUser.setRank(Rank.ADMIN);
      repo.save(newUser);
      return "redirect:/Admin";
        }

    }
