package com.example.Project.controllers;

import com.example.Project.payloads.CommentDto;
import com.example.Project.payloads.PostDto;
import com.example.Project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     *
     * @param commentDto -> content
     * @param postId -> to comment on particular post
     * @return commentDto with
     */
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId){
        CommentDto commentDto1=commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
    }

    /**
     *
     * @param commentId -> to delete comment by using comment ID
     * @return
     */
    @DeleteMapping("/post/DeleteComments/{commentId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable int commentId) {
        System.out.println("Deleting comment with : " + commentId);
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
