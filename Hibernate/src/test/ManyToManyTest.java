
import com.kaishengit.pojo.Teacher;
import com.kaishengit.pojo.XueSheng;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/14.
 */
public class ManyToManyTest {
    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        XueSheng s1 = new XueSheng();
        s1.setName("s1");

        XueSheng s2 = new XueSheng();
        s2.setName("s2");

        Teacher t1 = new Teacher();
        t1.setName("T1");

        Teacher t2 = new Teacher();
        t2.setName("T2");

        Set<Teacher> teacherSet = new HashSet<>();
        teacherSet.add(t1);
        teacherSet.add(t2);

        s1.setTeacherSet(teacherSet);
        s2.setTeacherSet(teacherSet);


        Set<XueSheng> xueShengSet = new HashSet<>();
        xueShengSet.add(s1);
        xueShengSet.add(s2);

        t1.setXueShengSet(xueShengSet);
        t2.setXueShengSet(xueShengSet);


        session.save(s1);
        session.save(s2);
        session.save(t1);
        session.save(t2);

        session.getTransaction().commit();
    }
    @Test
    public void find() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

       XueSheng xueSheng = (XueSheng) session.get(XueSheng.class,7);
        System.out.println(xueSheng.getName());

        //1.在映射文件中奖lazy属性配置为false
        //2.Hibernate.initialize(student.getTeacherSet());
        //3.student.getTeacherSet().size();
        //4.OpenSessionInView Filter(最好的做法)

        Set<Teacher> teacherSet = xueSheng.getTeacherSet();
        for(Teacher teacher : teacherSet) {
            System.out.println(teacher.getName());
        }


        session.getTransaction().commit();


    }

}
