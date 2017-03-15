import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/3/13.
 */
public class HQLTest {
    @Test
    public void where(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

       /* String hql = "from User where name = ? and passWord = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,"hibernate");
        query.setParameter(1,"1122");*/


        String hql = "from User where name = :name and passWord = :password";
        Query query = session.createQuery(hql);
        query.setParameter("name","hibernate");
        query.setParameter("password","1122");


        /*query.setFirstResult(0);
        query.setMaxResults(2);*/

        List<User> userList = query.list();
        for (User user:userList){
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
    @Test
    public void queryProperty(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "select name , passWord from User";
        Query query = session.createQuery(hql);

        List<Object[]> arraylist = query.list();
        for (Object[] obj:arraylist){
            System.out.println(obj[0] + "->" + obj[1]);
        }


        session.getTransaction().commit();
    }
    @Test
    public void count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "select count(*) from User";
        Query query = session.createQuery(hql);
        Long count = (Long) query.uniqueResult();

        System.out.println(count);

        session.getTransaction().commit();
    }
}
