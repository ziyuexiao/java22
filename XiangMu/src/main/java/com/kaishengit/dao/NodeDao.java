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
}