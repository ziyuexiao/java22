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

        Integer topicId = topicDao.save(topic);
        System.out.println(topicId);
        topic.setId(topicId);

        //topicDao.save(topic);
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
    }
}
