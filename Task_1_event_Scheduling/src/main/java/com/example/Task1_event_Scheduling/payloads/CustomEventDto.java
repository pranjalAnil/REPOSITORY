package com.example.Task1_event_Scheduling.payloads;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomEventDto {
    private int id;

    private Timestamp timestamp;
    @NotEmpty
    @Size(max = 10,min = 10,message = "customerId size must be 10")
    private String customerId;
    @NotEmpty(message = "please provide valid event")
    private String event;
    private int status ;
}
