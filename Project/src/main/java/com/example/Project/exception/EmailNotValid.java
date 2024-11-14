package com.example.Project.exception;

import jakarta.validation.constraints.Email;

public class EmailNotValid extends RuntimeException{
    String email;
    public EmailNotValid(String email){
        super(String.format("%s not valid", email));
        this.email =email;
    }
}
