package com.example.Project.payloads;

import com.example.Project.entities.User;
import lombok.Data;

@Data
public class CommentDto {
    private int commentId;

    private String content;

    private UserDto user;

}
