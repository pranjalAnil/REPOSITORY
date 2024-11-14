package com.example.Task_6_CRUD_XML.controller;

import com.example.Task_6_CRUD_XML.payload.UserDto;
import com.example.Task_6_CRUD_XML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/add" ,consumes = "application/json", produces = "application/xml")
    public UserDto addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping(value = "/update/{id}",consumes = "application/json", produces = "application/xml")
    public UserDto updateUser(@RequestBody UserDto userDto,@PathVariable int id){
        return userService.updateUser(userDto,id);
    }


    @GetMapping(value = "/getAll",produces = "application/xml")
    public List<UserDto> getAllUser(){
        return userService.getAllUsers();

    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
}
