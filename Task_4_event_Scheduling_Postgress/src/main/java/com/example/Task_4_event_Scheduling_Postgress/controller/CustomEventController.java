package com.example.Task_4_event_Scheduling_Postgress.controller;

import com.example.Task_4_event_Scheduling_Postgress.entity.CustomEvent;
import com.example.Task_4_event_Scheduling_Postgress.services.CustomerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class CustomEventController {
    @Autowired
    CustomerEventService customerEventService;

    @PostMapping("/add")
    public ResponseEntity<CustomEvent> addCustomEvent(@RequestBody CustomEvent customEvent){
        CustomEvent customEvent1= customerEventService.addEvent(customEvent);
        return new ResponseEntity<>(customEvent1, HttpStatus.CREATED);
    }
}
