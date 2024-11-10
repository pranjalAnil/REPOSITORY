package com.example.Task_4_event_Scheduling_Postgress.controller;

import com.example.Task_4_event_Scheduling_Postgress.entity.CustomEvent;
import com.example.Task_4_event_Scheduling_Postgress.payloads.CustomEventDto;
import com.example.Task_4_event_Scheduling_Postgress.services.CustomEventService;
import jakarta.validation.Valid;
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
    CustomEventService customerEventService;

    @PostMapping("/add")
    public ResponseEntity<CustomEventDto> addCustomEvent(@Valid @RequestBody CustomEventDto customEventDto){
        CustomEventDto customEvent1= customerEventService.addEvent(customEventDto);
        return new ResponseEntity<>(customEvent1, HttpStatus.CREATED);
    }
}
