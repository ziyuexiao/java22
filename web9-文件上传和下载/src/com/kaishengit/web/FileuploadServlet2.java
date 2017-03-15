package com.kaishengit.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@WebServlet("/upload2")
@MultipartConfig//有此注解文件才能上传
public class FileuploadServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/upload2.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String desc = req.getParameter("desc");
		//根据name属性值获取对应的part对象
		Part part = req.getPart("file");
		
		System.out.println("getName:" + part.getName());//上传文件的name属性值
		System.out.println("getContentType:" + part.getContentType());//文件的MIME类型，由文件后缀名决定
		System.out.println("getSize:" + part.getSize());//上传文件的体积（字节）
		System.out.println(FileUtils.byteCountToDisplaySize(part.getSize()));//上传文件的体积,转为可读
		System.out.println("getSubittedFileName:" + part.getSubmittedFileName());//获取上传的文件名
		
		
		  File saveDir = new File("F:/upload");
	        if(!saveDir.exists()) {
	            saveDir.mkdir();
	        }
	        
	        String filename = part.getSubmittedFileName();
	        String newname = UUID.randomUUID().toString()+filename.substring(filename.lastIndexOf("."));
	        
	        InputStream inputstream = part.getInputStream();
	        FileOutputStream outputstream = new FileOutputStream(new File(saveDir,newname));
	        
	        IOUtils.copy(inputstream, outputstream);
	        
	        outputstream.flush();
	        outputstream.close();
	        inputstream.close();

	        System.out.println(filename + "  -> upload success!");

	        
	        
	}
	
	
}
