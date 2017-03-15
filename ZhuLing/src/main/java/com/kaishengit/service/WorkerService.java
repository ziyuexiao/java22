package com.kaishengit.service;

import com.kaishengit.dto.WorkerDispatchDto;
import com.kaishengit.pojo.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by lenovo on 2017/2/18.
 */
public interface WorkerService {

    List<Worker> findAllWorkers();


    Worker findWorkerByid(Integer id);

    String saveNew(WorkerDispatchDto workerDispatchDto);

    WorkerDispatch findWorkerDispatchBySerialnumber(String serialnumber);

    List<WorkerDispatchDetail> findWorkerDispatchDetailByDispatchid(Integer id);

    List<WorkerDispatchDocs> findWorkerDispatchDocsByDispatchid(Integer id);

    InputStream downDocs(Integer id) throws IOException;

    WorkerDispatchDocs findWorkerDispatchDocsByid(Integer id);

    WorkerDispatch findWorkerDispatchByid(Integer id);

    void downZipFile(WorkerDispatch workerDispatch, ZipOutputStream zipOutputStream)throws IOException;

    List<WorkerDispatch> findWorkerDispatchBySearchParam(Map<String, Object> searchParam);

    Long count();

    Long countBySearchParam(Map<String, Object> searchParam);

    void changeRentState(Integer id);
}
