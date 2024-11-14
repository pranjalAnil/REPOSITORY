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
    /**
     *
     * @param categoryDto ->categoryTitle, categoryDescription
     * @return created category
     */
    @PostMapping("/addData")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1= categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }
    /**
     *
     * @return -all categorise
     */
    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> list=categoryService.getAllCategory();
        return list;
    }
    /**
     *
     * @param id -> to get particular category by its id
     * @return Category with categoryId id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryByID(@PathVariable int id){
        return  ResponseEntity.ok(categoryService.getCategoryByID(id));
    }
    /**
     *
     * @param id -> to update particular category
     * @param categoryDto -> categoryTitle, categoryDescription
     * @return updated category
     */
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable int id,@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updateCategory=categoryService.updateCategory(categoryDto,id);
        return ResponseEntity.ok(updateCategory);

    }
    /**
     *
     * @param id -> to delete category by its id (only admin can delete category)
     * @return response 204 or 404
     */
    @DeleteMapping("/deleteCategoryById/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
