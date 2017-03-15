package com.kaishengit.service.impl;

import com.kaishengit.mapper.LeaveMapper;
import com.kaishengit.pojo.Leave;
import com.kaishengit.pojo.User;
import com.kaishengit.service.LeaveWorkFlowService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/9.
 */
@Service
public class LeaveWorkFlowServiceImpl implements LeaveWorkFlowService {
    @Autowired
    LeaveMapper leaveMapper;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Override
    @Transactional//事务
    public void processStart(Leave leave, User user, String processDefKey, Map<String, Object> variables) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //封装leave请假对象并入库
        //Task task = leave.getTask();
        //leave.setProcessInstanceId(task.getProcessInstanceId());
        System.out.println(leave.getProcessInstanceId()+0000000000000);
        leave.setUserId(user.getId().toString());
        leave.setApplyTime(dateFormat.format(new Date()));
        leaveMapper.save(leave);

        //保存流程定义中的activiti:initiator的applyUserId，引擎会自动把用户ID保存到activiti:initiator中，initiator是发起人
        identityService.setAuthenticatedUserId(user.getId().toString());

        //启动流程 ProcessInstance流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefKey,leave.getId().toString(),variables);
        leave.setProcessInstanceId(instance.getProcessInstanceId());
        leaveMapper.update(leave);


    }
}
