package com.example.Project.payloads;

import com.example.Project.entities.Category;
import com.example.Project.entities.Comment;
import com.example.Project.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
public class PostDto {

    private int postId;

    @NotEmpty
    @Size(min = 10,max = 100)
    private String title;

    @NotEmpty
    @Size(min=20,max = 200)
    private String content;


    private String imageName;

    private LocalDateTime addedDate;

    private CategoryDto category;

    private UserDto user;

    private List<CommentDto> comments =new ArrayList<>();


}
