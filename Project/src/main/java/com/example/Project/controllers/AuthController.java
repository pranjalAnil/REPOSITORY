package com.example.Project.controllers;
import com.example.Project.payloads.LoginDto;
import com.example.Project.services.impl.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    /**
     *
     * @param loginDto -> email password
     * @return String -> gives token in output
     */
    @Async
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getEmail()+ " is logged_in");
        return new ResponseEntity<>(verify(loginDto), HttpStatus.OK);

    }
    public String verify(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(loginDto.getEmail());
        }else {
            return "Enter Valid Details";
        }
    }
}
