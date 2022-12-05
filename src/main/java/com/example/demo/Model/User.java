package com.example.demo.Model;

import com.example.demo.Converters.*;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
  @Column(nullable = false,unique = true)
  private String email;
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  @Column(nullable = false)
  private String password;
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  @Column(nullable = false)
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Column(nullable = false)
  private long phone;
  public long getPhone() {
    return phone;
  }
  public void setPhone(long phone) {
    this.phone = phone;
  }

  @Column(name = "addresses")
  @Convert(converter = StringListConverter.class)
  private List<String> addresses;
  public List<String> getAddresses() {
    return addresses;
  }
  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "urank")
  private Rank rank;
  public Rank getRank() {
    return rank;
  }
  public void setRank(Rank rank) {
    this.rank = rank;
  }
}
