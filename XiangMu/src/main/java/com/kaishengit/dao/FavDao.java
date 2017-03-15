package com.kaishengit.dao;

import com.kaishengit.entity.Fav;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by lenovo on 2016/12/23.
 */
public class FavDao {
    public void addFav(Fav fav) {
        String sql = "insert into t_fav(userid,topicid) values(?,?)";
        DbHelp.update(sql,fav.getUserid(),fav.getTopicid());
    }

    public void deleteFav(Integer userid, String topicid) {
        String sql = "delete from t_fav where userid = ? and topicid = ?";
        DbHelp.update(sql,userid,Integer.valueOf(topicid));
    }

    public Fav findByTopicIdAndUserId(Integer userid, Integer topicid) {
        String sql = "select * from t_fav where userid = ? and topicid = ?";
        return DbHelp.query(sql,new BeanHandler<>(Fav.class),userid,topicid);
    }
}
