package com.example.Task1_event_Scheduling.services;

import com.example.Task1_event_Scheduling.entities.CustomEvent;
import com.example.Task1_event_Scheduling.payloads.CustomEventDto;

import java.util.List;

public interface CustomEventService {
    public CustomEventDto addEvent(CustomEventDto customEventDto);
    public void archiveAndDeleteOldEvents();

}
