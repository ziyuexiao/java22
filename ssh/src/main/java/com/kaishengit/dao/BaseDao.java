package com.kaishengit.dao;

import com.kaishengit.util.Page;
import com.kaishengit.util.QueryParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *  BaseDao作为父类不能交给Spring管理
 *  创建这个父类是为了代码的复用
 *  System.out.println("create father:" + this);//当父类自己new一个对象时，this就是他自己。当通过子类new一个父类时，this是这个子类对象

 Class clazz = this.getClass();//获得是子类的class对象
 ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();//获取一个泛型父类，Father<Movie>，因为Son extends Father<Movie>，所以不是Father<T>
 Type[] types = parameterizedType.getActualTypeArguments();//返回的是数组，因为T可以为多个。
 Class paramClass = (Class) types[0];
 System.out.println(paramClass);

 * @param <T>
 */
public class BaseDao<T,PK extends Serializable> {//在泛型中extends前面可以是父类，也可以是接口

    @Autowired
    private SessionFactory sessionFactory;//SessionFactory能够在这里注入是因为BaseDao的子类交给Spring管理

    private Class clazz;//属性

    public BaseDao () {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public T findById(PK id) {
        return (T) getSession().get(clazz,id);
    }
    public void delete(PK id) {
        getSession().delete(findById(id));
    }
    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }
    /*排序后列出*/
    public List<T> findAll(String propertyName,String orderType) {
        Criteria criteria = getSession().createCriteria(clazz);
        if("desc".equalsIgnoreCase(orderType)) {
            criteria.addOrder(Order.desc(propertyName));
        } else {
            criteria.addOrder(Order.asc(propertyName));
        }
        return criteria.list();
    }
    /*获取数量*/
    public Long count(){
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }
    /*有查询条件时的数量*/
    public Long count(List<QueryParam> queryParamList) {
        Criteria criteria = getSession().createCriteria(clazz);
        for(QueryParam queryParam : queryParamList) {
            criteria.add(builderCriterionByQueryParam(queryParam));
        }
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    /*原始分页*/
    public Page<T> findByPage(int pageNo, int pageSize) {
        Page<T> page = new Page<>(pageNo,pageSize,count().intValue());
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(pageSize);
        List<T> items = criteria.list();
        page.setItems(items);
        return page;
    }
    /*有查询条件的分页*/
    public Page<T> findByPage(int pageNo,int pageSize,List<QueryParam> queryParamList) {
        Long count = count(queryParamList);
        Page<T> page = new Page<>(pageNo,pageSize,count.intValue());

        Criteria criteria = getSession().createCriteria(clazz);
        for(QueryParam queryParam : queryParamList) {
            criteria.add(builderCriterionByQueryParam(queryParam));
        }
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(pageSize);
        List<T> items = criteria.list();

        page.setItems(items);
        return page;
    }

    public List<T> findByQueryParam(List<QueryParam> queryParamList) {
        Criteria criteria = getSession().createCriteria(clazz);
        for(QueryParam queryParam : queryParamList) {
            criteria.add(builderCriterionByQueryParam(queryParam));
        }
        return criteria.list();
    }

    /*查询条件为影片或导演*/
    private Criterion builderCriterionByQueryParam(QueryParam queryParam) {
        String propertyName = queryParam.getPropertyName();
        Object value = queryParam.getValue();
        String type = queryParam.getType();
        if(propertyName.contains("_or_")) {
            String[] array = propertyName.split("_or_");
            Disjunction disjunction = Restrictions.disjunction();//hibernate中 disjunction连接起来的都是or的关系
            for(String name : array) {
                Criterion criterion = builderCriterionByQueryParam(name,value,type);
                disjunction.add(criterion);
            }
            return disjunction;
        } else {
            return builderCriterionByQueryParam(propertyName,value,type);
        }
    }


    /*查询条件只能是一种*/
    private Criterion builderCriterionByQueryParam(String propertyName, Object value, String type) {
        if("eq".equalsIgnoreCase(type)) {
            return Restrictions.eq(propertyName,value);
        } else if("gt".equalsIgnoreCase(type)) {
            return Restrictions.gt(propertyName,value);
        } else if("ge".equalsIgnoreCase(type)) {
            return Restrictions.ge(propertyName,value);
        } else if("lt".equalsIgnoreCase(type)) {
            return Restrictions.lt(propertyName,value);
        } else if("le".equalsIgnoreCase(type)) {
            return Restrictions.le(propertyName,value);
        } else if("like".equalsIgnoreCase(type)) {
            return Restrictions.like(propertyName,value.toString(), MatchMode.ANYWHERE);
        }
        return null;
    }



}
