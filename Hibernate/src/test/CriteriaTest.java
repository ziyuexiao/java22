import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/3/13.
 */
public class CriteriaTest {
    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();
        for (User user:userList){
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void where(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);

        /*criteria.add(Restrictions.eq("name","YYY"));
        criteria.add(Restrictions.eq("passWord","2233"));*/

        /*Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("name","YYY"));
        disjunction.add(Restrictions.eq("name","tom"));
        criteria.add(disjunction);*/


        /*criteria.add(Restrictions.or
                        (Restrictions.eq("name","YYY"),
                        Restrictions.eq("name","tom")));*/

        /*criteria.addOrder(Order.desc("id"));*/

        /*criteria.setFirstResult(0);
        criteria.setMaxResults(3);*/

        criteria.add(Restrictions.like("name","Y", MatchMode.ANYWHERE));


        List<User> userList = criteria.list();
        for (User user:userList){
            System.out.println(user);
        }

        session.getTransaction().commit();

    }

    @Test
    public void count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);

        Object[] obj = (Object[]) criteria.uniqueResult();


        System.out.println(obj[0] + "~~~~~~~~" + obj[1]);


        session.getTransaction().commit();
    }
}
