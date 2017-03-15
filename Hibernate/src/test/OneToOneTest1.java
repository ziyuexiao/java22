import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by lenovo on 2017/3/14.
 */
public class OneToOneTest1 {
    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Person person = new Person();
        person.setName("Jack");

        Card card = new Card();
        card.setCardname("X001");
        card.setPerson(person);

        //自动会先存person，再存card
        session.save(card);
        session.save(person);

        session.getTransaction().commit();
    }
    @Test
    public void find(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        //该种情况下的一对一不会出现懒加载，讲究的是范式
        /*Person person = (Person) session.get(Person.class,2);
        System.out.println(person.getName());*/

        Card card = (Card) session.get(Card.class,2);
        System.out.println(card.getCardname());


        session.getTransaction().commit();

    }
    @Test
    public void delete(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Person person = (Person) session.get(Person.class,1);
        session.delete(person);

        session.getTransaction().commit();
    }

}
