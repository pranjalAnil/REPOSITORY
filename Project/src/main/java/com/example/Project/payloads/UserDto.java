package com.example.Project.payloads;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private int id;

    @NotEmpty(message = "name is required")
    @Size(min = 4,message = "User name must be greater than 4 character")
    private String name;

    @Email(message = "Email not valid")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 7,message = "password must be minimum of 3 char and maximum of 4 char")
    private String password;

    @NotEmpty
    private String about;

    private List<String> roleList = new ArrayList<>();



}
