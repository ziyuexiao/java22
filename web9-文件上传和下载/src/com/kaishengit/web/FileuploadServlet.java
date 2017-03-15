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
 *	FileItem ������װ���е�Ԫ�غ����ݡ�

	ServletFileUpload ��������ݣ������ݷ�װ�� FileItem �����С�

	DiskFileItemFactory FileItem ����Ĺ����������趨��������С�ʹ����ʱ�ļ�Ŀ¼��

	ServletFileUpload �����ϴ����ļ������ݣ����ȱ����ڻ�������������ݳ����˻�������С��
	�򱣴浽Ӳ���ϣ��洢��DiskFileItemFactoryָ��Ŀ¼�µ���ʱ�ļ���
	���ݶ�������������ڴ���ʱ�ļ��н�����д�뵽�ϴ��ļ�Ŀ¼�µ�ָ���ļ��У���ɾ����ʱ�ļ���
 */
@WebServlet("/upload")
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//������enctype����ֵ����Ϊmultipart/form-data֮��
        //�ᵼ��request.getParameter()�����޷���ȡ��Ԫ�ص�ֵ
		//1,�ļ��ϴ����ŵ�·��
		File savaDir = new File("F:/upload");
		if(!savaDir.exists()){
			savaDir.mkdirs();// �����˳���·����ָ����Ŀ¼���������б��赫�����ڵĸ�Ŀ¼��
		}
		//2.�����ļ��ϴ�����ʱ·��
		File tempDir = new File("F:/temdir");
		if(!tempDir.exists()){
			tempDir.mkdirs();
		}
		
		//3.�жϱ��Ƿ�����enctype����
		if(ServletFileUpload.isMultipartContent(req)){
			
			DiskFileItemFactory itemFactory = new DiskFileItemFactory();
			itemFactory.setSizeThreshold(1024);// �趨��1KB�Ļ�����
			itemFactory.setRepository(tempDir);//�����ϴ��ļ�����ʱĿ¼
			
			ServletFileUpload servletfileupload = new ServletFileUpload(itemFactory);
			servletfileupload.setSizeMax(1024*1024*10); //�ϴ��ļ�������
			
			
			try {
				//��ȡ���е����б�Ԫ��(��ͨԪ��+�ļ�Ԫ��)����װ��FileItem����
				List<FileItem> fileItemList = servletfileupload.parseRequest(req);
				
				
				for(FileItem item:fileItemList){
					if(item.isFormField()){//����Ǳ��� �����Ƿ��ļ��ϴ�Ԫ��
						//��ͨԪ��
						System.out.println("FieldName:" + item.getFieldName());//��ȡname���Ե�ֵ
						System.out.println("getString:" + item.getString("UTF-8"));
					}else{
						 //�ļ�Ԫ��
                        System.out.println("FieldName:" + item.getFieldName()); //��ȡ����name���Ե�ֵ
                        System.out.println("Name:" + item.getName()); //��ȡ�ϴ��ļ���ԭʼ����(�ļ���)
                        //��ȡ�ļ���������
                        InputStream inputstream = item.getInputStream();
                        String filename = item.getName();
                        String newFileName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
                        
                        FileOutputStream outputstream = new  FileOutputStream(new File(savaDir,newFileName));
                        
                        IOUtils.copy(inputstream, outputstream);
                        outputstream.flush();
                        outputstream.close();
                        inputstream.close();
                        
                        
                        System.out.println(item.getName() + "  -> �ļ��ϴ��ɹ���");
					}
				}
				
				
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
			
			
			
		}else{
			throw new RuntimeException("form��enctype���������쳣");
		}
		
		
	}

}
