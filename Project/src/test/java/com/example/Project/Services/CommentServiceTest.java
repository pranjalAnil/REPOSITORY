package com.example.Project.Services;

import com.example.Project.entities.Comment;
import com.example.Project.repositories.CommentRepo;
import com.example.Project.repositories.PostRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Test
    public void testCreateComment(){
        Comment comment=new Comment();
        comment.setPost(postRepo.findById(15).orElseThrow());
        comment.setContent("Nice post");
        commentRepo.save(comment);
        assertFalse(commentRepo.findById(comment.getCommentId()).isEmpty());

    }
    @Test
    public void testDeleteComment(){
        commentRepo.delete(commentRepo.findById(15).orElseThrow());
        assertTrue(commentRepo.findById(15).isEmpty());
    }
}
