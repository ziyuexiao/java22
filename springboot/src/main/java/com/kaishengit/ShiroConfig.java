package com.kaishengit;

import com.google.common.collect.Maps;
import com.kaishengit.shiro.ShiroDbRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Map;

/**
 * Created by lenovo on 2017/3/23.
 */
//@Configuration
public class ShiroConfig {
    @Bean
    public CacheManager cacheManager() {//<bean id="cacheManager" class="org.apache.shiro.cache.MemoryConstrainedCacheManager"/>
        return new MemoryConstrainedCacheManager();
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {// <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setCacheManager(cacheManager());
        webSecurityManager.setRealm(shiroDbRealm());
        return webSecurityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/home");
        factoryBean.setUnauthorizedUrl("/403");

        Map<String,String> filterMap = Maps.newLinkedHashMap();

        filterMap.put("/**","authc");

        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }
    /**
     * <bean id="shiroFilter"
     class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
     <property name="securityManager" ref="securityManager"/>
     <!--登录地址-->
     <property name="loginUrl" value="/"/>
     <!--登录成功后的地址-->
     <property name="successUrl" value="/home"/>
     <!--没有权限给用户提示的页面-->
     <property name="unauthorizedUrl" value="/403"/>
     <!--权限配置-->
     <property name="filterChainDefinitions">
     <value>
     <!--static开头的url不登陆也能访问-->
     /static/** = anon

     <!--微信-->
     /weixin/init = anon

     <!--有管理员权限的才能访问/user和/setting/**-->
     /user = roles[admin]
     /setting/** = roles[admin]
     <!--全部需要认证（访问的url登陆后才能访问）-->
     /** = authc
     </value>
     </property>
     </bean>
     */
}
