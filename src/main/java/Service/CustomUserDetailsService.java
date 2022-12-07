package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  public CustomUserDetailsService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    list.add(new SimpleGrantedAuthority("ROLE_" + user.getRank().toString()));
    System.out.println(list);
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
    list);
  }
  
}
