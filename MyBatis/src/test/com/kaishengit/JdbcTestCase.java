package com.kaishengit;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lenovo on 2017/1/4.
 */
public class JdbcTestCase {
    @Test
    public void test() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql:///db_22","root","root");

        PreparedStatement statement = null;

        //开启事务
        connection.setAutoCommit(false);//默认值为true

        try {
            String sql = "INSERT into user3(username,address) values('刘师傅','北京')";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            String sql2 = "INSERT into user3(username,address) values('老师傅','上海')";
            statement = connection.prepareStatement(sql2);
            statement.executeUpdate();

            connection.commit(); //提交事务
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback(); //回滚事务,撤销前面的增删改
        } finally {
            statement.close();
            connection.close();
        }
    }
}
