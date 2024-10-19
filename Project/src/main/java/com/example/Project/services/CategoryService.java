package com.example.Project.services;

import com.example.Project.payloads.CategoryDto;

import java.util.List;


public interface CategoryService {
//    Create
    CategoryDto createCategory(CategoryDto categoryDto);

//    Update
    CategoryDto updateCategory(CategoryDto categoryDto,int id);


//    Delete
    public void deleteCategory(int id);

//    get
    public CategoryDto getCategoryByID(int id);

//    getAll
    public List<CategoryDto> getAllCategory();




    }
