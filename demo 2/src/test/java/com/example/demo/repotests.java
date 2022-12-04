package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.user;
import com.example.demo.repository.userrepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class repotests {
    @Autowired
    private userrepo repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testcreateuser(){
        user user = new user();
        user.setemail("rohan@gmail.com");
        user.setpassword("rhonda1818");
        user.setusername("rohan");

        user saveduser = repo.save(user);

        user existuser = entityManager.find(user.class, saveduser.getid());

        assertThat(existuser.getemail()).isEqualTo(user.getemail());


    }

    @Test
    public void testfindbyemail(){
        String email = "bingoboss@gmail.com";
        user user = repo.findByemail(email);

        assertThat(user).isNotNull();
    }
}
