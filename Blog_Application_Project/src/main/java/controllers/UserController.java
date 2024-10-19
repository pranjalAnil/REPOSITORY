package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payloads.UserDto;
import repositories.UserRepo;
import services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createUserDto = userService.createUser(userDto);
        return new ResponseEntity(createUserDto, HttpStatus.CREATED);
    }
//    @PostMapping
//    public User user(@RequestBody User user){
//        return userRepo.save(user);
//    }

//    @PostMapping("/")
//    public UserDto createUser(@RequestBody UserDto userDto)
//    {
//        UserDto createUserDto=this.userService.createUser(userDto);
//        return createUserDto;
//    }


}
