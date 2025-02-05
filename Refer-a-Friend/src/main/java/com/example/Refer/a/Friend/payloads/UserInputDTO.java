package com.example.Refer.a.Friend.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserInputDTO {
    private int id;
    @NotEmpty
    @Size(min = 10,max = 10 ,message = "mobile length should 10")
    private String mobileNumber;
    @NotEmpty(message = "User name cannot be empty")
    @NotEmpty(message = "password cannot be empty")
    private  String password;
    @NotEmpty(message = "Please confirm your password")
    private String confirmPassword;
    @NotEmpty(message = "name field cannot be empty")
    private String name;
    private String referralCode;
    private String referredCode;
    private boolean ContactsPermission;
}
