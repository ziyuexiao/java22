package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/14.
 */
@Entity
@Table(name = "t_teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "teacherSet")
    private Set<XueSheng> xueShengSet;

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

    public Set<XueSheng> getXueShengSet() {
        return xueShengSet;
    }

    public void setXueShengSet(Set<XueSheng> xueShengSet) {
        this.xueShengSet = xueShengSet;
    }
}
