import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by lenovo on 2017/3/13.
 */
public class HibernateTest2 {
    @Test
    public void saveAndUpdate(){
        Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        User user = new User();
        user.setName("XX");
        user.setPassWord("2233");

        session1.save(user);
        session1.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user.setName("YY");
        session2.update(user);

        session2.getTransaction().commit();

    }
    @Test
    public void saveOrUpdate(){
        Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        User user = new User();
        user.setName("XXX");
        user.setPassWord("2233");

        session1.saveOrUpdate(user);
        session1.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user.setName("YYY");
        session2.saveOrUpdate(user);

        session2.getTransaction().commit();

    }
    @Test
    public void merge(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = new User();
        user.setName("XXXX");
        user.setPassWord("2233");

        session.save(user);
        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user.setName("YYYY");
        session2.merge(user);

        session2.getTransaction().commit();

    }
    @Test
    public void clearAndFlush(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,3783);
        user.setName("YYYYYYYYY");
        session.flush();


       session.getTransaction().commit();

    }
}
