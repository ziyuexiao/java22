package com.kaishengit.mapper;

import com.kaishengit.pojo.Student;

import java.util.List;

/**
 * Created by lenovo on 2017/1/4.
 */
public interface StudentMapper {
    Student findByid(Integer id);
    List<Student> findByschoolid(Integer schoolId);/*一对多时常用*/
    List<Student> findAll();
}
