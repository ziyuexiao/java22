import com.kaishengit.pojo.School;
import com.kaishengit.pojo.Student;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/14.
 */
public class OneToManyTest {
    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        School school = new School();
        school.setSchoolname("汽车系");


        Student student1 = new Student();
        student1.setStudentname("杨");
        student1.setSchool(school);

        Student student2 = new Student();
        student2.setStudentname("姚");
        student2.setSchool(school);


        //看院系下的学生,体现双向关系，如果没有放弃一方关系维护，该情况下多出两条update语句
        Set<Student> students = new HashSet<>();
        students.add(student1);
        students.add(student2);

        school.setStudentSet(students);


        //1.先存一，再存多
        //2.让一端放弃关系维护（一），此时不会出现update语句
        session.save(school);
        session.save(student1);
        session.save(student2);



        session.getTransaction().commit();
    }

    @Test
    public void fondOneTOMany(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        School school = (School) session.get(School.class,4);
        System.out.println(school.getSchoolname());

        //懒加载，需要的时候就会去查
        Set<Student> students = school.getStudentSet();//也会出现一条查询sql
        for (Student student:students){
            System.out.println(student.getStudentname());
        }


        session.getTransaction().commit();
    }
    @Test
    public void findManyToOne(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        /*Student student = (Student) session.get(Student.class,1);
        System.out.println(student.getStudentname());

        //懒加载，此时也会出现一条sql
        System.out.println(student.getSchool().getSchoolname());
*/
        //N+1问题，当加上fetch="join"后只会执行一条左外查询的sql
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> studentList = criteria.list();
        for (Student student:studentList){
            System.out.println(student.getStudentname()+"----"+student.getSchool().getSchoolname());
        }


        session.getTransaction().commit();
    }
    @Test
    public void  cascadedelete(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        School school = (School) session.get(School.class,3);
        session.delete(school);


        session.getTransaction().commit();
    }
}
