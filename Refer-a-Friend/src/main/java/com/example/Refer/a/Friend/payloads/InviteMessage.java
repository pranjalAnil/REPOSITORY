package com.example.Refer.a.Friend.payloads;

import lombok.Data;

@Data
public class InviteMessage {
    private String title;
    private String content;
    private String referralCode;
}
