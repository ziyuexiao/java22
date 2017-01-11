package com.kaishengit.controller;

import com.kaishengit.pojo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lenovo on 2017/1/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    //@RequestMapping("/add")
    //@GetMapping("/add")  // >= 4.3
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save(Account account, String address) {
        System.out.println(account.getUserName() + ","+account.getAge()+","+address+" save success!");
        return "redirect:/home";
    }
    /*@RequestMapping(method = RequestMethod.GET)
    public String showUser(int id) {
        System.out.println("id:" + id);
        return "redirect:/home";
    }*/

    @RequestMapping(value = "/{id:\\d{3,}}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id) {
        System.out.println("PATH ID:" + id);
        return "redirect:/home";
    }

    /*@RequestMapping(value = "/{id:\\d{3,}}/{types}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id,
                           @PathVariable (name = "types") String type,
                           Model model){
        System.out.println("ID:" + id + " Type:" + type);
        model.addAttribute("id",id);
        model.addAttribute("type",type);
        return "user/show";
    }*/

    @RequestMapping(value = "/{id:\\d{3,}}/{types}",method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable Integer id,
                                 @PathVariable (name = "types") String type
                                 ){
        System.out.println("ID:" + id + " Type:" + type);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("id",id);
        modelAndView.addObject("type",type);
        modelAndView.setViewName("user/show");
        return new ModelAndView("user/show","id",id);
    }

}
