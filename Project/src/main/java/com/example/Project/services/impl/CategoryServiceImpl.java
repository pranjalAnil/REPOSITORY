package com.example.Project.services.impl;
import com.example.Project.entities.Category;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.CategoryDto;
import com.example.Project.repositories.CategoryRepo;
import com.example.Project.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=categoryDtoTOCategory(categoryDto);
        Category save=categoryRepo.save(category);
        return categoryToCategoryDto(save);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User"," ID ",id)
                );
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category update= categoryRepo.save(category);

        return categoryToCategoryDto(update);
    }

    @Override
    public void deleteCategory(int id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User"," ID ", id)
                );
        categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategoryByID(int id) {
        Category category=categoryRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User"," ID ",id)
        );

        return categoryToCategoryDto(category);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList =categoryRepo.findAll();
        return categoryList.stream().map(this::categoryToCategoryDto).collect(Collectors.toList());

    }


    private CategoryDto categoryToCategoryDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }

    private Category categoryDtoTOCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto ,Category.class);
    };
}
