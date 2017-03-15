package com.kaishengit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("file");
		String name = req.getParameter("name");
		 //url中含有中文乱码转换
        //fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
        //System.out.println("fileName:" + fileName);
		
		
		File saveDir = new File("F:/upload");
		File file = new File(saveDir,fileName);
		
		if(file.exists()){
			
			if(StringUtils.isNotEmpty(name)){
				 //设置相应的文件头 MIME Type
				resp.setContentType("application/octet-stream");
				 //设置文件的总大小
				resp.setContentLengthLong(file.length());
				//将浏览器弹出的对话框文字变成中文
				name = new String(name.getBytes("UTF-8"),"ISO8859-1");
				//设置弹出对话框的文件名称
		resp.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
			}
			
			
			
			
			//响应输出流
			OutputStream outputstream = resp.getOutputStream();
			 //文件输入流
            FileInputStream inputstream = new FileInputStream(file);
            IOUtils.copy(inputstream,outputstream);

           /* outputstream.flush();
            outputstream.close();
            inputstream.close();*/

			
		}else{
			resp.sendError(404,"文件找不到");
		}
		
	}

}
