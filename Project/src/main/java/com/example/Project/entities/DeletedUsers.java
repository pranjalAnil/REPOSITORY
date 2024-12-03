package com.example.Project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "deleted_user")
public class DeletedUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    @Getter(value = AccessLevel.NONE)
    private String password;

    private String about;

    private List<String> role = new ArrayList<>();

    private Timestamp timestamp;

}
