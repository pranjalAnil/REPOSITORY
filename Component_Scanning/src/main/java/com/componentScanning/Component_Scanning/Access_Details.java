package com.componentScanning.Component_Scanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Access_Details {
    @Autowired
    College college;
    @Autowired
    Student student;
    @Autowired
    Teachers teachers;

    public void setCollege(College college) {
        this.college = college;
    }

    public  void Compile(){

        student.setName("Pranjal");
        student.setStd_ID(20258);

        teachers.setTeacher_name("Asha");
        teachers.setTeacher_ID(9);


        college.setCollege_name("KBTCOE");
        college.setCollege_ID(4029);
        college.setStudent(student);
        college.setTeachers(teachers);

        setCollege(college);

        String str=college.toString();
        System.out.println(str);



    }


}
