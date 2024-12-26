package com.example.Refer.a.Friend.exceptions;

public class ContactAccessIssue extends RuntimeException{
   public ContactAccessIssue(){
       super(String.format("Not able to access contact"));
   }
}
