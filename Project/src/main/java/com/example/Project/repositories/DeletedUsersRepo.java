package com.example.Project.repositories;

import com.example.Project.entities.DeletedUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface DeletedUsersRepo extends JpaRepository<DeletedUsers,Integer> {
    List<DeletedUsers> findByTimestampBefore(Timestamp cutoffTimestamp);
}
