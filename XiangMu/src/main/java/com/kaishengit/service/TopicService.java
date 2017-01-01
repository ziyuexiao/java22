package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.dao.*;
import com.kaishengit.entity.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2016/12/20.
 */
public class TopicService {

    TopicDao topicDao = new TopicDao();
    UserDao userDao = new UserDao();
    NodeDao nodeDao = new NodeDao();
    ReplyDao replyDao = new ReplyDao();
    FavDao favDao = new FavDao();
    NotifyDao notifyDao = new NotifyDao();

    /**
     * 获取节点
     * @return
     */
    public List<Node> findAllNode(){
        List<Node> nodeList = nodeDao.findAllNodes();
        return nodeList;
    }
    /**
     * 添加主题,发帖
     */
    public Topic addNewTopic(String title,String content,Integer t_user_id,Integer t_note_id){
        Topic topic = new Topic();
        topic.setT_user_id(t_user_id);
        topic.setT_note_id(t_note_id);
        topic.setTitle(title);
        topic.setContent(content);

        /*//暂时设置最后回复时间为当前时间
        //topic.setLastreplytime(new Timestamp(new DateTime().getMillis()));*/

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
            //暂时设置最后回复时间为当前时间
            topic.setLastreplytime(new Timestamp(DateTime.now().getMillis()));
            topicDao.update(topic);
        }else {
            throw new ServiceException("回复主题不存在或已被删除");
        }

        //增加消息通知
        if(!user.getId().equals(topic.getT_user_id())){
            Notify notify = new Notify();
            notify.setUserid(topic.getT_user_id());
            System.out.println(topic.getT_user_id());
            notify.setState(Notify.STATE_UNREAD);
            System.out.println(Notify.STATE_UNREAD);
            //notify.setContent("你的帖子有了新的回复，请查看");
            notify.setContent("<a href=\"/topicDetail?topicid="+topic.getId()+"\">"+ topic.getTitle()+"</a>");
           // notify.setContent("您的主题 <a href=\"/topicDetail?topicid="+topic.getId()+"\">topic.getTitle()</a> 有了新的回复,请查看.");
          //  System.out.println("你的帖子<a href=\"/topicDetail?topicid="+topic.getId()+"\">["+ topic.getTitle()+"]</a>有了新的回复，请查看");
            notifyDao.save(notify);
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

    /**
     * 收藏业务
     * @param user
     * @param topicid
     */


    public Fav findFavByUserIdAndTopicId(User user,String topicid) {
        return favDao.findByTopicIdAndUserId(user.getId(),Integer.valueOf(topicid));
    }

    public void favTopic(User user, String topicid) {
        Fav fav = new Fav();
        fav.setUserid(user.getId());
        fav.setTopicid(Integer.valueOf(topicid));
        favDao.addFav(fav);

        //更新topic表中的favnum
        Topic topic = topicDao.findTopicById(topicid);
        topic.setFavnum(topic.getFavnum()+1);
        topicDao.update(topic);

    }

    public void unfavTopic(User user, String topicid) {
        //user = user.getId();
        favDao.deleteFav(user.getId(),topicid);
        //更新topic表中的favnum
        Topic topic = topicDao.findTopicById(topicid);
        topic.setFavnum(topic.getFavnum()-1);
        topicDao.update(topic);
    }
    public void updateTopic(Topic topic) {
        topicDao.update(topic);
    }
    /**
     * 感谢业务
     */
    /*  //更新topic表中的thankyounum
                topic.setThankyounum(topic.getThankyounum()+1);
                topicDao.update(topic);



                */


    /**
     * 编辑
     * @param title
     * @param topicId
     * @param content
     * @param nodeid
     */

    public void updateTopicById(String title, String topicId, String content, String nodeid) {
        Topic topic = topicDao.findTopicById(topicId);
        Integer lastNodeId = topic.getT_note_id();
        if(topic.isEdit()){
            topic.setTitle(title);
            topic.setContent(content);
            topic.setT_note_id(Integer.valueOf(nodeid));

           topicDao.update(topic);
             if (lastNodeId != Integer.valueOf(nodeid)) {
                //更新node表，使得原來的node的topicnum -1
                Node lastNode = nodeDao.findNodeById(lastNodeId);
                lastNode.setTopicnum(lastNode.getTopicnum() - 1);
                nodeDao.update(lastNode);
                //更新node表，使得新的node的topicnum + 1
                Node newNode = nodeDao.findNodeById(Integer.valueOf(nodeid));
                newNode.setTopicnum(newNode.getTopicnum() + 1);
                nodeDao.update(newNode);
            }
        }else {
            throw new ServiceException("该帖子已经不可编辑");
        }
    }

    /**
     * 分页
     * @param nodeid
     * @param pageNo
     * @return
     */

    public Page<Topic> findAllTopics(String nodeid, Integer pageNo) {
        HashMap<String,Object> map = Maps.newHashMap();
        int count = 0;
        if (StringUtils.isEmpty(nodeid)){
            count = topicDao.count();
        }else{
            count = topicDao.count(nodeid);
        }

        Page<Topic> topicPage = new Page<>(count,pageNo);
        map.put("nodeid",nodeid);
        map.put("start",topicPage.getStart());
        map.put("pageSize",topicPage.getPageSize());

        List<Topic> topicList = topicDao.findAll(map);
        topicPage.setItems(topicList);
        return topicPage;
    }


    /**
     * 查询节点
     * @param nodeId
     * @return
     */
    public Node findNodeById(String nodeId) {
        if (StringUtils.isNumeric(nodeId)){
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            if (node != null){
                return node;
            }else{
                throw new ServiceException("此节点不存在");
            }

        }else{
            throw new ServiceException("参数异常");
        }

    }

    /**
     * 修改帖子节点
     * @param topicid
     * @param nodeid
     */
    public void updateTopicNode(String topicid, String nodeid) {
        Topic topic = topicDao.findTopicById(topicid);
        topic.setT_note_id(Integer.valueOf(nodeid));
        topicDao.update(topic);
    }

    /**
     * 获取主题数和回复数
     * @param pageNo
     * @return
     */
    public Page<TopicAndReplyCount> getTopicAndReplyNumByDayList(Integer pageNo) {

        int count = topicDao.countTopicByDay();//获得了日期数
        Page<TopicAndReplyCount> page = new Page<>(count,pageNo);
        List<TopicAndReplyCount> countLit =  topicDao.getTopicAndReplyNumList(page.getStart(),page.getPageSize());
        page.setItems(countLit);
        return page;

    }


}
