package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.dto.WorkerDispatchDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.*;
import com.kaishengit.service.FileService;
import com.kaishengit.service.WorkerService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by lenovo on 2017/2/18.
 */
@Controller
@RequestMapping("/worker/dispatch")
public class WorkerDispatchController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private FileService fileService;

    @GetMapping
    public String list(){
        return "worker/dispatch/list";
    }

    /**
     * 加载首页数据
     * @param request
     * @return
     */
    @GetMapping("/loading")
    @ResponseBody
    public Map<String,Object> loading(HttpServletRequest request){
        /*第几次向服务端发出请求*/
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");



        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);


        List<WorkerDispatch> workerDispatchList = workerService.findWorkerDispatchBySearchParam(searchParam);
        Long count = workerService.count();

        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",count);//记录的总数
        result.put("recordsFiltered",count);//过滤后的总记录数,做查询的时候用到
        result.put("data",workerDispatchList);//该页数据
        return result;

    }
    /**
     * 修改合同完成状态
     */
    @PostMapping("/state/change")
    @ResponseBody
    public JsonResult changeRentState(Integer id){
        workerService.changeRentState(id);
        return new JsonResult(JsonResult.SUCCESS);
    }

    /**
     * 添加劳务外包合同
     * @return
     */
    @GetMapping("/add")
    public String addWorkerDispatch(Model model){
        List<Worker> workerList = workerService.findAllWorkers();
        model.addAttribute("workerList",workerList);
        return "worker/dispatch/add";
    }
    /**
     * 根据id查询选择的工种，并传送给客户端
     */
    @GetMapping("/worker.json")
    @ResponseBody
    public JsonResult Worker(Integer id){
        Worker worker = workerService.findWorkerByid(id);
        if (id==null){
            return new JsonResult(JsonResult.ERROR,"没有该工种");
        }else {
            return new JsonResult(worker);
        }
    }
    /**
     * 上传合同扫描件
     */
    @PostMapping("/upload")
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
    /**
     * 保存新合同
     */
    @PostMapping("/new")
    @ResponseBody
    public JsonResult saveNew(@RequestBody WorkerDispatchDto workerDispatchDto){
        try {
            String serialnumber = workerService.saveNew(workerDispatchDto);
            JsonResult jsonResult = new JsonResult();
            jsonResult.setData(serialnumber);
            jsonResult.setStatus(JsonResult.SUCCESS);
            return jsonResult;
        }catch (ServiceException e){
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
    }
    /**
     * 根据流水号找到相应的派遣合同
     */
    @GetMapping("/{serialnumber:\\d+}")
    public String showWorkerDis(@PathVariable String serialnumber,Model model){
        //根据序列号查询派遣合同
        WorkerDispatch workerDispatch = workerService.findWorkerDispatchBySerialnumber(serialnumber);
        if(workerDispatch == null){
            throw new NotFoundException();
        }else {
            //查询派遣的详情
            List<WorkerDispatchDetail> detailList = workerService.findWorkerDispatchDetailByDispatchid(workerDispatch.getId());
            //查询上传的合同附件
            List<WorkerDispatchDocs> docsList = workerService.findWorkerDispatchDocsByDispatchid(workerDispatch.getId());

            model.addAttribute("workerDispatch",workerDispatch);
            model.addAttribute("detailList",detailList);
            model.addAttribute("docsList",docsList);

            return "worker/dispatch/show";
        }

    }

    /**
     * 合同下载
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/doc")/*ResponseEntity为spring框架中的一个类*/
    @ResponseBody
    public ResponseEntity<InputStreamResource> downDocs(Integer id) throws IOException {
        InputStream inputStream = workerService.downDocs(id);
        if (inputStream == null){
            throw new NotFoundException();
        }else {
            WorkerDispatchDocs dispatchDocs = workerService.findWorkerDispatchDocsByid(id);
            String fileName = dispatchDocs.getSourcename();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));

            return new ResponseEntity<>(new InputStreamResource(inputStream),headers, HttpStatus.OK);

        }
    }
    /**
     * 打包下载
     */
    @GetMapping("/doc/zip")
    public void downZipFile(Integer id, HttpServletResponse response) throws IOException {
        //根据id查找对应的合同
        WorkerDispatch workerDispatch = workerService.findWorkerDispatchByid(id);
        if (workerDispatch == null){
            throw new NotFoundException();
        }else {
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = workerDispatch.getCompanyname()+".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            //使用装饰者模式对outputStream压缩打包
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            workerService.downZipFile(workerDispatch,zipOutputStream);
        }

    }

}
