package com.kaishengit.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

@WebServlet("/upload")
public class FileuploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/views/upload.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//文件上传后存放的路径
		File saveDir = new File("F:/upload");
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
		//文件存放的临时路径名
		File temDir = new File("F:/temDir");
		if(!temDir.exists()){
			temDir.mkdirs();
		}
		
		//判断表单是否设置enctype属性
		if(ServletFileUpload.isMultipartContent(req)){
			DiskFileItemFactory itemFactory = new DiskFileItemFactory();
			itemFactory.setSizeThreshold(1024*1024);
			itemFactory.setRepository(temDir);
			
			ServletFileUpload servletfileupload = new ServletFileUpload(itemFactory);
			servletfileupload.setSizeMax(1024*1024*10);
			
			try {
				List<FileItem> fileItemList = servletfileupload.parseRequest(req);
				for(FileItem item:fileItemList){
					if(item.isFormField()){
						//普通元素
						System.out.println("FieldName:" + item.getFieldName());
						System.out.println("getString:" + item.getString("UTF-8"));
					}else{
						 //文件元素
                        System.out.println("FieldName:" + item.getFieldName()); //获取表单中name属性的值
                        System.out.println("Name:" + item.getName()); //获取上传文件的原始名称(文件名)
                        
                        //获取文件的输入流
                        InputStream inputstream=item.getInputStream();
                        String filename = item.getName();
                        String Newfilename = UUID.randomUUID().toString()+filename.substring(filename.lastIndexOf("."));
                        
                        FileOutputStream outputstream = new  FileOutputStream(new File(saveDir,Newfilename));
                        IOUtils.copy(inputstream, outputstream);
                        outputstream.flush();
                        outputstream.close();
                        inputstream.close();
					}
				}
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
			
		}else{
			throw new RuntimeException("form表单enctype属性设置异常");
		}
		
	}

}
