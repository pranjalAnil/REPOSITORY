package com.example.Project.exception;

public class CommentDisabled extends RuntimeException {
    int postId;
    public CommentDisabled(int postId){
        super(String.format("comment section is disabled for post with postID"));
        this.postId=postId;
    }
}
