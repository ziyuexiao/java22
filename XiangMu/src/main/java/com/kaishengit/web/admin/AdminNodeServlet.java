package com.kaishengit.web.admin;

import com.kaishengit.entity.Node;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/28.
 */
@WebServlet("/admin/node")
public class AdminNodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicService topicService = new TopicService();
        List<Node> nodeList = topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);

        forword("admin/node.jsp",req,resp);
    }
}
