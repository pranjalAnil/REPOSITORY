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

    /**
     *
     * @param userDto -> name, email, password, about
     * @return created user with response 201 created
     */
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createUserDto = userServices.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    /**
     *
     * @return List<UserDto>
     */
    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userServices.getAllUsers();
    }


    /**
     *
     * @param id -> userId
     * @return user
     */
    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userServices.getUserById(id);
    }


    /**
     *
     * @param userDto ->  name, email, password, about
     * @return updated user with response 200 ok
     */
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userServices.update(userDto);
        return ResponseEntity.ok(userDto1);

    }

    /**
     *
     * @return NO_CONTENT
     */
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<APIResponse> deleteUserByID() {
        userServices.deleteUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
