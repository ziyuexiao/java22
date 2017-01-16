package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/13.
 */
@Controller
@RequestMapping("/setting/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @RequestMapping
    public String list(){

        return "setting/device/list";
    }

    @PostMapping("/load")
    @ResponseBody/*ajax请求会接收有返回值,HttpServletRequest用来获取客户端奇怪参数如order[0][column]
*/
    /*返回的是json对象*/
    public Map<String,Object> load(HttpServletRequest request){
        /*第几次向服务端发出请求*/
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        /*第几列参与排序*/
        String orderIndex = request.getParameter("order[0][column]");
        /*排序方式*/
        String orderType = request.getParameter("order[0][dir]");
        /*排序的列名字*/
        String orderColumn = request.getParameter("columns["+orderIndex+"][name]");

        String deviceName = request.getParameter("deviceName");

        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);
        searchParam.put("orderType",orderType);
        searchParam.put("orderColumn",orderColumn);
        searchParam.put("deviceName",deviceName);

        List<Device> deviceList = deviceService.findDeviceBySearchParam(searchParam);
        Long count = deviceService.count();
        Long filteredCount = deviceService.countBySearchParam(searchParam);
        /*获取当前页数据*//*
        List<Device> deviceList = deviceService.findByPage(start,length);
        Long count = deviceService.count();
*/
        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",count);//记录的总数
        result.put("recordsFiltered",filteredCount);//过滤后的总记录数
        result.put("data",deviceList);//该页数据
        return result;

    }



    @RequestMapping(value = "/adddevice",method = RequestMethod.GET)
    public String adddevice(){
        return "setting/device/add";
    }
    @RequestMapping(value = "/adddevice",method = RequestMethod.POST)
    public String add(Device device, RedirectAttributes redirectAttributes){

        deviceService.savaDevice(device);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/setting/device";
    }


    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public String delDevice(@PathVariable Integer id) {
        deviceService.delDevice(id);
        return "success";
    }


}
