package com.kaishengit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2017/1/8.
 */
/*自动产生的代理对象*/
public class SubjectInvocationHandler implements InvocationHandler {

    private Object target;

    public SubjectInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("sayHello".equals(method.getName())){
            System.out.println("目标对象执行前做的事");
            Object result = method.invoke(target, args);//代表目标对象方法的执行
            System.out.println("目标对象执行后做的事");
            return result;
        }else {
            return method.invoke(target,args);
        }
    }
}
