package com.example.Refer.a.Friend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String mobileNumber;
}
