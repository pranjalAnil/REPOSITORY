package com.example.Project.services;

import com.example.Project.entities.Comment;
import com.example.Project.payloads.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto,int postId);
    void deleteComment(int commentId);
    CommentDto updateComment(CommentDto commentDto,int commentId);

}
