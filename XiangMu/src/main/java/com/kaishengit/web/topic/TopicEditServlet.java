package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/24.
 */
@WebServlet("/topicEdit")
public class TopicEditServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");

        TopicService service = new TopicService();
        Topic topic = service.findTopicById(topicid);

        List<Node> nodeList = service.findAllNode();
        req.setAttribute("topic",topic);
        req.setAttribute("nodeList",nodeList);
        forword("topic/topicEdit.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String topicId = req.getParameter("topicId");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");

        JsonResult result = null;
        if(StringUtils.isNumeric(topicId)){
           try {
               TopicService topicService = new TopicService();
               topicService.updateTopicById(title,topicId,content,nodeid);
               result = new JsonResult();
               result.setState(JsonResult.SUCCESS);
               result.setData(topicId);
           }catch (ServiceException e){
               result = new JsonResult(e.getMessage());
           }

        }else {
            result = new JsonResult("参数异常");
        }
        renderJson(result,resp);
    }
}
