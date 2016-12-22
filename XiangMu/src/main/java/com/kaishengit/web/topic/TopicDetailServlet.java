package com.kaishengit.web.topic;

import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/21.
 */
@WebServlet("/topicDetail")
public class TopicDetailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        TopicService topicService = new TopicService();
       try {
           //获取对应的topic
           Topic topic = topicService.findTopicById(topicid);

           //获取该topicid对应的回复列表
           List<Reply> replyList = topicService.findReplyByTopicid(topicid);
           req.setAttribute("replyList",replyList);

           req.setAttribute("topic",topic);
           forword("topic/topicDetail.jsp",req,resp);
       }catch (ServiceException e){
           resp.sendError(404);
       }

    }
}
