package com.example.Project.repositories;

import com.example.Project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    //    User findByEmail(String email); // Change this to findByEmail
}
