package com.example.Project.controllers;


import com.example.Project.config.AppConstant;

import com.example.Project.payloads.PostDto;
import com.example.Project.payloads.PostResponse;
import com.example.Project.services.FileService;
import com.example.Project.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    //    create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable int userId,
            @PathVariable int categoryId
    ) {
        PostDto createPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    //Get post using user ID
    @GetMapping("/user/{userId}/posts")
    public List<PostDto> getPostsByUser(@PathVariable int userId) {
        List<PostDto> postDtoList = postService.getPostByUser(userId);
        return postDtoList;
    }

    // Get post using post category
    @GetMapping("/category/{categoryId}/posts")
    public List<PostDto> getPostByCategory(@PathVariable int categoryId) {
        List<PostDto> postDtoList = postService.getPostByCategory(categoryId);
        return postDtoList;
    }

    @GetMapping("/post/{postId}")
    public PostDto getPostByPostID(@PathVariable int postId){
        PostDto postDto=postService.getPostById(postId);
        return postDto;
    }

    // Update the data using postID
    @PutMapping("/updatePosts/{postId}") // Make sure the mapping is correct
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId, @Valid @RequestBody PostDto postDto) {
        System.out.println("Updating post with ID: " + postId);
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/DeletePost/{postId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable int postId) {
        System.out.println("Deleting post with ID: " + postId);
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/getAllPosts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy

    ) {
        System.out.println("Getting all posts");
        PostResponse listPostDto = postService.getAllPost(pageNumber, pageSize, sortBy);
        System.out.println(listPostDto);
        System.out.println("Got all posts");
        return listPostDto;
    }

    //    Post Image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable int postId
    ) throws IOException {
        PostDto postDto = postService.getPostById(postId);

        // Check if postDto is null
        if (postDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if post not found
        }

        String fileName = fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}" , produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(
            @PathVariable("imageName")String imageName,
            HttpServletResponse response
    )throws IOException{
        InputStream resource=fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }





}
