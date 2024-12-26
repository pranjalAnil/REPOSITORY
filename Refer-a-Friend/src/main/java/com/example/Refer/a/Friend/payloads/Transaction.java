package com.example.Refer.a.Friend.payloads;
import lombok.Data;


@Data
public class Transaction {
    private int user;
    private int toUser;
    private int amount;
}
