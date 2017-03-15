package com.kaishengit;

/**
 * Created by lenovo on 2017/3/7.
 */

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.*;

/**
 * 流程变量设置
 * 流程变量用于改变流程条件，例如请假请多少天
 */
public class VariablesTest {

    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 启动流程实例的同时可以设置流程变量，Map集合
     * act_ru_variable会插入数据
     * act_ru_execution启动任务流程该表会变化
     */
    @Test
    public void startProcess(){
        Map<String,Object> variables = new HashMap<>();
        variables.put("请假天数",5);
        variables.put("startTime",new Date());

        ProcessInstance pi = engine.getRuntimeService()
                .startProcessInstanceByKey("myProcess",variables);//act_ge_property中找key值

        System.out.println("id: " +pi.getId());//12501
        System.out.println("name: " +pi.getName());//null
        System.out.println("key: " + pi.getProcessDefinitionKey() );//myProcess
    }
    /**
     * 查看任务列表
     */
    @Test
    public void getTaskList(){
        //使用执行对象的id和变量名称获取对应流程变量的值
        Integer days = (Integer) engine.getRuntimeService().getVariable("12501","请假天数");
        System.out.println("days:"+days);//5
        //使用执行对象的id，获取所有的流程变量
        Map<String,Object> map = engine.getRuntimeService().getVariables("12501");
        map.get("请假天数");
        map.get("startTime");
        System.out.println(map.get("请假天数"));//5
        System.out.println(map.get("startTime"));//Tue Mar 07 09:45:36 CST 2017

        //获取一部分的流程变量的值
        List<String> variableNames = new ArrayList<>();
        variableNames.add("请假天数");
        Map<String,Object> map1 = engine.getRuntimeService().getVariables("12501",variableNames);
        System.out.println("map1:"+map1.size());

    }
    /**
     * 在运行时设置流程变量
     */
    @Test
    public void setRuntimeVariables(){
        Map<String,Object> variables = new HashMap<>();
        variables.put("days1", 5);
        variables.put("startTime1", new Date());
        engine.getRuntimeService().setVariables("12501",variables);
    }
    /**
     * 针对任务插入流程变量
     */
    @Test
    public void setTaskVariables(){
        Map<String,Object> variables = new HashMap<>();
        variables.put("days2", 6);
        variables.put("startTime2", new Date());
        engine.getTaskService().setVariables("12506",variables);//表act_ru_task中的id
    }

    /**
     * runtimeService和taskService都能获取taskServic设置的值
     */
    @Test
    public void getTaskVariables(){
        Integer days = (Integer)engine.getRuntimeService().getVariable("12501", "days2");
        System.out.println("days:" + days );//6
        Integer days2 = (Integer)engine.getTaskService().getVariable("12506", "days2");
        System.out.println("days:" + days2 );//6
    }

}
