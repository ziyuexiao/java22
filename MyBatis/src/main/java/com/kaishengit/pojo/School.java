package com.kaishengit.pojo;

import java.util.List;

/**
 * Created by lenovo on 2017/1/4.
 */
public class School {
    private Integer id;
    private String schoolname;
    private List<Student> studentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolname='" + schoolname + '\'' +
                '}';
    }
}
