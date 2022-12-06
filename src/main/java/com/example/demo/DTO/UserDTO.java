package com.example.demo.DTO;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
  @NotEmpty(message = "Email should not be empty")
  private String email;

  @NotEmpty(message = "Password should not be empty")
  private String password;

  @Positive(message = "Phone Number should not be empty")
  private long phone;

  @NotEmpty(message = "UserName should not be empty")
  private String name;

  @NotEmpty(message = "Confirm Password should not be empty")
  private String confirmPassword;

  @AssertTrue(message = "Confirm Password and Password should are not same")
  private boolean isPasswordSame(){
    return password.equals(confirmPassword);
    
  }
}