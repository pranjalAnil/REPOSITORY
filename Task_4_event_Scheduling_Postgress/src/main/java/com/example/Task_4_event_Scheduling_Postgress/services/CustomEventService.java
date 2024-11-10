package com.example.Task_4_event_Scheduling_Postgress.services;

import com.example.Task_4_event_Scheduling_Postgress.entity.CustomEvent;
import com.example.Task_4_event_Scheduling_Postgress.payloads.CustomEventDto;

public interface CustomEventService {
    public CustomEventDto addEvent(CustomEventDto customEvent);
}
