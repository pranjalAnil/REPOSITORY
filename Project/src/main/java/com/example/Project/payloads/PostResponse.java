package com.example.Project.payloads;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto>content;
    private int pageSize;
    private int pageNumber;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;

}
