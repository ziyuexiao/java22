package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.StringUtils;

/**
 * Created by lenovo on 2016/12/28.
 */
public class NodeService {
    NodeDao nodeDao = new NodeDao();
    public String validateNodeName(String nodeid, String nodename) {
        Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
        if (node.getNodename().equals(nodename)){
            return "true";
        }else {
            Node nodeIsIn = nodeDao.findNodeByName(nodename);
            if (nodeIsIn == null) {
                return "true";
            }
        }
        return "false";
    }


    public void updateNode(String nodeid, String nodename) {
        if (StringUtils.isNumeric(nodeid) && StringUtils.isNotEmpty(nodename)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
            node.setNodename(nodename);
            nodeDao.update(node);
        } else {
            throw new ServiceException("参数异常");
        }
    }
}
