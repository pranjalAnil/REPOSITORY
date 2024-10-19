package com.example.demo;

public class Students {
    private int roll;
    private String name;
    private int marks;

    public Students(int roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    public int getRoll() {
        return roll;
    }


    public String getName() {
        return name;
    }


    public int getMarks() {
        return marks;
    }

    public void displayDetails(){
        System.out.println("Name: "+getName());
        System.out.println("Roll_number: "+getRoll());
        System.out.println("Marks: "+getMarks());
    }
}
