package com.example.Task_8_Profiles.repositories;

import com.example.Task_8_Profiles.entity.StudentDev;
import com.example.Task_8_Profiles.entity.StudentProd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDevRepo extends JpaRepository<StudentDev,Integer> {
}
