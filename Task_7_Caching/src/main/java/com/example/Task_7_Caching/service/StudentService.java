package com.example.Task_7_Caching.service;

import com.example.Task_7_Caching.entities.Student;
import com.example.Task_7_Caching.payloads.StudentDto;
import com.example.Task_7_Caching.repositories.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CacheManager cacheManager;  // Inject CacheManager


    public StudentDto add(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        studentRepo.save(student);
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    @Cacheable(value = "student", key = "#id")
    public StudentDto getById(int id) {
        log.info("Fetching student from database with ID: {}", id);
        Student student = studentRepo.findById(id).orElseThrow();
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    @CacheEvict(value = "student", key = "#id")
    public String deleteStud(int id){
        Student student=studentRepo.findById(id).orElseThrow();
        studentRepo.delete(student);
        if (cacheManager.getCache("student") != null && cacheManager.getCache("student").get(id) == null) {
            log.info("Cache entry for student ID {} successfully evicted.", id);
        } else {
            log.warn("Cache entry for student ID {} is not present in cache memory .", id);
        }
        return "Student Deleted";
    }

    @CachePut(value = "student", key = "#id")
    public StudentDto updateById(int id,StudentDto studentDto){
        Student student=studentRepo.findById(id).orElseThrow();
        student.setName(studentDto.getName());
        student.setCollege(studentDto.getCollege());
        studentRepo.save(student);
        BeanUtils.copyProperties(student,studentDto);
        Cache cache = cacheManager.getCache("student");
        if (cache != null && cache.get(id) != null) {
            log.info("Cache entry for student ID {} successfully updated with new data: {}", id, cache.get(id).get());
        } else {
            log.warn("Cache entry for student ID {} is not present in cache memory.", id);
        }

        return studentDto;
    }




}
