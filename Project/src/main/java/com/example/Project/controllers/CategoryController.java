package com.example.Project.controllers;


import com.example.Project.payloads.CategoryDto;
import com.example.Project.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userController")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/addData")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1= categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }
    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategory(){
        List<CategoryDto> list=categoryService.getAllCategory();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryByID(@PathVariable int id){
        return  ResponseEntity.ok(categoryService.getCategoryByID(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable int id,@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updateCategory=categoryService.updateCategory(categoryDto,id);
        return ResponseEntity.ok(updateCategory);

    }


    @DeleteMapping("/deleteCategoryById/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
