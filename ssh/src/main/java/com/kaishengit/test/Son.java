package com.kaishengit.test;

import com.kaishengit.pojo.Movie;

public class Son extends Father<Movie> {

    public Son() {
       // super();//构造方法中会默认有这一个
        System.out.println("Create son :" + this);//this指的是子类对象
    }

}
