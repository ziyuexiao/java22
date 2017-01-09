package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/1/9.
 */
@Component
@Aspect
public class AopAspect {

    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pt(){}

    @Before("pt()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }
    @AfterReturning(value="pt()",returning = "result")
    public void afterAdvice(Object result){
        System.out.println("后置通知:"+result);
    }
    @AfterThrowing(value = "pt()",throwing = "e")
    public void exceptionAdvice(Exception e){
        System.out.println("异常通知:"+e.getMessage());
    }
    @After("pt()")
    public void finallyAdvice(){
        System.out.println("最终通知");
    }

   /* @Around("pt()")
    public void arroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

            try {
                System.out.println("~~前置~~");
                Object result = proceedingJoinPoint.proceed();*//*目标对象方法的执行*//*

                System.out.println("~~后置~~");


            } catch (Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("~~异常~~");
            }finally {
                System.out.println("~~最终~~");
            }

    }*/


}
