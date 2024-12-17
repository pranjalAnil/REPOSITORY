package com.example.Refer.a.Friend.exceptions;

public class AccessDenied extends RuntimeException{
    String val;
   public AccessDenied(String val){
       super(String.format("%s for user the please permit your contacts",val));
       this.val=val;
   }
}
