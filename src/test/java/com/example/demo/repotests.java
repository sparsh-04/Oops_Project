package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.Repository.UserRepo;
import com.example.demo.Model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class repotests {
    @Autowired
    private UserRepo repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testcreateuser(){
        User user = new User();
        user.setEmail("rohan@gmail.com");
        user.setPassword("rhonda1818");
        user.setName("rohan");

        User saveduser = repo.save(user);

        User existuser = entityManager.find(User.class, saveduser.getId());

        assertThat(existuser.getEmail()).isEqualTo(user.getEmail());


    }

    @Test
    public void testfindbyemail(){
        String email = "bingoboss@gmail.com";
        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
