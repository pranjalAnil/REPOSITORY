package com.example.Project.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

    private int categoryID;

    @NotEmpty(message = "Category title is required")
    private String categoryTitle;
    @NotNull(message = "Category description")
    private String categoryDescription;
}
