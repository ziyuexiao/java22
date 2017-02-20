package com.kaishengit.service.impl;

import com.kaishengit.mapper.WorkerMapper;
import com.kaishengit.pojo.Worker;
import com.kaishengit.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/2/18.
 */
@Service
public class WorkerServiceImpl implements WorkerService{
    private Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);
    @Autowired
    private WorkerMapper workerMapper;
    @Override
    public List<Worker> findAllWorkers() {
        return workerMapper.findAllWorkers();
    }

    @Override
    public Worker findWorkerByid(Integer id) {
        return workerMapper.findWorkerById(id);
    }
}
