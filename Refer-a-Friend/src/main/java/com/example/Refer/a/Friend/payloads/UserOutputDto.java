package com.example.Refer.a.Friend.payloads;

import lombok.Data;

@Data
public class UserOutputDto {
    private int id;
    private String mobileNumber;
    private String name;
    private String referralCode;
    private String referredCode;
    private boolean ContactsPermission;
}
