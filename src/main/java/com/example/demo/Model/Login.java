package com.example.demo.Model;

import org.springframework.stereotype.Component;

@Component
public class Login {
    private String email;
    private String password;

    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }

    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password = password;
    }
}
