package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/3/7.
 */
public class TaskGroupTest {
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
                .name("Task3")//添加部署的名称,写到表act_re_deployment
                .addClasspathResource("diagrams/TaskGroup3.bpmn")//
                .addClasspathResource("diagrams/TaskGroup3.png")//一次只能加载一个，部署流程资源文件BPMN/png
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
                .startProcessInstanceByKey("task3");//act_re_procdef中找key值


        System.out.println("id: " +pi.getId());//act_ge_property中的value，类似序列号，会用到，有变化
        System.out.println("name: " +pi.getName());//null
        System.out.println("key: " + pi.getProcessDefinitionKey() );//myProcess
    }
    /**
     *第三步 查看任务列表
     */
    @Test
    public void getTaskList(){
        String candidateUser = "A";
        List<Task> taskList = processEngine.getTaskService()
                .createTaskQuery()//创建任务查询对象
                //设置查询条件
                .taskCandidateUser(candidateUser)//指定的查询人
                .orderByTaskAssignee().desc()//
                .list();

        for(Task task:taskList){
            System.out.println("办理人:" + task.getAssignee());
            System.out.println("name:" + task.getName());
            System.out.println("流程定义ID:" + task.getProcessDefinitionId());
        }

    }
    /**
     * 领取任务
     */
    @Test
    public void claim(){
        processEngine.getTaskService().claim("52504","A");
        System.out.println("成功");
    }

}
