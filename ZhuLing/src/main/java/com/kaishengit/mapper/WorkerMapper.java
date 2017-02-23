package com.kaishengit.mapper;

import com.kaishengit.pojo.Worker;
import com.kaishengit.pojo.WorkerDispatch;

import java.util.List;

/**
 * Created by lenovo on 2017/2/18.
 */
public interface WorkerMapper {
    List<Worker> findAllWorkers();

    Worker findWorkerById(Integer id);


    void save(WorkerDispatch workerDispatch);

    void updateCurrnum(Worker worker);
}
