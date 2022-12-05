package com.example.demo.Controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Rank;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;

@Controller
public class appcontroller {

    @Autowired
    private UserRepo repo;
    
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup" ;
    }

    @PostMapping("/process_register")
    public String processRegistration (@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
        User existingUser = repo.findByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/signup";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User newUser = new User();
        String encodedpassword = encoder.encode(userDto.getPassword());
        newUser.setPassword(encodedpassword);
        newUser.setRank(Rank.CUSTOMER);
        newUser.setPhone(userDto.getPhone());
        newUser.setEmail(userDto.getEmail());
        newUser.setName(userDto.getName());
        repo.save(newUser);
        return "redirect:/signup?success";
        }

    @RequestMapping(value = "/signin", method = RequestMethod.GET) 
    public String displayLogin(Model model) { 
        return "signin"; 
    }
    
    @GetMapping(value = "/login_success")
    public String goToHomePage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repo.findByEmail(auth.getPrincipal().toString());
        switch (user.getRank()) {
            case CUSTOMER:
                return "Costumer";

            case ADMIN:
                return "Admin_HomePage";
            
            case MANAGER:
                return "Manager_Homepage";
            
            default:
                return "index";
        }
    }
}