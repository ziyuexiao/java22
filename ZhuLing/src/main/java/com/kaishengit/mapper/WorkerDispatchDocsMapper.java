package com.kaishengit.mapper;

import com.kaishengit.pojo.WorkerDispatchDocs;

import java.util.List;

/**
 * Created by lenovo on 2017/2/22.
 */
public interface WorkerDispatchDocsMapper {
    void batchSave(List<WorkerDispatchDocs> dispatchDocsList);

    List<WorkerDispatchDocs> findWorkerDispatchDocsByDispatchid(Integer dispatchid);

    WorkerDispatchDocs findDispatchDocsByid(Integer id);
}
