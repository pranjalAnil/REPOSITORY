package com.example.Project.Services;

import com.example.Project.entities.Category;
import com.example.Project.repositories.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryTest {

    @Autowired
    CategoryRepo categoryRepo;

    @Test
    public void testCreateCategory(){
        Category category=new Category();
        category.setCategoryTitle("New");
        category.setCategoryDescription("This new Category");
        categoryRepo.save(category);
        assertFalse(categoryRepo.findById(category.getCategoryId()).isEmpty());

    }
    @Test
    public void testUpdateCategory(){
        Category categoryTest=categoryRepo.findById(17).orElseThrow();
        Category category=categoryRepo.findById(17).orElseThrow();
        category.setCategoryTitle("To delete ");
        category.setCategoryDescription("This new Category");
        categoryRepo.save(category);
        assertNotEquals(categoryTest,category);



    }
    @Test
    public void testGetCategoryById(){
        assertFalse(categoryRepo.findById(17).isEmpty());


    }
    @Test
    public void testGetAllCategory(){
        assertFalse(categoryRepo.findAll().isEmpty());

    }
    @Test
    public void testDeleteCategory(){
        categoryRepo.delete(categoryRepo.findById(17).orElseThrow());
        assertTrue(categoryRepo.findById(17).isEmpty());

    }

}
