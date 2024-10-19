package com.example.Creating_and_managing_beans;


import org.springframework.stereotype.Repository;
@Repository
public class Company {
    private int company_id;
    private String company_name;
    private Emp_details emp;

    public void setEmp(Emp_details emp) {
        this.emp = emp;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }


    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return
                "company_id= " + company_id +
                ", company_name= " + company_name +
                ", \nemp:_____\nname: " + emp.getEmp_name() +
                    "\nID: "+emp.getEmpId();
    }
}
