package com.example.Creating_and_managing_beans;

import org.springframework.stereotype.Component;

@Component
public class Emp_details {
    private int empId;
    private String emp_name;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
}
