package com.example.Task1_event_Scheduling.controllers;
import com.example.Task1_event_Scheduling.payloads.CustomEventDto;
import com.example.Task1_event_Scheduling.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class customEventController {
    @Autowired
    CustomEventService customEventService;

    @PostMapping("/add")
    public ResponseEntity<CustomEventDto> addCustomEvent(@Valid @RequestBody  CustomEventDto customEventDto){
        CustomEventDto customEventDto1= customEventService.addEvent(customEventDto);
        return new ResponseEntity<>(customEventDto1, HttpStatus.CREATED);
    }

}
