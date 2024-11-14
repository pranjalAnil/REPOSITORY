package com.example.Project.controllers;

import com.example.Project.entities.User;
import com.example.Project.exception.EmailExists;
import com.example.Project.exception.EmailNotValid;
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
    public ResponseEntity<?> createUser( @RequestBody @Valid UserDto userDto) {
        try {
            UserDto createUserDto = userServices.createUser(userDto);
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        } catch (EmailExists e) {
            APIResponse response = new APIResponse("Email already exists.", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }catch (EmailNotValid e){
            APIResponse response = new APIResponse("Email not valid.", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }
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
    public ResponseEntity<?> updateUserById(@Valid @RequestBody UserDto userDto) {
        try {
            UserDto userDto1 = userServices.update(userDto);
            return ResponseEntity.ok(userDto1);
        } catch (EmailExists e) {
            APIResponse response = new APIResponse("Email already exists.", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (EmailNotValid e) {
            APIResponse response = new APIResponse("Email not valid.", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);


        }
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
