package com.kaishengit.web.user;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2016/12/26.
 */
@WebServlet("/notify")
public class NotifyServlet extends BaseServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = getCurrentUser(req);

        List<Notify> notifyList = userService.findNotifyListByUser(user);

        int a = userService.findcountBystateanduser(user);
        int b = userService.findcountByuser(user);

        req.setAttribute("notifyList",notifyList);
        req.setAttribute("a",a);
        req.setAttribute("b",b);


        forword("user/notify.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = getCurrentUser(req);
        //1.根据用户id和通知状态查询未读列表

        //2.根据guava 的Collections2.filter 过滤未读消息数据
        List<Notify> notifyList = userService.findNotifyListByUser(user);
        List<Notify> unreadList = Lists.newArrayList(Collections2.filter(notifyList, new Predicate<Notify>() {
            @Override
            public boolean apply(Notify notify) {
                return notify.getState()==0;
            }
        }));
        JsonResult result = new JsonResult();
        result.setData(unreadList.size());
        result.setState(result.SUCCESS);
        renderJson(result,resp);

    }
}
