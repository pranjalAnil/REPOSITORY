package com.componentScanning.Component_Scanning;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private int std_ID;
    private String name;


    public void setStd_ID(int std_ID) {
        this.std_ID = std_ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "std_ID=" + std_ID +
                ", name='" + name + '\'' +
                '}';
    }

}
