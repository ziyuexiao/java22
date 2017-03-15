package com.kaishengit.service.impl;



import com.kaishengit.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.UUID;


/**
 * Created by lenovo on 2017/2/17.
 */
@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${upload.path}")//读取配置文件中的内容
    private String filePath;


    @Override
    public String uploadFile(String originalFileName, String contentType, InputStream inputStream) {
        //文件上传后存放的路径
        File savaDir = new File(filePath);
        System.out.println(filePath);
        if(!savaDir.exists()){
            savaDir.mkdirs();// 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        }
        System.out.println(originalFileName);
        //文件上传后的新名字
        String newFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        //将新名字上传到指定空间
        File file = new File(savaDir,newFileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            return newFileName;

        } catch (IOException e) {
            logger.error("文件上传异常",e);
            throw new RuntimeException("文件上传异常",e);
        }

    }
}
