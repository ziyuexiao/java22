package com.kaishengit.controller;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;


/**
 * Created by lenovo on 2017/2/21.
 */
@Controller
@RequestMapping("/pan")
public class PanController {
    @Autowired
    private DiskService diskService;
    @RequestMapping
    public String list(
            @RequestParam(required = false,defaultValue = "0") Integer path,//defaultValue = "0"代表请求span的时候显示根目录
            Model model) {
        List<Disk> diskList = diskService.findByFid(path);
        model.addAttribute("diskList",diskList);
        model.addAttribute("fid",path);
        return "pan/list";
    }

    /**
     * 上传文件
     *
     */
    @PostMapping("/load")
    @ResponseBody
    public JsonResult saveFile(Integer fid, MultipartFile file){
        try {
            diskService.saveNewFile(fid,file);
            return new JsonResult(JsonResult.SUCCESS);
        }catch (ServiceException e){
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
    }
    /**
     * 建文件夹
     */
    @PostMapping("/folder/new")
    @ResponseBody
    public JsonResult saveFolder(Disk disk) {
        diskService.saveNewFolder(disk);
        return new JsonResult(JsonResult.SUCCESS);
    }
    /**
     * 下载文件
     *
     */
    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws FileNotFoundException {
        InputStream inputStream = diskService.downloadFile(id);
        Disk disk = diskService.findByid(id);

        String fileName = disk.getSourcename();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));

        return new ResponseEntity<>(new InputStreamResource(inputStream),headers, HttpStatus.OK);

    }
    /**
     * 删除
     */
    @GetMapping("/del/{id:\\d+}")
    @ResponseBody
    public JsonResult del(@PathVariable Integer id){
        diskService.delByid(id);
        return new JsonResult(JsonResult.SUCCESS);
    }
}
