package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2017/2/23.
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @RequestMapping("/dayreport")
    public String dayreport(){
        return "finance/report/day";
    }
}
