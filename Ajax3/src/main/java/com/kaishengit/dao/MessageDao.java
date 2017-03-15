package com.kaishengit.dao;

import com.kaishengit.entity.Message;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by lenovo on 2016/12/9.
 */
public class MessageDao {
    public List<Message> findAll(){
        String sql = "select*from t_message order by id desc";
        return  DbHelp.query(sql,new BeanListHandler<>(Message.class));
    }
    public List<Message> findByMaxId(int MaxId) {
        String sql = "select * from t_message where id > ? order by id desc";
        return DbHelp.query(sql,new BeanListHandler<>(Message.class),MaxId);
    }
}
