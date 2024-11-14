package com.example.Project.exception;

public class EmailExists extends RuntimeException{
    String resourceName;
    String email;
    public EmailExists(String resourceName,String email){
        super(String.format("%s already exists with %s", resourceName, email));
        this.resourceName=resourceName;
        this.email =email;
    }

}
