package com.kaishengit.controller;

import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/3/21.
 */
@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message","Hello,SpringBoot!");
        String name = "jack";
        List<String> nameList = Arrays.asList("AA","BB","CC");

        model.addAttribute("name",name);
        model.addAttribute("nameList",nameList);
        model.addAttribute("userList",userService.findAll());
        return "hello";
    }
}
