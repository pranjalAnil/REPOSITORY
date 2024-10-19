package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class MyClass{
    public void print_msg(String name)
    {
        System.out.println("___________________________");
        System.out.println("Hello "+name);
    }}
