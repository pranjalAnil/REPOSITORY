package com.example.Task_6_CRUD_XML.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    String userName;
    long mobileNumber;
}
