package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/14.
 */
@Entity
@Table(name = "t_student")
public class XueSheng {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(name = "t_student_teacher",
            joinColumns = @JoinColumn(name = "studentid"),
            inverseJoinColumns = @JoinColumn(name = "teacherid"))
    private Set<Teacher> teacherSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }
}
