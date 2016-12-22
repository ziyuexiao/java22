package com.kaishengit.dao;

import com.kaishengit.entity.Topic;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
}
