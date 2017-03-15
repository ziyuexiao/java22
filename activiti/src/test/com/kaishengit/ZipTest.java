package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by lenovo on 2017/3/6.
 */


public class ZipTest {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    //获取zip文件的输入流
    /**
     * zip格式部署
     */
    @Test
    public void deployZip(){
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("diagrams/Hello.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deploy = engine.getRepositoryService()
                .createDeployment()
                .name("zipDeploy")
                .addZipInputStream(zipInputStream)
                .deploy();

        System.out.println("流程id：" + deploy.getId());
        System.out.println("流程name" + deploy.getName());

    }
    /**
     * 获取流程定义
     * 查询的是表act_re_procdef
     *
     * */
    @Test
    public void getProcessDef(){

        List<ProcessDefinition> processDefList =  engine.getRepositoryService()
                .createProcessDefinitionQuery()//创建查询query对象
                //封装查询条件
                .processDefinitionKey("myProcess")
                .list();

        System.out.println("lsit size :" + processDefList.size());

        for(ProcessDefinition def:processDefList){

            System.out.println("def id:" + def.getId());
            System.out.println("def name:" + def.getName());
            System.out.println("def version:" + def.getVersion());
        }


    }
    /**
     * 删除流程定义，启动的流程无法删除
     *act_re_procdef
     * act_re_deployment
     * */
    @Test
    public void delProcessDef(){
        engine.getRepositoryService().deleteDeployment("7501");
        System.out.println("success!");

    }
    /**
     * 获取流程定义PNG
     * @throws IOException
     *
     * */
    @Test
    public void getProcessDefPng() throws IOException {
        String defId = "myProcess:2:10004";
        ProcessDefinition def= engine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionId(defId)
                .singleResult();
        String deployId = def.getDeploymentId();
        String resourceName = def.getDiagramResourceName();

        System.out.println("id: " + deployId +" name:" +resourceName);
        InputStream input = engine.getRepositoryService()
                .getResourceAsStream(deployId, resourceName);

        FileUtils.copyInputStreamToFile(input, new File("F:/Hello.png"));

    }
}
