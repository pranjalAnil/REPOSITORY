package com.example.Project.Services;
import com.example.Project.entities.User;
import com.example.Project.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ParameterizedTest
    @CsvSource({
            "1","2","10","51","15"
    })
    public void testGetUserById(int id) {
        assertFalse(userRepo.findById(id).isEmpty());
    }

    @Test
    public void testGetAllUsers(){
        assertFalse(userRepo.findAll().isEmpty());
    }

    @Test
    public void testDeleteUser(){
        User user=userRepo.findById(44).orElseThrow();
        userRepo.delete(user);
        assertTrue(userRepo.findById(66).isEmpty());
    }

    @Test
    public void testUpdate(){
        User userTest=userRepo.findById(45).orElseThrow();
        User user=userRepo.findById(45).orElseThrow();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setAbout("Nashik");
        user.setPassword(passwordEncoder.encode("ab@123"));
        userRepo.save(user);
        user.setRole(Collections.singletonList("ROLE_NORMAL_USER"));
        assertNotEquals(userTest,user);
    }

    @Test
    public void testCreateUser(){
        User user=new User();
        user.setName("ABC");
        user.setEmail("abc@gmail.com");
        user.setRole(Collections.singletonList("ROLE_NORMAL_USER"));
        user.setPassword(passwordEncoder.encode("abc@123"));
        user.setAbout("Nashik");
        userRepo.save(user);
        assertFalse(userRepo.findById(user.getId()).isEmpty());
    }











}
