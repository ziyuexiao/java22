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

    /**
     * 检验节点是否已经存在
     * @param nodeid
     * @param nodename
     * @return
     */
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

    /**
     * 修改节点
     * @param nodeid
     * @param nodename
     */
    public void updateNode(String nodeid, String nodename) {
        if (StringUtils.isNumeric(nodeid) && StringUtils.isNotEmpty(nodename)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
            node.setNodename(nodename);
            nodeDao.update(node);
        } else {
            throw new ServiceException("参数异常");
        }
    }

    /**
     * 删除节点
     * @param id
     */

    public void delNodeByid(String id) throws ServiceException {
        Node node = nodeDao.findNodeById(Integer.valueOf(id));

        if (node.getTopicnum()!=null){
            System.out.println(node.getTopicnum());
            throw new ServiceException("该节点下有主题，不能删除");
        }else {
            nodeDao.delByid(id);
        }

    }
}
