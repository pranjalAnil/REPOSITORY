package com.example.Project.services.impl;
import com.example.Project.entities.Comment;
import com.example.Project.entities.Post;
import com.example.Project.entities.User;
import com.example.Project.exception.CommentDisabled;
import com.example.Project.exception.DeactivatedUser;
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
import java.util.List;

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
        if (user.isActivated()) {

            UserDto userDto = modelMapper.map(user, UserDto.class);
            Post post = postRepo.findById(postId).orElseThrow(
                    () -> new ResourceNotFoundException("Post", " postId ", postId)
            );
            if (!post.isCommentDisabled()) {
                if (post.getUser().isActivated()) {
                    commentDto.setUser(userDto);
                    Comment comment = modelMapper.map(commentDto, Comment.class);
                    comment.setPost(post);
                    Comment comment1 = commentRepo.save(comment);
                    return modelMapper.map(comment1, CommentDto.class);
                } else {
                    throw new DeactivatedUser(email);
                }
            }else {
                throw new CommentDisabled(postId);
            }
        }else {
            throw new DeactivatedUser(email);
        }

    }

    @Override
    public String deleteComment(int commentId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepo.findByEmail(email).orElseThrow(
                ()-> new  ResourceNotFoundException("user",email,0)
        );
        if (user.isActivated()) {


            List<Comment> comment = commentRepo.findByUser(user);
            Comment commentToDelete = comment.stream()
                    .filter(comment1 ->comment1.getCommentId () == commentId) // assuming Post has a getId() method
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment ID", commentId)
                    );
            if (commentToDelete.getUser().isActivated()) {
                commentRepo.delete(commentToDelete);
                commentRepo.flush();
                return "Comment Deleted";
            }else {
                throw new DeactivatedUser(commentToDelete.getUser().getEmail());
            }
        }else
        {
            throw new DeactivatedUser(email);
        }

    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, int commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepo.findByEmail(email).orElseThrow(
                ()-> new  ResourceNotFoundException("user",email,0)
        );

        if (user.isActivated()) {
            UserDto userDto = modelMapper.map(user, UserDto.class);

            List<Comment> commentList = commentRepo.findByUser(user);

            Comment comment =commentList.stream()
                    .filter(comment1 ->comment1.getCommentId () == commentId) // assuming Post has a getId() method
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment ID", commentId)
                    );
            if (comment.getUser().isActivated()) {

                comment.setContent(commentDto.getContent());
                comment.setUser(user);
                commentRepo.save(comment);

                BeanUtils.copyProperties(comment, commentDto);
                commentDto.setUser(userDto);

                return commentDto;
            }else {
                throw new DeactivatedUser(comment.getUser().getEmail());
            }
        }else {
            throw new DeactivatedUser(email);
        }
    }


}
