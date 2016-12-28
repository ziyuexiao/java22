package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lenovo on 2016/12/28.
 */
public class AdminService {
        Logger logger = LoggerFactory.getLogger(AdminService.class);

        ReplyDao replyDao = new ReplyDao();
        TopicDao topicDao = new TopicDao();
        AdminDao adminDao = new AdminDao();
        NodeDao nodeDao = new NodeDao();

    /**
     * 进入管理系统
     * @param adminname
     * @param password
     * @param ip
     * @return
     */
    public Admin adminlogin(String adminname, String password, String ip) {
        Admin admin = adminDao.findAdminByname(adminname);
        if (admin!=null&&admin.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt")+password))){
            logger.debug("管理员{}登录了，IP为{}",admin,ip);
            return admin;
        }else {
            throw new ServiceException("账号或密码错误");
        }

    }


    /**
     * 删帖
     * @param id
     */
    public void deleteTopicById(String id) {
        //删除帖子回复
        replyDao.delByTopicid(id);

        //更新节点下的帖子数量
        //1，根据topicid得到nodeid
        Topic topic = topicDao.findTopicById(id);
        //2.根据nodeid 获取node
        if (topic!=null){
            Node node = nodeDao.findNodeById(topic.getT_note_id());
            //3.更新node
            node.setTopicnum(node.getTopicnum()-1);
            //删除主题
            topicDao.delByTopicid(id);
        }else {
            throw new ServiceException("要删除的主题不存在或已删除");
        }



    }
}
