import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by lenovo on 2017/3/3.
 */
public class RedisTest {
    @Test
    public void stringSet(){
        Jedis jedis = new Jedis("123.56.135.120",6379);
        jedis.set("name","kaishengit");
        jedis.close();
    }
}
