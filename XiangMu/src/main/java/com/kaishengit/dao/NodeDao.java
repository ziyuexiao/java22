package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by lenovo on 2016/12/20.
 */
public class NodeDao {
    public List<Node> findAllNodes(){
        String sql = "select*from t_node";
        return DbHelp.query(sql,new BeanListHandler<>(Node.class));
    }
    public Node findNodeById(Integer id){
        String sql = "select*from t_node where id=?";
        return DbHelp.query(sql,new BeanHandler<Node>(Node.class),id);
    }
    public void update(Node node){
        String sql = "update t_node set nodename=?,topicnum=? where id=?";
        DbHelp.update(sql,node.getNodename(),node.getTopicnum(),node.getId());
    }

    public Node findNodeByName(String nodename) {
        String sql = "select * from t_node where nodename = ?";
        return DbHelp.query(sql,new BeanHandler<>(Node.class),nodename);
    }

    public void delByid(String id) {
        String sql = "delete from t_node where id = ?";
        DbHelp.update(sql,id);
    }

    public void savenode(String nodename) {
        String sql = "insert into t_node(nodename) values(?)";
        DbHelp.update(sql,nodename);
    }
}
