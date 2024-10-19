package com.example.Autowire_Dependency_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class AutowireDependencyInjectionApplication  implements CommandLineRunner{

	public static void main(String[] args) {
//		ApplicationContext applicationContext= SpringApplication.run(AutowireDependencyInjectionApplication.class, args);
//		Dev dev =applicationContext.getBean(Dev.class);
//		dev.build();


//		SpringApplication.run(AutowireDependencyInjectionApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		dev().build();
	}
	@Bean
	public Dev dev(){
		return new Dev(new Laptop());
	}


}
