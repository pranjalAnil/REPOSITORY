package com.example.Creating_and_managing_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Class_call {
    @Autowired
    Company company;
    public void setCompany(Company company) {
        this.company = company;
    }
    @Autowired
    Emp_details empDetails;
//
    public void run(){
        company.setCompany_id(20);
        company.setCompany_name("Winjit");
        empDetails.setEmp_name("Pranjal");
        empDetails.setEmpId(20258);

        company.setEmp(empDetails);
        System.out.println("Compiling_____________________________");

        System.out.println(company.toString());






        }

}
