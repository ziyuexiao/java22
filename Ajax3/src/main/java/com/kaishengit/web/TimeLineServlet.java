package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/9.
 */
@WebServlet("/timeline")
public class TimeLineServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MaxId = req.getParameter("MaxId");

        int id = 0;
        if(StringUtils.isNumeric(MaxId)){

            id=Integer.parseInt(MaxId);
        }

        MessageService messageService = new MessageService();
        List<Message> messageList = messageService.findByMaxId(id);
        renderJSON(messageList,resp);



    }
}
