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
 * Created by lenovo on 2016/12/28.
 */
@WebServlet("/admin/nodeUpdate")
public class AdminNodeUpdateServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeId = req.getParameter("nodeid");
        TopicService topicService = new TopicService();
        try {
            Node node = topicService.findNodeById(nodeId);
            req.setAttribute("node", node);
            forword("admin/updatenode.jsp",req,resp);
        }catch (ServiceException e){
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid = req.getParameter("nodeid");
        String nodename = req.getParameter("nodename");
        NodeService nodeService = new NodeService();
        JsonResult result = new JsonResult();
        try {
            nodeService.updateNode(nodeid, nodename);
            result.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            result.setState(JsonResult.ERROR);
            result.setMessage(e.getMessage());
        }
        renderJson(result,resp);

    }
}
