package com.example.Refer.a.Friend.exceptions;

public class AccessDenied extends RuntimeException{
    String val;
   public AccessDenied(String val){
       super(String.format("%s : Allow us to access contacts",val));
       this.val=val;
   }
   public AccessDenied(){
       super(String.format("Invalid request please try again"));
   }

}
