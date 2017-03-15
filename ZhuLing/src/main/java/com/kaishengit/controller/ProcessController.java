package com.kaishengit.controller;

import com.kaishengit.dto.Process;

import com.kaishengit.shiro.ShiroUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/9.
 */
@Controller
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;
    @RequestMapping("/apply")
    public String processApply(){
        return "activiti/process/processList";
    }
    @RequestMapping("/task/list")
    public String taskList(Model model){
        String userId = ShiroUtil.getCurrentUserId().toString();
        List<Task> taskList = new ArrayList<>();
        List<Process> processes = new ArrayList<>();
        //查看任务列表
        List<Task> todoList = taskService.createTaskQuery()
                .taskAssignee(userId)
                .orderByTaskCreateTime()
                .asc().list();

        List<Task> unSignedTask = taskService.createTaskQuery()
                .taskCandidateUser(userId)
                .orderByTaskCreateTime()
                .asc().list();

        taskList.addAll(todoList);
        taskList.addAll(unSignedTask);

        for (Task task : taskList){
            String processInstanceId = task.getProcessInstanceId();
            //获得历史流程实例
            HistoricProcessInstance historicActivityInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            //获得Activiti流程中的用户
            User actUser = identityService.createUserQuery().userId(historicActivityInstance.getStartUserId())
                    .singleResult();

            Process process = new Process();
            process.setUserName(actUser.getFirstName());
            process.setTask(task);
            process.setHistoricProcessInstance(historicActivityInstance);
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();

            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processInstance.getProcessDefinitionId())
                    .singleResult();

            process.setProcessDefinitionName(definition.getName());

            processes.add(process);
        }

        model.addAttribute("processes",processes);
        return "activiti/process/taskList";
    }

}
