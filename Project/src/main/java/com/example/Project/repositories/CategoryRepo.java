package com.example.Project.repositories;

import com.example.Project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category,Integer> {


}
