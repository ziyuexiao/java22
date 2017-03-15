package com.kaishengit.controller;

import com.kaishengit.pojo.Leave;
import com.kaishengit.pojo.User;
import com.kaishengit.service.LeaveWorkFlowService;
import com.kaishengit.shiro.ShiroUtil;
import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/9.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
    Logger logger = LoggerFactory.getLogger(LeaveController.class);
    @Autowired
    private LeaveWorkFlowService leaveWorkFlowService;
    @RequestMapping("/apply")
    public String leaveApply(){
        return "activiti/leave/leaveApply";
    }
    @RequestMapping("/start")
    public String leaveStart(Leave leave, Model model){
        //流程启动
        User user = ShiroUtil.getCurrentUser();
        String processDefKey = "leaveProcess";
        Map<String,Object> variables = new HashMap<>();
        //todo 模拟找到了上级ID
        String  upperId = "1";
        variables.put("deptLeaderUserId",upperId);

        try {

            leaveWorkFlowService.processStart(leave, user, processDefKey, variables);
            model.addAttribute("message","流程启动成功");
        }catch(ActivitiException e){
            logger.error("KEY为{}：流程启动异常",processDefKey);
            model.addAttribute("message","流程启动失败");
        }


        return "activiti/leave/leaveApply";
    }
}
