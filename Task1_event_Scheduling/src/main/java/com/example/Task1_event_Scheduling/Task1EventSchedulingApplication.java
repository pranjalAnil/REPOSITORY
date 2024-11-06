package com.example.Task1_event_Scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Task1EventSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task1EventSchedulingApplication.class, args);
	}

}
