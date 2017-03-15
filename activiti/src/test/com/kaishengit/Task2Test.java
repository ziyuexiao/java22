package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/7.
 */
public class Task2Test {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    /*
	 * 第一步：使用部署流程
	 * 数据库操作4张表act_re_deployment（部署对象表）
	 * act_ge_property(默认插入三条数据)
	 *act_re_procdef(流程定义表)
	 * act_ge_bytearray(资源文件表)
	 * **/
    @Test
    public void deployProcess(){
        Deployment deployment = processEngine.getRepositoryService()//获取repositoryservice
                .createDeployment()//创建一个部署对象
                .name("Task2")//添加部署的名称,写到表act_re_deployment
                .addClasspathResource("diagrams/Task2.bpmn")//
                .addClasspathResource("diagrams/Task2.png")//一次只能加载一个，部署流程资源文件BPMN/png
                .deploy();//完成部署



        System.out.println("name: "+ deployment.getName());
        System.out.println("id: "+ deployment.getId());

    }
    /**
     * 第二步 启动流程
     * act_hi_actinst
     * act_hi_identitylink
     * act_hi_procinst
     * act_hi_taskinst
     * act_ru_execution
     * act_ru_identitylink
     * act_ru_task
     */
    @Test
    public void startProcess(){

        //通过设置申请人获取申请人的部门经理id

        ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceByKey("task2");//act_re_procdef中找key值


        System.out.println("id: " +pi.getId());//act_ge_property中的value，类似序列号，会用到，有变化
        System.out.println("name: " +pi.getName());//null
        System.out.println("key: " + pi.getProcessDefinitionKey() );//myProcess
    }

}
