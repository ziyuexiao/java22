package com.kaishengit.web.topic;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/21.
 */
@WebServlet("/newReply")
public class NewReplyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicId = req.getParameter("topicid");
        String content = req.getParameter("content");
        //User user = (User) req.getSession().getAttribute("curr_user");
        User user = getCurrentUser(req);
        TopicService topicService = new TopicService();
        if (StringUtils.isNumeric(topicId)){
            try {
                topicService.addNewReply(topicId,content,user);
            }catch (ServiceException e){
                resp.sendError(404,e.getMessage());
            }
        }else{
            resp.sendError(404);
        }
        resp.sendRedirect("/topicDetail?topicid="+topicId);
    }
}
