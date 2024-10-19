package com.example.Autowire_Dependency_injection;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer{
    @Override
    public void compile() {
        System.out.println("Compiling code");
    }
}
