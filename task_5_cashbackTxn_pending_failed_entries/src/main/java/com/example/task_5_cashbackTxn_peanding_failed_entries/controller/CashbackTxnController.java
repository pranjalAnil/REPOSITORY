package com.example.task_5_cashbackTxn_peanding_failed_entries.controller;

import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.*;
import com.example.task_5_cashbackTxn_peanding_failed_entries.services.CashbackTxnServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request/cashback")
public class CashbackTxnController{

    @Autowired
    CashbackTxnServices cashbackTxnServices;

    /**
     *
     * @param cashbackTxn1Req ->> (int amount,String payeeVpa,int mcc,String
     *                        payerName,String accType,String bankName)
     *
     * @return ->> saved cashbackTxn1Req
     */
    @PostMapping("/txn1")
    public ResponseEntity<CashbackTxn1> cashbackTxn2Request(@RequestBody CashbackTxn1 cashbackTxn1Req){
        CashbackTxn1 cashbackTxn1 = cashbackTxnServices
                .cashbackTxn1Request(cashbackTxn1Req);
        return new ResponseEntity<>(cashbackTxn1, HttpStatus.CREATED);
    }

    @PostMapping("/txn2")
    public ResponseEntity<CashbackTxn2> cashbackTxn2Request(@RequestBody CashbackTxn2 cashbackTxn2Req){
        CashbackTxn2 cashbackTxn2= cashbackTxnServices
                .cashbackTxn2Request(cashbackTxn2Req);
        return new ResponseEntity<>(cashbackTxn2Req, HttpStatus.CREATED);
    }
    @PostMapping("/txn3")
    public ResponseEntity<CashbackTxn3> cashbackTxn3Request(@RequestBody CashbackTxn3 cashbackTxn3Req){
        CashbackTxn3 cashbackTxn3 = cashbackTxnServices
                .cashbackTxn3Request(cashbackTxn3Req);
        return new ResponseEntity<>(cashbackTxn3, HttpStatus.CREATED);
    }

    @PostMapping("/txn4")
    public ResponseEntity<CashbackTxn4> cashbackTxn4Request(@RequestBody CashbackTxn4 cashbackTxn4Req){
        CashbackTxn4 cashbackTxn4 = cashbackTxnServices
                .cashbackTxn4Request(cashbackTxn4Req);
        return new ResponseEntity<>(cashbackTxn4, HttpStatus.CREATED);
    }

    @PostMapping("/txn5")
    public ResponseEntity<CashbackTxn5> cashbackTxn5Request(@RequestBody CashbackTxn5 cashbackTxn5Req){
        CashbackTxn5 cashbackTxn5 = cashbackTxnServices
                .cashbackTxn5Request(cashbackTxn5Req);
        return new ResponseEntity<>(cashbackTxn5, HttpStatus.CREATED);
    }




}
