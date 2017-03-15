import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/3/13.
 */
public class NativeSQLQueryTest {
    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        /*//查询的为对象时
        String sql = "select * from tt_account";
        SQLQuery query = session.createSQLQuery(sql).addEntity(User.class);

        List<User> list = query.list();
        for(User user : list){
            System.out.println(user.getName());
        }*/

        //查询结果为一部分，组成一个数组
        String sql = "select * from tt_account";
        SQLQuery query = session.createSQLQuery(sql);

        List<Object[]> userList = query.list();

        for(Object[] obj:userList){
            System.out.println(obj[0]+"~~~"+obj[1]+"~~~"+obj[2]);
        }


        session.getTransaction().commit();
    }
}
