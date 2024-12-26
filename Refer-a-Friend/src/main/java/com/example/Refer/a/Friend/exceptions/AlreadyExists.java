package com.example.Refer.a.Friend.exceptions;

public class AlreadyExists extends RuntimeException{
    public AlreadyExists (){
        super(String.format("User Already Exists"));
    }
}
