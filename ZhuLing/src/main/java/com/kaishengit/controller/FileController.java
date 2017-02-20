package com.kaishengit.controller;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/17.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult fileUpload(MultipartFile file){
       try {
           String fileName = fileService.uploadFile(file.getOriginalFilename(),file.getContentType(),file.getInputStream());
           Map<String,String> map = new HashMap<>();
           map.put("newFileName",fileName);
           map.put("sourceFileName",file.getOriginalFilename());

           return new JsonResult(map);
       }catch (Exception e){
           e.printStackTrace();
           return new JsonResult(JsonResult.ERROR,e.getMessage());
       }
    }
}
