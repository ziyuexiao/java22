package com.kaishengit.dao;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Test;



/**
 * Created by lenovo on 2016/12/13.
 */
public class CacheTestCase {
    @Test
    public void testCache(){
        CacheManager cacheManager = new CacheManager();
        Ehcache ehcache = cacheManager.getEhcache("user");

        //添加
         Element element = new Element("user:1","李四");
         ehcache.put(element);
        //删除缓存
        ehcache.remove("user:1");
        //ehcache.removeAll();
        //取值
        Element e = ehcache.get("user:1");
        System.out.println(e.getObjectValue());

    }
}
