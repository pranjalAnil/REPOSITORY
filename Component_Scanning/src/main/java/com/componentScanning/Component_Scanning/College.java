package com.componentScanning.Component_Scanning;

import org.springframework.stereotype.Component;


@Component
public class College {
    private int college_ID;
    private String college_name;
    private Student student;
    private Teachers teachers;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public void setCollege_ID(int college_ID) {
        this.college_ID = college_ID;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    @Override
    public String toString() {
        return "College{" +
                "college_ID=" + college_ID +
                ", college_name='" + college_name + '\'' +
                ", \nstudent=" + student.toString() +
                ", \nteachers=" + teachers.toString() +
                '}';
    }
}
