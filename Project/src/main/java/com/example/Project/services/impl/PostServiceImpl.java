package com.example.Project.services.impl;
import com.example.Project.entities.Category;
import com.example.Project.entities.Post;
import com.example.Project.entities.User;
import com.example.Project.exception.DeactivatedUser;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PostDto createPost(PostDto postDto, int categoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", " email "+email, 0));

        if(user.isActivated()) {
            Category category = categoryRepo.findById(categoryId).orElseThrow(
                    () -> new ResourceNotFoundException("Category", " Category ID ", categoryId)
            );
            Post post = modelMapper.map(postDto, Post.class);
            post.setCommentDisabled(post.isCommentDisabled());
            post.setImageName("default.png");
            Date date = new Date();
            post.setAddDate(date);
            post.setUser(user);
            post.setCategory(category);
            Post newPost = postRepo.save(post);
            return modelMapper.map(newPost, PostDto.class);
        }
        else {
            throw new  DeactivatedUser(user.getEmail());
        }
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("User","Email "+email,0)
        );
        if(user.isActivated()) {
            List<Post> posts = postRepo.findByUser(user);
            Post postToUpdate = posts.stream()
                    .filter(post -> post.getPostId() == postId) // assuming Post has a getId() method
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId)
                    );
            Date date = new Date();
            postToUpdate.setAddDate(date);
            postToUpdate.setTitle(postDto.getTitle());
            postToUpdate.setContent(postDto.getContent());
            postToUpdate.setImageName(postDto.getImageName());
            Post updatedPost = postRepo.save(postToUpdate);
            return modelMapper.map(updatedPost, PostDto.class);
        }else {
            throw new DeactivatedUser(user.getEmail());
        }
    }

    @Override
    public String deleteMyPost(int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("User","Email "+email,0)
        );
        if (user.isActivated()) {
            List<Post> posts = postRepo.findByUser(user);

            Post postToDelete = posts.stream()
                    .filter(post -> post.getPostId() == postId) // assuming Post has a getId() method
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId)
                    );
            postRepo.delete(postToDelete);
            return "User deleted";
        }else {
             throw new  DeactivatedUser(email);
        }

    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow();
        if(user.isActivated()) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Post> pageResult = postRepo.findAll(pageable);


            List<PostDto> postList = new ArrayList<>();
            for (Post post : pageResult) {
                if (post.getUser().isActivated()) {
                    postList.add(modelMapper.map(post, PostDto.class));
                }

            }

            PostResponse postResponse = new PostResponse();
            postResponse.setContent(postList);
            postResponse.setPageSize(pageResult.getSize());
            postResponse.setPageNumber(pageResult.getNumber());
            postResponse.setLastPage(pageResult.isLast());
            postResponse.setTotalElements(pageResult.getNumberOfElements());


            return postResponse;
        }else {
            throw new DeactivatedUser(email);
        }
    }

    @Override
    public PostDto getPostById(int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow();
        if (user.isActivated()) {
            Post post = postRepo.findById(postId)
                    .orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
            if (post.getUser().isActivated()) {
                return modelMapper.map(post, PostDto.class);

            } else {
                throw new DeactivatedUser(email);
            }
        }else {
            throw new DeactivatedUser(email);
        }

    }


    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow();
        if (user.isActivated()) {
            Category category = categoryRepo.findById(categoryId).orElseThrow(
                    () -> new ResourceNotFoundException("Category", " category_id ", categoryId)
            );
            List<Post> posts = postRepo.findByCategory(category);
            return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        }else {
            throw new DeactivatedUser(email);
        }
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow();
        if (user.isActivated()) {
            User user1 = userRepo.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User", " UserId ", userId)
            );
            if (user1.isActivated()) {
                List<Post> posts = postRepo.findByUser(user1);
                return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

            }
            List<Post> posts = postRepo.findByUser(user1);
            return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        }
        else {
            throw new DeactivatedUser(email);
        }
    }

    @Override
    public List<PostDto> getMyPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user=userRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("User","Email "+email,0)
        );
        if (user.isActivated()) {
            List<Post> posts = postRepo.findByUser(user);

            return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        }
        else {
            throw new DeactivatedUser(email);
        }
    }






}
