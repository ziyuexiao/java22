package com.kaishengit.web;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/12.
 */
@WebServlet("/wysiwyg")
public class WysiwygServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "LI7uRDL_runXFHqiiTR-r2hFmToe8ThEnKiBBMl4";
        String sk = "bE2Ddb8bWYEjqbiumJs64Xr2P_rMyaIWCVTFV5HB";
        String bucketName = "demo22";

        Auth auth = Auth.create(ak,sk);

        String returnBody = "{\"success\":true,\"file_path\":\"http://oi0x10ek3.bkt.clouddn.com/${key}\"}";

        StringMap map = new StringMap();
        map.put("returnBody",returnBody);

        String token = auth.uploadToken(bucketName,null,3600,map);

        req.setAttribute("token",token);
        req.getRequestDispatcher("wysiwyg.jsp").forward(req,resp);

    }
}
