package com.example.Task1_event_Scheduling.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.sql.Timestamp;


@Entity
@Data
public class CustomEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp timestamp;
    private String customerId;
    private String event;
    private int status ;



}
