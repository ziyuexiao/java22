package com.kaishengit.service;

import com.kaishengit.pojo.Leave;
import com.kaishengit.pojo.User;

import java.util.Map;

/**
 * Created by lenovo on 2017/3/9.
 */
public interface LeaveWorkFlowService {

    void processStart(Leave leave, User user, String processDefKey, Map<String, Object> variables);
}
