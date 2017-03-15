package com.kaishengit.web.topic;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Config;
import com.kaishengit.web.BaseServlet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

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

        //获取七牛上传的token
        Auth auth = Auth.create(Config.get("qiniu.ak"),Config.get("qiniu.sk"));
        StringMap map = new StringMap();

        String returnBody = "{ \"success\": true,\"file_path\": \""+Config.get("qiniu.domain")+"${key}\"}";
        map.put("returnBody",returnBody);
        //map.put("returnBody","{ \"success\": true,\"file_path\": \""+Config.get("qiniu.domain")+"${key}\"}");
        String token = auth.uploadToken(Config.get("qiniu.storage"),null,3600,map);



        //获取nodelist到jsp页面
        List<Node> nodeList = topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);

        req.setAttribute("token",token);
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

