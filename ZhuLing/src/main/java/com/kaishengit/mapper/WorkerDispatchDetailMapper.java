package com.kaishengit.mapper;

import com.kaishengit.pojo.WorkerDispatchDetail;

import java.util.List;

/**
 * Created by lenovo on 2017/2/22.
 */

public interface WorkerDispatchDetailMapper {
    void batchSave(List<WorkerDispatchDetail> workerDispatchDetailList);

    List<WorkerDispatchDetail> findWorkerDispatchDetailByDispatchid(Integer dispatchid);
}
