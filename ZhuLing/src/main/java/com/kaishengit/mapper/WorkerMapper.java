package com.kaishengit.mapper;

import com.kaishengit.pojo.Worker;

import java.util.List;

/**
 * Created by lenovo on 2017/2/18.
 */
public interface WorkerMapper {
    List<Worker> findAllWorkers();

    Worker findWorkerById(Integer id);
}
