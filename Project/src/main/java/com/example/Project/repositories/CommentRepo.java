package com.example.Project.repositories;

import com.example.Project.entities.Comment;
import com.example.Project.entities.Post;
import com.example.Project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByUser(User user);
}
