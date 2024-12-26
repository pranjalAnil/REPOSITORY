package com.example.Refer.a.Friend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ReferFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inviteId;

    private int refereeId;

    private String referralCode;

    private String referredContact;

    private String status;

}

