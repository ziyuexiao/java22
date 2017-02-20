package com.kaishengit.service;

import com.kaishengit.pojo.Worker;

import java.util.List;

/**
 * Created by lenovo on 2017/2/18.
 */
public interface WorkerService {

    List<Worker> findAllWorkers();


    Worker findWorkerByid(Integer id);
}
