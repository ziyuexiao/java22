package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2017/1/13.
 */
@Controller
@RequestMapping("/setting/device")
public class DeviceController {
    @RequestMapping
    public String list(){
        return "setting/device/list";
    }
}
