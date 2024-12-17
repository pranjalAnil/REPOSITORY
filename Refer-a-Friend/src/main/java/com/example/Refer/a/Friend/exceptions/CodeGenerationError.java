package com.example.Refer.a.Friend.exceptions;

public class CodeGenerationError extends RuntimeException{
    public CodeGenerationError(){
        super(String.format("Error While generating referral code"));
    }
}
