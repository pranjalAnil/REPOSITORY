package com.example.Refer.a.Friend.controller;

import com.example.Refer.a.Friend.payloads.Transaction;
import com.example.Refer.a.Friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    UserService userService;
    @PostMapping("/user")
    public ResponseEntity<?> transactionController(@RequestBody Transaction transaction){
        return new ResponseEntity<>(userService.transaction(transaction),HttpStatus.OK);
    }

}
