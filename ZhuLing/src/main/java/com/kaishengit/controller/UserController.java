package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.db.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by lenovo on 2017/1/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
/*@RequestParam从URL或者表单中获得值，该参数不是必须的*/
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1") Integer p,
                       @RequestParam(required = false,defaultValue = "",name = "q_name") String queryName,
                       @RequestParam(required = false,defaultValue = "",name = "q_role") String queryRole,
                                   Model model) throws UnsupportedEncodingException {
        if (StringUtils.isNotEmpty(queryName)){
            queryName = new String(queryName.getBytes("ISO8859-1"),"UTF-8");
        }

        Page<User> page = userService.findByPageNuAndParam(p,queryName,queryRole);//userService.findByPageNu(p);
        List<Role> roleList = userService.findAllRole();
        model.addAttribute("roleList",roleList);
        model.addAttribute("page",page);
        /*将值传入前端页面，使搜索框的值不是空白*/
        model.addAttribute("queryName",queryName);
        model.addAttribute("queryRole",queryRole);

        return "user/list";
    }

    @RequestMapping(value = "/new",method  = RequestMethod.GET)
    public String rolelist(Model model){
        List<Role> roleList = userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "user/new";
    }
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveUser(User user,Integer[] roleIds, RedirectAttributes redirectAttributes){
        userService.save(user,roleIds);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/user";
    }
    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String delUser(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        userService.delUser(id);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/user";
    }
    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id,Model model) {
        User user = userService.findUserById(id);
        if(user == null) {
            throw new NotFoundException();
        } else {
            List<Role> roleList = userService.findAllRole();
            model.addAttribute("roleList",roleList);
            model.addAttribute("user",user);
            return "user/edit";
        }
    }
    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.POST)
    public String editUser(User user,Integer[] roleIds,RedirectAttributes redirectAttributes) {
        userService.editUser(user,roleIds);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/user";
    }


}
