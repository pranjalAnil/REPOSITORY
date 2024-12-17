    package com.example.Refer.a.Friend.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.UUID;

    @Entity
    @Data
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(unique = true)
        private String mobileNumber;
        private String name;
        @Column(unique = true)
        private String email;
        @Column(unique = true)
        private String referralCode;
        private String referredCode;
        private boolean ContactsPermission;
        private String Password;


    }