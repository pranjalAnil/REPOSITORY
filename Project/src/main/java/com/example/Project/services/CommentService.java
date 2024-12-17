package com.example.Project.services;

import com.example.Project.payloads.CommentDto;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto,int postId);
    String deleteComment(int commentId);
     CommentDto updateComment(CommentDto commentDto,int commentId);

}
