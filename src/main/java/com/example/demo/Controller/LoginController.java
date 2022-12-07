package com.example.demo.Controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Rank;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.UserRepo;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("/signup/process")
    public String processRegistration (@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
        User existingUser = userRepo.findByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "signup";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User newUser = new User();
        String encodedpassword = encoder.encode(userDto.getPassword());
        newUser.setPassword(encodedpassword);
        newUser.setRank(Rank.CUSTOMER);
        newUser.setPhone(userDto.getPhone());
        newUser.setEmail(userDto.getEmail());
        newUser.setName(userDto.getName());
        userRepo.save(newUser);
        User savedUser = userRepo.findByEmail(userDto.getEmail());
        if(savedUser.getRank().equals(Rank.CUSTOMER)){
            customerRepo.save(new Customer(savedUser.getId()));
        }
        return "redirect:/signin?signupSuccess";
    }
    
    @GetMapping(value = "/login/success")
    public String goToHome(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(auth.getName());
        switch (user.getRank()) {
            case CUSTOMER:
                return "redirect:/Customer";

            case ADMIN:
                return "redirect:/Admin";
            
            case MANAGER:
                return "redirect:/Manager";
            
            default:
                return "redirect:/";
        }
    }

}