package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/23.
 */
@WebServlet("/topicFav")
public class TopicfavServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String topicid = req.getParameter("topicid");
        User user = (User) req.getSession().getAttribute("curr_user");
        TopicService topicService = new TopicService();
        JsonResult result = new JsonResult();
        if(StringUtils.isNotEmpty(action)){
            if("fav".equals(action)){
                topicService.favTopic(user,topicid);
                result.setState(JsonResult.SUCCESS);
            }else if ("unfav".equals(action)){
               topicService.unfavTopic(user,topicid);
                result.setState(JsonResult.SUCCESS);
            }
            TopicService service = new TopicService();
            Topic topic = topicService.findTopicById(topicid);
            result.setData(topic.getFavnum());
        }else {
            result.setMessage("参数异常");
        }
        renderJson(result,resp);
    }
}
