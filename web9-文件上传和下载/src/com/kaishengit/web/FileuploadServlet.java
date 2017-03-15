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

/**
 *	FileItem 用来封装表单中的元素和数据。

	ServletFileUpload 处理表单数据，将数据封装到 FileItem 对象中。

	DiskFileItemFactory FileItem 对象的工厂，可以设定缓冲区大小和存放临时文件目录。

	ServletFileUpload 处理上传的文件的数据，优先保存在缓冲区，如果数据超过了缓冲区大小，
	则保存到硬盘上，存储在DiskFileItemFactory指定目录下的临时文件。
	数据都接收完后，它再在从临时文件中将数据写入到上传文件目录下的指定文件中，并删除临时文件。
 */
@WebServlet("/upload")
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//将表单的enctype属性值设置为multipart/form-data之后，
        //会导致request.getParameter()方法无法获取表单元素的值
		//1,文件上传后存放的路径
		File savaDir = new File("F:/upload");
		if(!savaDir.exists()){
			savaDir.mkdirs();// 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
		}
		//2.设置文件上传的临时路径
		File tempDir = new File("F:/temdir");
		if(!tempDir.exists()){
			tempDir.mkdirs();
		}
		
		//3.判断表单是否设置enctype属性
		if(ServletFileUpload.isMultipartContent(req)){
			
			DiskFileItemFactory itemFactory = new DiskFileItemFactory();
			itemFactory.setSizeThreshold(1024);// 设定了1KB的缓冲区
			itemFactory.setRepository(tempDir);//设置上传文件的临时目录
			
			ServletFileUpload servletfileupload = new ServletFileUpload(itemFactory);
			servletfileupload.setSizeMax(1024*1024*10); //上传文件最大体积
			
			
			try {
				//获取表单中的所有表单元素(普通元素+文件元素)，包装成FileItem对象
				List<FileItem> fileItemList = servletfileupload.parseRequest(req);
				
				
				for(FileItem item:fileItemList){
					if(item.isFormField()){//如果是表单域 ，就是非文件上传元素
						//普通元素
						System.out.println("FieldName:" + item.getFieldName());//获取name属性的值
						System.out.println("getString:" + item.getString("UTF-8"));
					}else{
						 //文件元素
                        System.out.println("FieldName:" + item.getFieldName()); //获取表单中name属性的值
                        System.out.println("Name:" + item.getName()); //获取上传文件的原始名称(文件名)
                        //获取文件的输入流
                        InputStream inputstream = item.getInputStream();
                        String filename = item.getName();
                        String newFileName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
                        
                        FileOutputStream outputstream = new  FileOutputStream(new File(savaDir,newFileName));
                        
                        IOUtils.copy(inputstream, outputstream);
                        outputstream.flush();
                        outputstream.close();
                        inputstream.close();
                        
                        
                        System.out.println(item.getName() + "  -> 文件上传成功！");
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
