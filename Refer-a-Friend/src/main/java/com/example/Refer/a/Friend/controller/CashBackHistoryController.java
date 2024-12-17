package com.example.Refer.a.Friend.controller;

import com.example.Refer.a.Friend.service.CashBackHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashBackHistory")
public class CashBackHistoryController {
    @Autowired
    CashBackHistoryService cashBackHistoryService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCashBackHistory(@PathVariable int userId){
        return new ResponseEntity<>(cashBackHistoryService.cashBackHistory(userId),HttpStatus.OK);
    }
}
