package com.kaishengit.web;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/15.
 */
@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nodeid = req.getParameter("nodeid");
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        if(StringUtils.isNotEmpty(nodeid)&& !StringUtils.isNumeric(nodeid)){
            forword("home.jsp",req,resp);
            return;
        }


        TopicService topicService = new TopicService();
        List<Node> nodeList = topicService.findAllNode();


        Page<Topic> page = topicService.findAllTopics(nodeid,pageNo);
        req.setAttribute("page",page);
        req.setAttribute("nodeList",nodeList);




        forword("home.jsp",req,resp);
    }
}
