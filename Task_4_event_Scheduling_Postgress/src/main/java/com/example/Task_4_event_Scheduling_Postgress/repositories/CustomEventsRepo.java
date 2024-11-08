package com.example.Task_4_event_Scheduling_Postgress.repositories;

import com.example.Task_4_event_Scheduling_Postgress.entity.CustomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CustomEventsRepo extends JpaRepository<CustomEvent,Integer> {
    List<CustomEvent> findByTimestampBefore(Timestamp cutoffTimestamp);
}

