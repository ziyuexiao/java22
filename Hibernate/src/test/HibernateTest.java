import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;


/**
 * Created by lenovo on 2017/3/13.
 */
public class HibernateTest {
    @Test
    public void save(){
        //1,创建SessionFactory
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory =
                configuration.buildSessionFactory(serviceRegistry);

        //2,创建session
        Session session = sessionFactory.getCurrentSession();
        //3,事务
        Transaction transaction = session.getTransaction();
        transaction.begin();

       /* User user = new User();
        user.setName("hibernate");
        user.setPassWord("1122");

        session.persist(user);*///persist方法成功执行后，不会返回持久化对象的ID
        /*Integer id = (Integer) session.save(user);//sava方法成功执行后，返回持久化对象的ID
        System.out.println(id);*/



        Task task = new Task();
        task.setTitle("ZZZ");

        session.save(task);


        //4,结束
        transaction.commit();
        //session.close();//commit()后session会自动close

    }
    @Test
    public void findById(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,3754);
        System.out.println(user);

         /* User user = (User) session.get(User.class,3751);
        System.out.println(user);*/

        //System.out.println(session.contains(user));
        //session.clear();
        //session.evict(user);

        //清除二级缓存
        //Cache cache = HibernateUtil.getSessionFactory().getCache();
        //cache.evictAllRegions();
        //cache.evictEntityRegion(User.class);

       /* Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        user = (User) session1.get(User.class,92);
        System.out.println(user);

        session1.getTransaction().commit();*/


        session.getTransaction().commit();
    }
    @Test
    public void update(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,3754);

        user.setName("haha");

        session.getTransaction().commit();
    }
    @Test
    public void delete(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,3754);
        session.delete(user);

        session.getTransaction().commit();
    }
    @Test
    public void findByid(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.load(User.class,3754);
        System.out.println(user==null);//false

        session.getTransaction().commit();
    }
    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "from User where passWord = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,"1122");

        List<User> userList = query.list();
        for (User user:userList){
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
}
