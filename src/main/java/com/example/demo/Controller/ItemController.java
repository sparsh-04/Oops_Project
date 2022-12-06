package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.ItemDTO;

import jakarta.validation.Valid;

@Controller
public class ItemController {
  
  @PostMapping(value = "/Add-Item/process")
  public String processAddItem(@Valid @ModelAttribute("item") ItemDTO itemDto, BindingResult result, Model model){

    return ""; // TODO: Add the page to show after adding a new item
  }
}
