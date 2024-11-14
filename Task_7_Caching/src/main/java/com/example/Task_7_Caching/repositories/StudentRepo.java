    package com.example.Task_7_Caching.repositories;
    
    import com.example.Task_7_Caching.entities.Student;
    import org.springframework.data.jpa.repository.JpaRepository;
    
    public interface StudentRepo extends JpaRepository<Student,Integer> {
    
    }
