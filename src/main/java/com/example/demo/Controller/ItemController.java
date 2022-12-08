package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.ItemDTO;
import com.example.demo.Model.Item;
import com.example.demo.Repository.ItemRepo;

import jakarta.validation.Valid;

@Controller
public class ItemController {

  @Autowired
  private ItemRepo itemRepo;

  @GetMapping("/Manager/DeleteItem")
  public String deleteUser(@RequestParam(required = false, value = "id") Long id){
    if(id != null){
        itemRepo.deleteById(id);
        return "redirect:/Manager?deleteSuccess";
    } else {
        return "redirect:/Manager?noexist";
    }
  }
  
  @PostMapping(value = "/Add-Item/process")
  public String processAddItem(@Valid @ModelAttribute("item") ItemDTO itemDto, BindingResult result, Model model){
    Item existingItem = itemRepo.findByName(itemDto.getName());

    if(existingItem != null && existingItem.getName() != null && !existingItem.getName().isEmpty()){
      result.rejectValue("name", null,
                    "There is already an item registered with the same name");
    }

    if(result.hasErrors()){
      model.addAttribute("user", itemDto);
      return "Manager/Additem";
    }

    Item newItem = new Item();
    newItem.setAvailable(itemDto.isAvailable());
    newItem.setCategory(itemDto.getCategory());
    newItem.setDescription(itemDto.getDescription());
    newItem.setImageUrl(itemDto.getImageUrl());
    newItem.setName(itemDto.getName());
    newItem.setOffer(itemDto.getOffer());
    newItem.setPrice(itemDto.getPrice());
    newItem.setStock(itemDto.getStock());
    itemRepo.save(newItem);

    return "redirect:/Manager?success";
  }
}
