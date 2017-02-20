package com.kaishengit.service;

import java.io.InputStream;

/**
 * Created by lenovo on 2017/2/17.
 */
public interface FileService {
    String uploadFile(String originalFileName,String contentType, InputStream inputStream);
}
