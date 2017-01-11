package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Account;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by lenovo on 2017/1/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    //@RequestMapping("/add")
    //@GetMapping("/add")  // >= 4.3
   /* @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "user/add";
    }*/

   /* @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save(Account account, String address) {
        System.out.println(account.getUserName() + ","+account.getAge()+","+address+" save success!");
        return "redirect:/home";
    }*/
    /*@RequestMapping(method = RequestMethod.GET)
    public String showUser(int id) {
        System.out.println("id:" + id);
        return "redirect:/home";
    }*/

    /*@RequestMapping(value = "/{id:\\d{3,}}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id) {
        System.out.println("PATH ID:" + id);
        return "redirect:/home";
    }*/

    /*@RequestMapping(value = "/{id:\\d{3,}}/{types}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id,
                           @PathVariable (name = "types") String type,
                           Model model){
        System.out.println("ID:" + id + " Type:" + type);
        model.addAttribute("id",id);
        model.addAttribute("type",type);
        return "user/show";
    }*/

    /*@RequestMapping(value = "/{id:\\d{3,}}/{types}",method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable Integer id,
                                 @PathVariable (name = "types") String type
                                 ){
        System.out.println("ID:" + id + " Type:" + type);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("id",id);
        modelAndView.addObject("type",type);
        modelAndView.setViewName("user/show");
        return new ModelAndView("user/show","id",id);
    }*/

    @GetMapping
    public String list(Model model){
        Account account = new Account();
        account.setUserName("tom");
        account.setAge(21);

        Account account1 = new Account();
        account1.setUserName("james");
        account1.setAge(36);

        List<Account> accountList = Arrays.asList(account,account1);
        model.addAttribute("accountList",accountList);

        return "user/list";
    }
    @GetMapping("/new")
    public String newUser(){
        return "user/add";
    }
    @PostMapping("/new")
    public String saveUser(Account account,
                           RedirectAttributes redirectAttributes){
        System.out.println(account);
        redirectAttributes.addFlashAttribute("message","添加成功");

        return "redirect:/user";

    }

    @GetMapping(value = "/check/{username}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public  String checkUserName(@PathVariable String username) {
        if(username.equals("jack")) {
            return "不可用";
        } else {
            return "可以用";
        }
    }

    @RequestMapping(value = "/aa/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public Account findById(@PathVariable Integer id) {
        Account account = new Account();
        account.setUserName("李四");
        account.setAge(23);
        return account;
    }

    /*自动注入原来servlet里内容*/
    @RequestMapping("/http")
    public String servlet(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "redirect:/user";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String fileUpload() {
        return "user/upload";
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String file(String name, MultipartFile file){
        System.out.println("Name:" + name);
        if(!file.isEmpty()) {
            //上传表单控件的name属性值
            System.out.println("getName:" + file.getName());
            //上传文件的原始名称
            System.out.println("getOriginalFilename:" + file.getOriginalFilename());
            //文件大小（字节）
            System.out.println("size: " + file.getSize());
            //文件的MIME类型
            System.out.println("ContentType:" + file.getContentType());

            //1,文件上传后存放的路径
            File savaDir = new File("F:/upload");
            if(!savaDir.exists()){
                savaDir.mkdirs();// 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
            }
            //2.设置文件上传的临时路径
            File tempDir = new File("F:/temdir");
            if(!tempDir.exists()){
                tempDir.mkdirs();
            }
            try {
                InputStream inputstream = file.getInputStream();
                String filename = file.getOriginalFilename();

                String newFileName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));

                FileOutputStream outputstream = new  FileOutputStream(new File(savaDir,newFileName));
                IOUtils.copy(inputstream, outputstream);
                outputstream.flush();
                outputstream.close();
                inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "redirect:/user";
    }

    @RequestMapping("/u-{id:\\d+}")
    public String showUser(@PathVariable Integer id) {
        if(id == 100) {
            throw new NotFoundException();
        }
        return "redirect:/user";
    }



}
