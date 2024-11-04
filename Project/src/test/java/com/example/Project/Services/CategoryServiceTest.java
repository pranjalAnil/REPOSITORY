package com.example.Project.Services;

import com.example.Project.entities.Category;
import com.example.Project.payloads.CategoryDto;
import com.example.Project.repositories.CategoryRepo;
import com.example.Project.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    CategoryRepo categoryRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CategoryServiceImpl createCategory;

    private static CategoryDto categoryDto;


//    @BeforeAll
//    public static void init(){
//        categoryDto =new CategoryDto();
//        categoryDto.setCategoryID(1);
//
//    }

    @Test
    public void createCategory_test() {
        Category category = new Category();
//        category.setCategoryId(1);
        category.setCategoryTitle("New");
        category.setCategoryDescription("This new Category");

//        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
//        when(categoryRepo.save(any())).thenReturn(category);
//
//        CategoryDto response = createCategory.createCategory(categoryDto);
//        assertNotNull(response);
//        assertEquals(1, response.getCategoryID());
        assertNotNull(categoryRepo.findById(category.getCategoryId()));

    }


    @Test
    public void testUpdateCategory(){
//        Category categoryTest=categoryRepo.findById(17).orElseThrow();
        Category category=categoryRepo.findById(1).orElseThrow();
        category.setCategoryTitle("To delete ");
        category.setCategoryDescription("This new Category");
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(categoryRepo.save(any())).thenReturn(category);
        categoryRepo.save(category);

//        assertNotEquals(categoryTest,category);



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
