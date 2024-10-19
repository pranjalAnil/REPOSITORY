package com.example.Creating_and_managing_beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreatingAndManagingBeansApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CreatingAndManagingBeansApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Compiling");
		classCall().run();
	}
	@Bean
	public Class_call classCall(){
		return new Class_call();
	}
}
