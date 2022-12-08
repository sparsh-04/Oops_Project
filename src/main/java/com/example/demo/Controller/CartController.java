package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.CartItem;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Item;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.ItemRepo;
import com.example.demo.Repository.UserRepo;

@Controller
public class CartController {

  @Autowired
  private ItemRepo itemRepo;
  
  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private UserRepo userRepo;

  @GetMapping("/Customer/Cart/Delete")
  public String deleteCartItem(@RequestParam(value = "itemId") Long itemId){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepo.findByEmail(auth.getName());
    Long customerId = user.getId();
    if(customerId != null){
      Customer customer = customerRepo.findById(customerId).orElse(null);
      if(customer == null){
        return "redirect:/Customer?notacustomer";
      }
      for(CartItem item: customer.getCart()){
        if(item.getId() == itemId){
          List<CartItem> items = customer.getCart();
          items.remove(item);
          customer.setCart(items);
        }
      }
      customerRepo.save(customer);
      return "redirect:/Customer/Cart?deleteSuccess";
  } else {
      return "redirect:/Customer/Cart?noexist";
  }
  }

  @GetMapping("/Customer/Cart/Add")
  public String addCartItem(@RequestParam(value = "itemId") Long itemId){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepo.findByEmail(auth.getName());
    Long customerId = user.getId();
    if(customerId != null){
      Customer customer = customerRepo.findById(customerId).orElse(null);
      if(customer == null){
        return "redirect:/Customer?notacustomer";
      }
      Item item = itemRepo.findById(itemId).orElse(null);
      List<CartItem> items = customer.getCart();
      items.add(new CartItem(item.getCode(), 1));
      customerRepo.save(customer);
      return "redirect:/Customer/Cart?addSuccess";
  } else {
      return "redirect:/Customer/Cart?noexist";
  }
  }

  @GetMapping("/Customer/Cart/Increase")
  public String increaseCartItem(@RequestParam(value = "itemId") Long itemId){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepo.findByEmail(auth.getName());
    Long customerId = user.getId();
    if(customerId != null){
      Customer customer = customerRepo.findById(customerId).orElse(null);
      if(customer == null){
        return "redirect:/Customer?notacustomer";
      }
      Item item = itemRepo.findById(itemId).orElse(null);
      List<CartItem> items = customer.getCart();
      for(CartItem citem: items){
        if(citem.getId() == itemId){
          items.remove(citem);
          items.add(new CartItem(item.getCode(), citem.getQuantity() + 1));
          break;
        }
      }

      customer.setCart(items);
      
      customerRepo.save(customer);
      return "redirect:/Customer/Cart?increaseSuccess";
  } else {
      return "redirect:/Customer/Cart?noexist";
  }
  }

  @GetMapping("/Customer/Cart/Decrease")
  public String decreaseCartItem(@RequestParam(value = "itemId") Long itemId){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepo.findByEmail(auth.getName());
    Long customerId = user.getId();
    if(customerId != null){
      Customer customer = customerRepo.findById(customerId).orElse(null);
      if(customer == null){
        return "redirect:/Customer?notacustomer";
      }
      Item item = itemRepo.findById(itemId).orElse(null);
      List<CartItem> items = customer.getCart();
      for(CartItem citem: items){
        if(citem.getId() == itemId){
          items.remove(citem);
          items.add(new CartItem(item.getCode(), (citem.getQuantity() - 1) > 0 ? citem.getQuantity() - 1: 1));
          break;
        }
      }

      customer.setCart(items);
      
      customerRepo.save(customer);
      return "redirect:/Customer/Cart?increaseSuccess";
  } else {
      return "redirect:/Customer/Cart?noexist";
  }
  }

}
