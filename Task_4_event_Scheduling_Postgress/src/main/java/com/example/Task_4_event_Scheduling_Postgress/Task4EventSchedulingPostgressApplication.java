package com.example.Task_4_event_Scheduling_Postgress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Task4EventSchedulingPostgressApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task4EventSchedulingPostgressApplication.class, args);
	}

}
