package com.example.Task_6_CRUD_XML.services;


import com.example.Task_6_CRUD_XML.entity.User;
import com.example.Task_6_CRUD_XML.repository.UserRepo;
import com.example.Task_6_CRUD_XML.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;
    @Test
    public void addUserTest(){
        User user = new User();
        user.setUserName("abc");
        user.setMobileNumber(992337499);
        userRepo.save(user);
        assertFalse(userRepo.findById(user.getId()).isEmpty(),"User should be created in database");

    }





}
