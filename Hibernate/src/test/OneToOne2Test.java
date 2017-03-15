import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.TopicContent;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by lenovo on 2017/3/14.
 */
public class OneToOne2Test {
    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Topic topic = new Topic();
        topic.setTitle("java学习");

        TopicContent topicContent = new TopicContent();
        topicContent.setContent("aaaa");

        topic.setTopicContent(topicContent);
        topicContent.setTopic(topic);

        session.save(topic);
        session.save(topicContent);



        session.getTransaction().commit();
    }

    @Test
    public void find(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        //有延时加载
        Topic topic = (Topic) session.get(Topic.class,1);

        System.out.println(topic.getTitle());
        System.out.println(topic.getTopicContent().getContent());


        session.getTransaction().commit();
    }
}
