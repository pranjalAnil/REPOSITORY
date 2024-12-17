package com.example.Refer.a.Friend.controller;
import com.example.Refer.a.Friend.payloads.UserInputDTO;
import com.example.Refer.a.Friend.payloads.UserOutputDto;
import com.example.Refer.a.Friend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userOnBoarding")
public class UserOnBoardingController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserOutputDto> userOnboarding(@RequestBody @Valid UserInputDTO userInputDTO){
        return new ResponseEntity<>(userService.OnboardUser(userInputDTO),HttpStatus.OK);
    }
}
