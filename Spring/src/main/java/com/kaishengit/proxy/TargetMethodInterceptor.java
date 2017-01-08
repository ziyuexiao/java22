package com.kaishengit.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2017/1/8.
 */
public class TargetMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object target, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标对象执行前做的事");
        Object result = methodProxy.invokeSuper(target,params);//代表目标对象方法的执行
        System.out.println("目标对象执行后做的事");
        return result;
    }
}
