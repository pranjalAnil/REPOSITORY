package com.example.Autowire_Dependency_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Dev {

//    @Autowired  //filled injection
    private Computer computer;
    Dev (Laptop laptop){
        computer=laptop;
    }

//    @Autowired
//    private Computer computer;
//    public void setComputer(Computer computer) {
//        this.computer = computer;
//    }

    public void build(){
        computer.compile();
        System.out.println("Hello Developer");
    }
}
