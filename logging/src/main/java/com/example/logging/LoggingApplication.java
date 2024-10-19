package com.example.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class LoggingApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext= SpringApplication.run(LoggingApplication.class, args);
		applicationContext.getBean(Logs.class).run();
	}

}
