package com.kaishengit.controller;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.pojo.Worker;
import com.kaishengit.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2017/2/18.
 */
@Controller
@RequestMapping("/worker/dispatch")
public class WorkerDispatchController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public String list(){
        return "worker/dispatch/list";
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
}
