package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * Created by lenovo on 2017/3/6.
 */
public class ActivitiEngine {
    /**
     *创建引擎 初始化数据库
     */
    @Test
    public void initDatabases(){
        ProcessEngineConfiguration process =
                ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //根据set方法修改连接数据库配置
        process.setJdbcDriver("com.mysql.jdbc.Driver");
        process.setJdbcUrl("jdbc:mysql:///db_activiti");
        process.setJdbcUsername("root");
        process.setJdbcPassword("root");

        /*public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表存在
        public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表在创建表
        public static final String DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表*/

        process.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);//创建表
        //工作流的核心对象
        ProcessEngine processEngine = process.buildProcessEngine();
        System.out.println("processEngine:"+processEngine);

    }
    @Test
    public void initDatabases2(){
        //创建名为activiti.cfg.xml文件，并通过ProcessEngineConfiguration加载ProcessEngine，初始化数据库
        ProcessEngine processEngine =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
    }
    @Test
    public void initDatabases3(){
        //调用ProcessEngines的getDefaultProceeEngine方法时会自动加载classpath下名为activiti.cfg.xml文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    }


}
