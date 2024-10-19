package com.example.Project.controllers;

import com.example.Project.payloads.APIResponse;
import com.example.Project.payloads.UserDto;
import com.example.Project.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createUserDto = userServices.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers(){
       List<UserDto> list =userServices.getAllUsers();
        return list;
    }


    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable int id){
        return userServices.getUserById(id);
    }


    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable int id,@Valid @RequestBody UserDto userDto){
        UserDto userDto1=userServices.update(userDto,id);
        return ResponseEntity.ok(userDto1);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse> deleteUserByID(@PathVariable int id){
        userServices.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
