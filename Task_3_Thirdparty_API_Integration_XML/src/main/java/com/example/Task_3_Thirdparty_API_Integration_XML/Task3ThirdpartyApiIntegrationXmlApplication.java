package com.example.Task_3_Thirdparty_API_Integration_XML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task3ThirdpartyApiIntegrationXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task3ThirdpartyApiIntegrationXmlApplication.class, args);
	}


}
