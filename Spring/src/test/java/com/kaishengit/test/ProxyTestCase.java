package com.kaishengit.test;

import com.kaishengit.proxy.*;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by lenovo on 2017/1/8.
 */
public class ProxyTestCase {
    @Test
    public void proxy() {
        Subject subject = new SubjectProxy();
        subject.sayHello();
    }
    @Test
    public void jdkproxy(){
        RealSubject realSubject = new RealSubject();
        InvocationHandler invocationHandler = new SubjectInvocationHandler(realSubject);

        Subject subject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),invocationHandler);
        subject.sayHello();
    }
    @Test
    public void cglibproxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new TargetMethodInterceptor());
        Target target = (Target) enhancer.create();//父类指向子类对象
        target.save();
        target.update();
    }
}
