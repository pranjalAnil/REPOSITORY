package com.example.Task_9_Swagger.service;

import com.example.Task_9_Swagger.entity.User;
import com.example.Task_9_Swagger.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User addUser(User user){
        return userRepo.save(user);
    }
}
