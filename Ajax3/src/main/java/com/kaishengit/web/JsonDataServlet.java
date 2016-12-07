package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2016/12/7.
 */
@WebServlet("/data.json")
public class JsonDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        User user = new User(1,"老李","北京");
        User user1 = new User(12,"王明明","上海");
        User user2 = new User(23,"赵丽丽","郑州");

        List<User> userList = Arrays.asList(user,user1,user2);

        Gson gson = new Gson();
        String json = gson.toJson(userList);


        PrintWriter out = resp.getWriter();
        //out.print("{\"id\":1,\"name\":\"james\"}");
        out.print(json);
        out.flush();
        out.close();
    }
}
