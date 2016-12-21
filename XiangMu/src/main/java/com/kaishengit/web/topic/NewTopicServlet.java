package com.kaishengit.web.topic;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by lenovo on 2016/12/20.
 */
@WebServlet("/newTopic")
public class NewTopicServlet extends BaseServlet {

    TopicService topicService = new TopicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Node> nodeList = topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);

        forword("topic/newTopic.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("t_note_id");

        User user = (User)req.getSession().getAttribute("curr_user");
        Topic topic = topicService.addNewTopic(title,content,user.getId(),Integer.valueOf(nodeid));
        JsonResult jsonResult = new JsonResult(topic);
        renderJson(jsonResult,resp);

        /*Map<String,Object> result = Maps.newHashMap();

        try {
            topicService.addNewTopic(title,content,user.getId(),Integer.valueOf(nodeid));
            result.put("state","success");
        }catch (ServiceException e){
            result.put("state","error");
            result.put("message",e.getMessage());

        }
        renderJson(result,resp);*/


    }
}

