package com.example.Refer.a.Friend.exceptions;

public class WrongConfirmationPassword  extends RuntimeException {
       public WrongConfirmationPassword() {
        super(String.format("confirmation password dose not match with password"));

    }
}

