package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by lenovo on 2017/1/9.
 */
public class AopAspect {
    /*public void beforeAdvice(){
        System.out.println("前置通知");
    }
    public void afterAdvice(Object result){
        System.out.println("后置通知:"+result);
    }
    public void exceptionAdvice(Exception e){
        System.out.println("异常通知:"+e.getMessage());
    }
    public void finallyAdvice(){
        System.out.println("最终通知");
    }*/

    public void arroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

            try {
                System.out.println("~~前置~~");
                Object result = proceedingJoinPoint.proceed();/*目标对象方法的执行*/

                System.out.println("~~后置~~");


            } catch (Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("~~异常~~");
            }finally {
                System.out.println("~~最终~~");
            }

    }


}
