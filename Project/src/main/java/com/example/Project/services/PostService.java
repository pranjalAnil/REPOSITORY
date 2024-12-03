package com.example.Project.services;

import com.example.Project.payloads.PostDto;
import com.example.Project.payloads.PostResponse;

import java.util.List;

public interface PostService {
//    create
    PostDto createPost(PostDto postDto,int categoryId);

//    Update
    PostDto updatePost(PostDto postDto,int postId);

//    delete
    public String deleteMyPost(int postId);

//    getAllPost
    PostResponse getAllPost(int pageNumber, int pageSize);

//    get single post
    PostDto getPostById(int postId);

//    get all post by category
    List<PostDto> getPostByCategory(int categoryId);

//    get all post by user
    List<PostDto> getPostByUser(int userId);

    List<PostDto> getMyPosts();







}
