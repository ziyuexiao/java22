package com.kaishengit.dao;

import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2016/12/20.
 */
public class TopicDao {
    public Integer save(Topic topic){
        String sql = "insert into t_topic(title,content,t_user_id,t_note_id) values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getT_user_id(),topic.getT_note_id());
    }
    public Topic findTopicById(String topicId) {
        String sql = "select * from t_topic where id = ?";
        return  DbHelp.query(sql,new BeanHandler<>(Topic.class),topicId);
    }
    public void update(Topic topic){
        String sql = "update t_topic set title = ?,content = ?,clicknum = ?,favnum = ?,thankyounum = ?,replynum = ?,lastreplytime = ?,t_user_id = ?,t_note_id = ? where id = ?";
        DbHelp.update(sql,topic.getTitle(),topic.getContent(),topic.getClicknum(),topic.getFavnum(),topic.getThankyounum(),topic.getReplynum(),topic.getLastreplytime(),topic.getT_user_id(),topic.getT_note_id(),topic.getId());
    }

    public int count() {
        String sql = "select count(*) from t_topic";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public int count(String t_note_id) {
        String sql = "select count(*) from t_topic where t_note_id = ? ";
        return DbHelp.query(sql,new ScalarHandler<Long>(),t_note_id).intValue();

    }

    public List<Topic> findAll(HashMap<String, Object> map) {

        String sql = "SELECT tu.username,tu.avatar,tt.* FROM t_topic tt,t_user tu WHERE tt.t_user_id = tu.id ";
        String t_note_id = map.get("t_note_id") == null ?null:String.valueOf(map.get("t_note_id"));
        String where = "";
        List<Object> array = new ArrayList<>();
        if (StringUtils.isNotEmpty(t_note_id)){
            where += "AND t_note_id = ? ";
            array.add(t_note_id);
        }
        where += "ORDER BY tt.lastreplytime DESC LIMIT ?,?";
        array.add(map.get("start"));
        array.add(map.get("pageSize"));
        sql += where;

        return DbHelp.query(sql, new AbstractListHandler<Topic>() {
            @Override
            protected Topic handleRow(ResultSet rs) throws SQLException {
                Topic topic = new BasicRowProcessor().toBean(rs,Topic.class);
                User user = new User();
                user.setId(rs.getInt("t_user_id"));
                user.setUsername(rs.getString("username"));
                user.setAvatar(Config.get("qiniu.domain") + rs.getString("avatar"));
                topic.setUser(user);
                return topic;
            }
        },array.toArray());
    }
}
