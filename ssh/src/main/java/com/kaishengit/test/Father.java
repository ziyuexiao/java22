package com.kaishengit.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Type是一个接口,只有一个实现类Class
 * @param <T>
 */
public class Father <T> {

    public Father() {
        System.out.println("create father:" + this);//当父类自己new一个对象时，this就是他自己。当通过子类new一个父类时，this是这个子类对象

        Class clazz = this.getClass();//获得是子类的class对象
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();//获取一个泛型父类，Father<Movie>，因为Son extends Father<Movie>，所以不是Father<T>
        Type[] types = parameterizedType.getActualTypeArguments();//返回的是数组，因为T可以为多个。
        Class paramClass = (Class) types[0];
        System.out.println(paramClass);


    }
}
