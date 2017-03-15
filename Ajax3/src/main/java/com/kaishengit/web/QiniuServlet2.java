package com.kaishengit.web;

import com.kaishengit.entity.User;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/12.
 */
@WebServlet("/qiniu2")
public class QiniuServlet2 extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "LI7uRDL_runXFHqiiTR-r2hFmToe8ThEnKiBBMl4";
        String sk = "bE2Ddb8bWYEjqbiumJs64Xr2P_rMyaIWCVTFV5HB";
        String bucketName = "demo22";

        Auth auth = Auth.create(ak,sk);

       /* //获取session对象
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();*/

        String token = auth.uploadToken(bucketName);

        req.setAttribute("id",11223);
        req.setAttribute("token",token);
        req.getRequestDispatcher("uploader_qiniu.jsp").forward(req,resp);
    }
}
