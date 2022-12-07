package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Rank;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;

@Service
public class Adminservice {
    
    @Autowired
    UserRepo repo;

    public Adminservice(UserRepo repo){
        this.repo = repo;
    }

    public void adduser(UserDTO userDTO , User user){
        new User();
        user.setRank(Rank.ADMIN);
        repo.save(user);
    }

    public void deleteuser(String email){
        repo.deleteAll();
    }
}
