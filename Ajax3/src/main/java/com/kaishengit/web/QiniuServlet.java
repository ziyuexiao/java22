package com.kaishengit.web;

import com.qiniu.common.Zone;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/qiniu")
public class QiniuServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "LI7uRDL_runXFHqiiTR-r2hFmToe8ThEnKiBBMl4";
        String sk = "bE2Ddb8bWYEjqbiumJs64Xr2P_rMyaIWCVTFV5HB";
        String bucketName = "demo22";

        Auth auth = Auth.create(ak,sk);
        //计算上传文件的Token
        StringMap map = new StringMap();
        map.put("returnUrl","http://localhost:8080/qiniucallback");//指定回跳位置

        String token = auth.uploadToken(bucketName,null,3600,map);

        req.setAttribute("token",token);
        req.getRequestDispatcher("qiniu.jsp").forward(req,resp);

    }
}
