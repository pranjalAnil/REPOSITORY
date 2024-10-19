package com.example.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_Data")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    private String email;


    private String password;


    private String about;

    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    private List<Post> posts= new ArrayList<>();

}
