package com.kaishengit;

import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.pojo.School;
import com.kaishengit.pojo.Student;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/1/4.
 */
public class StudentMapperTestCase {
    @Test
    public void findByid(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findByid(1);
        System.out.println(student);
        School school = student.getSchool();
        System.out.println(school);

        sqlSession.close();
    }
    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = studentMapper.findAll();
        for (Student student:studentList){
            System.out.println(student);
            School school = student.getSchool();
            System.out.println(school);
            System.out.println("--------------");
        }
    }
}
