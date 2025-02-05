package com.example.Refer.a.Friend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ConfigurableValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String keyNew;
    private int value;
    private String constVar;

}
