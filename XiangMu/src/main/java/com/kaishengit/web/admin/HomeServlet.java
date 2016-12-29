package com.kaishengit.web.admin;

import com.kaishengit.entity.TopicAndReplyCount;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/28.
 */
@WebServlet("/admin/home")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        Page<TopicAndReplyCount> page = new TopicService().getTopicAndReplyNumByDayList(pageNo);

        req.setAttribute("page",page);

        forword("admin/first.jsp",req,resp);
    }
}
