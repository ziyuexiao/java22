package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/14.
 */
@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String schoolname;
    @OneToMany(mappedBy = "school")//代表该对象不维护关系
    @OrderBy("id desc")
    private Set<Student> studentSet;

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

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
}
