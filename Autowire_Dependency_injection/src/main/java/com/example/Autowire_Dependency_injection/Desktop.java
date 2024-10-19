package com.example.Autowire_Dependency_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Desktop implements  Computer {

    @Override
    public void compile() {
        System.out.println("Compile faster");
    }
}
