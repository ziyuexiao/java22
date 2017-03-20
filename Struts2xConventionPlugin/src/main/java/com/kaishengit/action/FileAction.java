package com.kaishengit.action;

import java.io.*;

/**
 * Created by lenovo on 2017/3/20.
 */
public class FileAction extends BaseAction {
    private String uName;//文本框输入的名字
    /*private String docFileName;//选择上传文件的名字
    private String  docContentType;//上传文件的MIMI类型
    private File doc;//要上传的文件对象*/
    //多文件上传
    private File[] doc;
    private String[] docFileName;
    private String[] docContentType;
    //文件下载使用
    private String fileName;
    private Long size;
    private String fileContentType;
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
    public String upload(){
        System.out.println(uName);
        for(int i = 0;i < doc.length;i++) {
            System.out.println("FileName:" + docFileName[i]);
            System.out.println("ContentType:" + docContentType[i]);
        }
        return SUCCESS;
    }
    public String download() throws UnsupportedEncodingException {
        fileName = new String("书籍封面.jpg".getBytes("UTF-8"),"ISO8859-1");
        size = new File("F:/img/67dd74e0gw1f8og0rdfklj20b40got9k.jpg").length();
        fileContentType = "image/jpg";
        return SUCCESS;
    }
    /*指定文件下载输入流*/
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(new File("F:/img/67dd74e0gw1f8og0rdfklj20b40got9k.jpg"));
    }

    //get set

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    /*public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    public String getDocContentType() {
        return docContentType;
    }

    public void setDocContentType(String docContentType) {
        this.docContentType = docContentType;
    }

    public File getDoc() {
        return doc;
    }

    public void setDoc(File doc) {
        this.doc = doc;
    }*/

    public File[] getDoc() {
        return doc;
    }

    public void setDoc(File[] doc) {
        this.doc = doc;
    }

    public String[] getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String[] docFileName) {
        this.docFileName = docFileName;
    }

    public String[] getDocContentType() {
        return docContentType;
    }

    public void setDocContentType(String[] docContentType) {
        this.docContentType = docContentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}
