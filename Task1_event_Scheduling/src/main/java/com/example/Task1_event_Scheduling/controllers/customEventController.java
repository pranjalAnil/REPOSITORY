package com.example.Task1_event_Scheduling.controllers;

import com.example.Task1_event_Scheduling.entities.CustomEvent;
import com.example.Task1_event_Scheduling.services.CustomerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class customEventController {
    @Autowired
    CustomerEventService customerEventService;

    @PostMapping("/add")
    public ResponseEntity<CustomEvent> addCustomEvent(@RequestBody CustomEvent customEvent){
        CustomEvent customEvent1= customerEventService.addEvent(customEvent);
        return new ResponseEntity<>(customEvent1, HttpStatus.CREATED);
    }

}
