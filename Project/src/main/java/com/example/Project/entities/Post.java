package com.example.Project.entities;

import com.example.Project.payloads.CategoryDto;
import com.example.Project.payloads.UserDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;

    private String content;

    private String imageName;

    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments =new ArrayList<>();





}
