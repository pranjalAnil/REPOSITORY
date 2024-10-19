    package com.example.Project.entities;


    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @Table(name = "comments")
    @Data
    public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int commentId;

        private String content;

        @ManyToOne
        private Post post;

    }
