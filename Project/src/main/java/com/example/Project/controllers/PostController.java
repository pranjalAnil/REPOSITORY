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

    /**
     * @param postDto ->
     * @param categoryId ->title, content
     * @return createPost, HttpStatus. CREATED
     */
    @PostMapping("/user/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable int categoryId
    ) {
        PostDto createPost = postService.createPost(postDto, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    //Get post using user ID

    /**
     * @param userId-> get all posts of particular user with userID
     * @return list postDto
     */
    @GetMapping("/user/{userId}/posts")
    public List<PostDto> getPostsByUser(@PathVariable int userId) {
        return postService.getPostByUser(userId);
    }


    // Get post using post category

    /**
     * @param categoryId -> get all posts of particular category with userID
     * @return List<PostDto>
     */
    @GetMapping("/category/{categoryId}/posts")
    public List<PostDto> getPostByCategory(@PathVariable int categoryId) {
        return postService.getPostByCategory(categoryId);
    }

    /**
     * @param postId -> get post oby postId
     * @return PostDto
     */
    @GetMapping("/post/{postId}")
    public PostDto getPostByPostID(@PathVariable int postId) {
        return postService.getPostById(postId);
    }

    // Update the data using postID

    /**
     * @param postId -> find post by using post ID
     * @param postDto-> title , content (details to update post)
     * @return PostDto
     */
    @PutMapping("/updatePosts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId, @Valid @RequestBody PostDto postDto) {
        System.out.println("Updating post with ID: " + postId);
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    /**
     * @param postId-> delete post by post id
     * @return response 204 No_CONTENT
     */
    @DeleteMapping("/DeletePost/{postId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable int postId) {
        System.out.println("Deleting post with ID: " + postId);
        postService.deleteMyPost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    /**
     * That's how we can add filter
     * @param pageNumber -> start with 0
     * @param pageSize ->  to divide one page in to multiple
     * @return PostResponse pageSize,,pageNumber, totalElements, totalPages lastPage
     */
    @GetMapping("/getAllPosts")
    public PostResponse getAllPosts(
//            ?pageNumber=0&pageSize=4
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize

    ) {
        System.out.println("Getting all posts");
        PostResponse listPostDto = postService.getAllPost(pageNumber, pageSize);
        System.out.println(listPostDto);
        System.out.println("Got all posts");
        return listPostDto;
    }

//    Get User own posts

    /**
     * @return List<PostDto> --(post of user who logged in)
     */
    @GetMapping("/myPosts")
    public ResponseEntity<List<PostDto>> getMyPosts() {
        return new ResponseEntity<>(postService.getMyPosts(), HttpStatus.OK);
    }

    //    Post Image upload

    /**
     * @param image -> add image in .png
     * @param postId -> add image to particular post
     * @return postDto with updated image name
     * @throws IOException -> throws if not able to get or upload image
     */
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

    /**
     * @param imageName -> to get image
     * @throws IOException -> throws if image not found
     */
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }


}
