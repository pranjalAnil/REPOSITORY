package com.example.Profiles_and_Configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Create_Bean implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        myClass().compile();
        System.out.println("run_______");

    }
    @Bean
    public MyClass myClass(){
        return new MyClass();
    }



}
