package com.example.Project.services.impl;
import com.example.Project.entities.Category;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.CategoryDto;
import com.example.Project.repositories.CategoryRepo;
import com.example.Project.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

    /**
     * create category
     * @param categoryDto
     * @return CategoryDto
     */
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
//        Category category=new Category();
//        BeanUtils.copyProperties(categoryDto,category);
        Category category=modelMapper.map(categoryDto,Category.class);
        Category save=categoryRepo.save(category);
        return modelMapper.map(save,CategoryDto.class);
    }

    /**
     *
     * @param categoryDto
     * @param id
     * @return CategoryDto
     */
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User"," ID ",id)
                );
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category update= categoryRepo.save(category);

        return modelMapper.map(update,CategoryDto.class);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteCategory(int id) {
        Category category=categoryRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User"," ID ", id)
                );
        categoryRepo.delete(category);

    }

    /**
     *
     * @param id
     * @return category
     */
    @Override
    public CategoryDto getCategoryByID(int id) {
        Category category=categoryRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User"," ID ",id)
        );

        return modelMapper.map(category,CategoryDto.class);

    }

    /**
     *
     * @return categoryList
     */
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList =categoryRepo.findAll();
        return categoryList.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());

    }


    /**
     *
     * @param category
     * @return
     */
    private CategoryDto categoryToCategoryDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }

    private Category categoryDtoTOCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto ,Category.class);
    }
}
