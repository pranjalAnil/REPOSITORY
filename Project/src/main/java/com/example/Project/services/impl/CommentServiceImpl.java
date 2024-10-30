package com.example.Project.services.impl;

import com.example.Project.entities.Comment;
import com.example.Project.entities.Post;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.CommentDto;
import com.example.Project.repositories.CommentRepo;
import com.example.Project.repositories.PostRepo;
import com.example.Project.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post"," postId ",postId)
        );
        Comment comment =modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment comment1=commentRepo.save(comment);
        return modelMapper.map(comment1,CommentDto.class);

    }

    @Override
    public void deleteComment(int commentId){
        Comment comment= commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "CommentID", commentId)
        );
        commentRepo.delete(comment);
        System.out.println("Comment deleted");
        commentRepo.flush();

    }




}
