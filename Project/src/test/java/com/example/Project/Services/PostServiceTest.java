package com.example.Project.Services;

import com.example.Project.entities.Category;
import com.example.Project.entities.Post;
import com.example.Project.entities.User;
import com.example.Project.repositories.CategoryRepo;
import com.example.Project.repositories.PostRepo;
import com.example.Project.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostRepo postRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    UserRepo userRepo;

    public Date getCurrentDate(){
        return new Date();
    }


    @Test
    public void testCreatePost(){
        Post post=new Post();
        Category category=categoryRepo.findById(6).orElseThrow();
        User user =userRepo.findById(2).orElseThrow();
        post.setTitle("Wild Animals");
        post.setContent("Wild animals are decreasing day by day because of deforestation");
        post.setCategory(category);
        post.setUser(user);
        post.setAddDate(getCurrentDate());
        post.setImageName("default.png");
        postRepo.save(post);
        assertFalse(postRepo.findById(post.getPostId()).isEmpty());//--pass
//        assertTrue(postRepo.findById(post.getPostId()).isEmpty()); //--Fail

    }

    @Test
    public void testUpdatePost(){

        Post postTest =postRepo.findById(20).orElseThrow();
        Post post =postRepo.findById(20).orElseThrow();

        Category category=categoryRepo.findById(6).orElseThrow();
        User user =userRepo.findById(2).orElseThrow();

        post.setTitle("Wild Animals");
        post.setContent("Wild animals are decreasing day by day because of deforestation");
        post.setCategory(category);
        post.setUser(user);
        post.setAddDate(getCurrentDate());
        post.setImageName("default.png");

        postRepo.save(post);
        assertNotEquals(post,postTest);

    }

    @Test
    public void testDelete(){
        Post post =postRepo.findById(21).orElseThrow();
        postRepo.delete(post);
//        assertTrue(postRepo.findById(21).isEmpty()); //--pass
        assertTrue(postRepo.findById(20).isEmpty()); //--fail

    }

    @Test
    public void testGetAllPosts(){
        List<Post> posts=postRepo.findAll();
        assertFalse(posts.isEmpty());//--pass
//        assertTrue(posts.isEmpty()); //--fail

    }
    @Test
    public void testGetPostByUserId(){
         List<Post> posts= postRepo.findByUser(userRepo.findById(2).orElseThrow());
         assertFalse(posts.isEmpty());

    }

    @Test
    public void tetGetPostByCategory(){
        List<Post> posts=postRepo.findByCategory(categoryRepo.findById(6).orElseThrow());
        assertFalse(posts.isEmpty());
    }









}
