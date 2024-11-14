package com.example.Project.services.impl;

import com.example.Project.entities.Comment;
import com.example.Project.entities.Post;
import com.example.Project.entities.User;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.CommentDto;
import com.example.Project.payloads.UserDto;
import com.example.Project.repositories.CommentRepo;
import com.example.Project.repositories.PostRepo;
import com.example.Project.repositories.UserRepo;
import com.example.Project.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email).orElseThrow(
                ()-> new  ResourceNotFoundException("user",email,0)
        );
        UserDto userDto=modelMapper.map(user,UserDto.class);
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post"," postId ",postId)
        );
        commentDto.setUser(userDto);
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

    @Override
    public CommentDto updateComment(CommentDto commentDto, int commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepo.findByEmail(email).orElseThrow(
                ()-> new  ResourceNotFoundException("user",email,0)
        );

        UserDto userDto =modelMapper.map(user,UserDto.class);

        Comment comment =commentRepo.findByUser(user).orElseThrow(
                ()->new ResourceNotFoundException("Comment","commentId",commentId)
        );
        comment.setContent(commentDto.getContent());
        comment.setUser(user);
        commentRepo.save(comment);

        BeanUtils.copyProperties(comment,commentDto);
        commentDto.setUser(userDto);

        return commentDto;
    }


}
