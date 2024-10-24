package com.example.Project.payloads;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {
    @Email
    String email;
    String password;
}
