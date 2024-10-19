package com.componentScanning.Component_Scanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ComponentScanningApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(ComponentScanningApplication.class, args);
		applicationContext.getBean(Access_Details.class).Compile();

	}

}
