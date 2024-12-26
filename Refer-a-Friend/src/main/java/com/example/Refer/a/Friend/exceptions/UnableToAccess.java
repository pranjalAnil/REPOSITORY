package com.example.Refer.a.Friend.exceptions;

public class UnableToAccess extends RuntimeException{
    public UnableToAccess(){
        super(String.format("Unable To access Please Try Again"));
    }
}
