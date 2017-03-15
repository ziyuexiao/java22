package com.kaishengit.proxy;

/**
 * Created by lenovo on 2017/1/8.
 */
public class RealSubject implements Subject {
    @Override
    public void sayHello() {
        System.out.println("RealSubject sayHello...");
    }

    @Override
    public void sava() {
        System.out.println("RealSubject save...");
    }

}
