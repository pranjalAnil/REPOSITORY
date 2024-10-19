package com.componentScanning.Component_Scanning;

import org.springframework.stereotype.Service;

@Service
public class Teachers {
    private int teacher_ID;
    private String Teacher_name;

    public String getTeacher_name() {
        return Teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        Teacher_name = teacher_name;
    }

    public int getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(int teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "teacher_ID=" + teacher_ID +
                ", Teacher_name='" + Teacher_name + '\'' +
                '}';
    }
}
