package com.kaishengit.dao;

import com.kaishengit.entity.Notify;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by lenovo on 2016/12/26.
 */
public class NotifyDao {

    public List<Notify> findNotifyByUserid(Integer id) {
        String sql = "select*from t_notify where userid = ? order by readtime,createtime";
        return DbHelp.query(sql,new BeanListHandler<Notify>(Notify.class),id);
    }
    public void save(Notify notify){
        String sql = "INSERT INTO t_notify(userid,content,state) VALUES (?,?,?)";
        DbHelp.update(sql,notify.getUserid(),notify.getContent(),notify.getState());
    }


    public Notify findNotifyByid(String id) {
        String sql = "select*from t_notify where id = ?";
        return DbHelp.query(sql,new BeanHandler<Notify>(Notify.class),id);
    }

    public void update(Notify notify) {
        String sql = "update t_notify set state = ?,readtime = ? where id = ?";
        DbHelp.update(sql,notify.getState(),notify.getReadtime(),notify.getId());
    }



    public int count1(Integer userid) {
        String sql = "select count(*) from t_notify where state = 1 and userid = ? ";
        return DbHelp.query(sql,new ScalarHandler<Long>(),userid).intValue();

    }

    public int count2(Integer userid) {
        String sql = "select count(*) from t_notify where  userid = ? ";
        return DbHelp.query(sql,new ScalarHandler<Long>(),userid).intValue();

    }
}
