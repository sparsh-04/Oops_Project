package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @PostMapping(value = "/Admin/Add-User")
    public String adduser(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
    User existingUser = repo.findByEmail(userDto.getEmail());

    if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
      result.rejectValue("name", null, "There is already a user registered with the same email ID");
    }
  
    if(result.hasErrors()){
      model.addAttribute("user", userDto);
      return "/Admin/Add-User";
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User newUser = new User();
    String encodedpassword = encoder.encode(userDto.getPassword());

    newUser.setPassword(encodedpassword);
    newUser.setRank(Rank.CUSTOMER);
    newUser.setPhone(userDto.getPhone());
    newUser.setEmail(userDto.getEmail());
    newUser.setName(userDto.getName());
    newUser.setRank(Rank.ADMIN);
    repo.save(newUser);
    return "redirect:/Admin";
  }

}
