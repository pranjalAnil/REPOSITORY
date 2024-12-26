package com.example.Refer.a.Friend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class CashBackHistoryReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int referredUser;
    private int referralUser;
    private String referralCode;
    private double cbAmount;
    private String status;   // 0-success, 1 pending, 2-failed
    private Timestamp updatedAt;
    private Timestamp createdAt;
}
