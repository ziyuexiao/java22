package com.kaishengit.web;



import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lenovo on 2016/12/12.
 */
@WebServlet("/img")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String file = req.getParameter("file");
        if (StringUtils.isEmpty(file)){
            resp.sendError(404);
        }else {
            File f = new File("F:/upload",file);
            if (!f.exists()){
                resp.sendError(404);
            }else {
                FileInputStream fileInputStream = new FileInputStream(f);
                OutputStream outputStream =resp.getOutputStream();

                IOUtils.copy(fileInputStream,outputStream);

                outputStream.flush();
                fileInputStream.close();
                outputStream.close();
            }

        }
    }
}
