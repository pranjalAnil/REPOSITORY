package com.example.Project.services.impl;

import com.example.Project.entities.Category;
import com.example.Project.entities.Post;
import com.example.Project.entities.User;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.PostDto;
import com.example.Project.payloads.PostResponse;
import com.example.Project.repositories.CategoryRepo;
import com.example.Project.repositories.PostRepo;
import com.example.Project.repositories.UserRepo;
import com.example.Project.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " User ID ", userId)
        );
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", " Category ID ", categoryId)
        );

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
//        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    //    @Override
//    public PostDto updatePost(PostDto postDto, int postId) {
//
//        Post post=postRepo.findById(postId).orElseThrow(
//                ()->new ResourceNotFoundException("Post"," PostId ",postId)
//        );
//        PostDto postDto1=modelMapper.map(post,PostDto.class);
//        post.setContent(postDto1.getContent());
//        post.setTitle(postDto1.getTitle());
//        post.setImageName("default.png");
//        post.setAddDate(new Date());
//        Post post1= postRepo.save(post);
//        return modelMapper.map(post1,PostDto.class);
//    }
    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", " PostId ", postId)
        );
        // Update fields directly from postDto
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", " PostID ", postId)
        );
        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Post> pageResult = postRepo.findAll(pageable);
        List<PostDto> postList =pageResult.getContent().stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postList);
        postResponse.setPageSize(pageResult.getSize());
        postResponse.setPageNumber(pageResult.getNumber());
        postResponse.setLastPage(pageResult.isLast());
        postResponse.setTotalElements(pageResult.getNumberOfElements());



        return postResponse;
    }

    @Override
    public PostDto getPostById(int postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
        return modelMapper.map(post, PostDto.class);
    }


    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", " category_id ", categoryId)
        );
        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " UserId ", userId)
        );
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return List.of();
    }


}
