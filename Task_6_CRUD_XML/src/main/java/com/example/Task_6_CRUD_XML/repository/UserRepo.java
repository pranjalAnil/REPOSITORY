package com.example.Task_6_CRUD_XML.repository;

import com.example.Task_6_CRUD_XML.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
