package com.example.Task_9_Swagger.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(description = "User data model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "User's unique identifier", example = "12345")
    private int id;
    @Schema(description = "User's usename name", example = "John")
    private String userName;
    @Schema(description = "User's about", example = "Winjit")
    private String about;

}
