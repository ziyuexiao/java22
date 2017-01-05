package com.kaishengit;

import com.kaishengit.mapper.SchoolMapper;
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
public class SchoolMapperTestCase {
    @Test
    public void findByid(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);
        School school = schoolMapper.findByid(1);
        System.out.println(school);
        /*//一对多时一般不采用此方法
        List<Student> studentList = school.getStudentList();
        for (Student student: studentList){
            System.out.println(student);
        }*/

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.findByschoolid(1);
        for (Student student:students){
            System.out.println(student);
        }

        sqlSession.close();
    }

    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);
        List<School> schoolList = schoolMapper.findAll();
        for (School school:schoolList){
            System.out.println(school);
            List<Student> studentList = school.getStudentList();
            for (Student student:studentList){
                System.out.println(student);
            }
        }
        sqlSession.close();
    }
}
