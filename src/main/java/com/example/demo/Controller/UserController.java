package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Rank;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.UserRepo;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

  @Autowired
  private CustomerRepo customerRepo;

  @PostMapping("/Admin/AddUser/process")
  public String processRegistration (@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
      User existingUser = userRepo.findByEmail(userDto.getEmail());

      if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
          result.rejectValue("email", null,
                  "There is already an account registered with the same email");
      }

      if(result.hasErrors()){
          model.addAttribute("user", userDto);
          return "adduser";
      }

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      User newUser = new User();
      String encodedpassword = encoder.encode(userDto.getPassword());
      newUser.setPassword(encodedpassword);
      newUser.setRank(userDto.getRank());
      newUser.setPhone(userDto.getPhone());
      newUser.setEmail(userDto.getEmail());
      newUser.setName(userDto.getName());
      userRepo.save(newUser);
      User savedUser = userRepo.findByEmail(userDto.getEmail());
      if(savedUser.getRank().equals(Rank.ADMIN)){
          customerRepo.save(new Customer(savedUser.getId()));
      }
      return "redirect:/Admin/Users";
  }

}
