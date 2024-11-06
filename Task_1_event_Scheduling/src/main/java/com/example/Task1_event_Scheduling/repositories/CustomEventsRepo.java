package com.example.Task1_event_Scheduling.repositories;

import com.example.Task1_event_Scheduling.entities.CustomEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Timestamp;
import java.util.List;

public interface CustomEventsRepo extends JpaRepository<CustomEvent,Integer> {
    List<CustomEvent> findByTimestampBefore(Timestamp cutoffTimestamp);
}
