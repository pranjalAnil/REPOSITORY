package com.example.Task_2_Third_party_API_Integration_Using_JSON;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Task2ThirdPartyApiIntegrationUsingJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task2ThirdPartyApiIntegrationUsingJsonApplication.class, args);

	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}


}
