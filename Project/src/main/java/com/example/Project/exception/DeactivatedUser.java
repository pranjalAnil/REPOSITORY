package com.example.Project.exception;

public class DeactivatedUser extends RuntimeException {
    String email;
    public DeactivatedUser(String email){
        super(String.format("user with %s is deactivated ", email));
        this.email =email;
    }
}
