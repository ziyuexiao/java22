package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.StringUtils;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lenovo on 2016/12/20.
 */
public class TopicService {

    TopicDao topicDao = new TopicDao();
    UserDao userDao = new UserDao();
    NodeDao nodeDao = new NodeDao();
    ReplyDao replyDao = new ReplyDao();

    /**
     * 获取节点
     * @return
     */
    public List<Node> findAllNode(){
        List<Node> nodeList = nodeDao.findAllNodes();
        return nodeList;
    }
    /**
     * 添加主题
     */
    public Topic addNewTopic(String title,String content,Integer t_user_id,Integer t_note_id){
        Topic topic = new Topic();
        topic.setT_user_id(t_user_id);
        topic.setT_note_id(t_note_id);
        topic.setTitle(title);
        topic.setContent(content);

        //暂时设置最后回复时间为当前时间
        topic.setLastreplytime(new Timestamp(new DateTime().getMillis()));

        Integer topicId = topicDao.save(topic);
        System.out.println(topicId);
        topic.setId(topicId);



        //更新node表的topicnum
       Node node = nodeDao.findNodeById(t_note_id);
       if (node != null){
           node.setTopicnum(node.getTopicnum()+1);
           nodeDao.update(node);
       }else {
           throw new ServiceException("节点不在");
       }


        return topic;
    }
    /**
     * 获取详情页
     */
    public Topic findTopicById(String topicId){
        if(StringUtils.isNumeric(topicId)){
            Topic topic = topicDao.findTopicById(topicId);
            if(topicId!=null){
                User user = userDao.findById(topic.getT_user_id());
                Node node = nodeDao.findNodeById(topic.getT_note_id());

                user.setAvatar(Config.get("qiniu.domain")+user.getAvatar());
                topic.setUser(user);
                topic.setNode(node);

                //更新topic表中的clicknum字段
                topic.setClicknum(topic.getClicknum()+1);
                topicDao.update(topic);

                return topic;

            }else {
                throw new ServiceException("该帖不存在或已被删除");
            }
        }else{
            throw new ServiceException("参数异常");
        }
    }

    /**
     * 添加回复
     * @param topicId
     * @param content
     * @param user
     */
    public void addNewReply(String topicId, String content, User user) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setUserid(user.getId());
        reply.setTopicid(Integer.valueOf(topicId));
        replyDao.addReply(reply);


        //更新t_topic表中的replynum 和 lastreplytime字段
        Topic topic = topicDao.findTopicById(topicId);
        if(topic!=null){
            topic.setReplynum(topic.getReplynum()+1);
            topic.setLastreplytime(new Timestamp(DateTime.now().getMillis()));
            topicDao.update(topic);
        }else {
            throw new ServiceException("回复主题不存在或已被删除");
        }
    }

    /**
     * 获取回复列表
     * @param topicid
     * @return
     */
    public List<Reply> findReplyByTopicid(String topicid) {
        return replyDao.findReplyListByTopicid(topicid);

    }
}
