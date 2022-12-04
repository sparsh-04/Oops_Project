package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true, length = 40)
    private String email;

    @Column(nullable = false , unique = true, length = 20)
    private String username;
    
    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, unique = true, length = 10)
    private String phonenumber;

    @Column(nullable = false, unique = false)
    private String name;
    
    @Transient
    private String confirmPassword;

    public String getphonenumber(){
        return phonenumber;
    }
    public void setphonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }
    
    public String getname(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public Long getid(){
        return id;
    }
    public void setid(long id){
        this.id = id;
    }

    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }

    public String getusername(){
        return username;
    }
    public void setusername(String username){
        this.username = username;
    }

    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password = password;
        checkPassword();
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkPassword();
    }
    
    private void checkPassword() {
        if(this.password == null || this.confirmPassword == null){
            return;
        }else if(!this.password.equals(confirmPassword)){
            this.confirmPassword = null;
        }
    }
}
