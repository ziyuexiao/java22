package com.kaishengit.web;



import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lenovo on 2016/12/12.
 */
@WebServlet("/upload2")
@MultipartConfig
public class Upload2Servlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");

        InputStream inputStream = part.getInputStream();
        String uuid = UUID.randomUUID().toString();
        File file = new File("F:/upload",uuid);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        IOUtils.copy(inputStream,fileOutputStream);

        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();

        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("file_path","http://localhost:8080/img?file="+uuid);
        renderJSON(result,resp);
    }
}
