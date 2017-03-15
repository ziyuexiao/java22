package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDocs;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static org.springframework.http.MediaType.*;

/**
 * Created by lenovo on 2017/2/16.
 */
@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {
    @Autowired
    private DeviceService deviceService;

    /**
     * 加载显示租赁合同列表
     * @return
     */
    @GetMapping
    public String list() {
        return "device/rent/list";
    }
    @GetMapping("/load")
    @ResponseBody
    public DataTablesResult load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);

        List<DeviceRent> deviceRentList = deviceService.findDeviceRentByParam(searchParam);
        Long count = deviceService.countOfDeviceRent();

        return new DataTablesResult(draw,count,count,deviceRentList);//一个count是记录的总数，一个是过滤后的记录总数，不做查询时，两者是相等的

    }
    /**
     * 修改合同完成状态
     *
     */
    @PostMapping("/state/change")
    @ResponseBody
    public JsonResult changeRentState(Integer id){
        deviceService.changeRentState(id);
        return new JsonResult(JsonResult.SUCCESS);
    }

    /**
     * 新建租赁合同
     */
    @GetMapping("/new")
    public String newRent(Model model){
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "device/rent/new";
    }
    /**
     * 保存合同
     *
     */
    @PostMapping("/new")
    @ResponseBody
    public JsonResult saveContract(@RequestBody DeviceRentDto deviceRentDto){//一个类将一个json转换为一个对象
        try {
            String serialnumber = deviceService.saveContract(deviceRentDto);
            JsonResult jsonResult = new JsonResult();
            jsonResult.setData(serialnumber);
            jsonResult.setStatus(JsonResult.SUCCESS);
            return jsonResult;
        }catch (ServiceException e){
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }

    }

    /**
     * 根据设备id查找设备信息
     *
     */
    @GetMapping("/device.json")
    @ResponseBody
    public JsonResult deviceJson(Integer id) {
        Device device = deviceService.findDeviceById(id);
        if(device == null) {
            return new JsonResult(JsonResult.ERROR,"设备不存在");
        } else {
            return new JsonResult(device);
        }
    }
    /**
     * 根据流水号显示合同详情
     *
     */
    @GetMapping("/{serialnumber:\\d+}")
    public String showDeviceRent(@PathVariable String serialnumber,Model model){
        //根据序列号查询合同
        DeviceRent deviceRent = deviceService.findDeviceRentBySerialnumber(serialnumber);

        if (deviceRent == null){
            throw new NotFoundException();
        }else {
            //查询合同详情列
            List<DeviceRentDetail> detailList = deviceService.findDeviceRentDetailByRentid(deviceRent.getId());
            System.out.println(detailList);
            //查询上传合同文件列
            List<DeviceRentDocs> deviceRentDocsList = deviceService.findDeviceRentDocsByRentid(deviceRent.getId());
            System.out.println(detailList);

            model.addAttribute("deviceRent",deviceRent);
            model.addAttribute("detailList",detailList);
            model.addAttribute("deviceRentDocsList",deviceRentDocsList);

            return "device/rent/show";
        }
    }
    /**
     * 下载合同文件
     */
     /*@GetMapping("/doc")
    public void downloadFile(Integer id, HttpServletResponse response) throws IOException {
        InputStream inputStream = deviceService.downloadFile(id);
        if(inputStream == null) {
            throw new NotFoundException();
        } else {
            DeviceRentDoc doc = deviceService.findDeviceRentDocById(id);
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = doc.getSourceName();
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }*/
    @GetMapping("/doc")/*ResponseEntity为spring框架中的一个类*/
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        InputStream inputStream = deviceService.downloadFile(id);
        if (inputStream == null){
            throw new NotFoundException();
        }else {
           DeviceRentDocs rentDocs = deviceService.findDeviceRentDocsByid(id);
            String fileName = rentDocs.getSourcename();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));

            return new ResponseEntity<>(new InputStreamResource(inputStream),headers, HttpStatus.OK);

        }
    }

    /**
     *合同文件的打包下载
     */
    @GetMapping("/doc/zip")
    public void downloadZipFile(Integer id, HttpServletResponse response) throws IOException {
        //根据id查找对应的合同
        DeviceRent deviceRent = deviceService.findDeviceRentByid(id);
        if (deviceRent == null){
            throw new NotFoundException();
        }else {
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = deviceRent.getCompanyname()+".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            //使用装饰者模式对outputStream压缩打包
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            deviceService.downloadZipFile(deviceRent,zipOutputStream);
        }

    }


}
