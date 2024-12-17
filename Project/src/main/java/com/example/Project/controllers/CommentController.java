package com.example.Project.controllers;

import com.example.Project.exception.CommentDisabled;
import com.example.Project.exception.DeactivatedUser;
import com.example.Project.payloads.APIResponse;
import com.example.Project.payloads.CommentDto;
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
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId){
        try {
            CommentDto commentDto1=commentService.createComment(commentDto,postId);
            return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
        }catch (DeactivatedUser deactivatedUser) {
            APIResponse response = new APIResponse("Deactivated User", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        catch (CommentDisabled commentDisabled) {
            APIResponse response = new APIResponse("Comment disabled", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }

    }

    /**
     *
     * @param commentId -> to delete comment by using comment ID
     * @return
     */
    @DeleteMapping("/post/DeleteComments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        try {
            System.out.println("Deleting comment with : " + commentId);
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DeactivatedUser deactivatedUser){
            APIResponse response = new APIResponse("Deactivated User", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }
        catch (CommentDisabled commentDisabled) {
            APIResponse response = new APIResponse("Comment disabled", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }


    }

    /**
     *
     * @param commentDto -> content
     * @param commentId -> to find particular comment
     * @return commentDto with
     */
    @PutMapping("/post/updateComment/{commentId}")
    public  ResponseEntity<?> updateComment(@PathVariable int commentId,@RequestBody CommentDto commentDto){
        try {
            System.out.println("comment");
            commentService.updateComment(commentDto,commentId);
            return new ResponseEntity<>(commentDto,HttpStatus.OK);
        }catch (DeactivatedUser deactivatedUser){
            APIResponse response = new APIResponse("Deactivated User", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }catch (CommentDisabled commentDisabled){
            APIResponse response = new APIResponse("Comment disabled", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }


    }



}
