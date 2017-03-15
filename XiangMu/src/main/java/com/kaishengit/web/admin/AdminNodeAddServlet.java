package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.NodeService;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/31.
 */
@WebServlet("/admin/nodeadd")
public class AdminNodeAddServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodename = req.getParameter("nodename");
        NodeService nodeService = new NodeService();


            forword("admin/addnode.jsp",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nodename = req.getParameter("nodename");
        NodeService nodeService = new NodeService();
        JsonResult result = new JsonResult();
        try {
            nodeService.addNode(nodename);
            result.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            result.setState(JsonResult.ERROR);
            result.setMessage(e.getMessage());
        }
        renderJson(result,resp);


    }
}
