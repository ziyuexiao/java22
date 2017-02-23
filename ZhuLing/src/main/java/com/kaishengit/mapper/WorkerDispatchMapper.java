package com.kaishengit.mapper;

import com.kaishengit.pojo.Worker;
import com.kaishengit.pojo.WorkerDispatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/22.
 */
public interface WorkerDispatchMapper {
    void save(WorkerDispatch workerDispatch);

    void updateCost(@Param("totalprice") float totalprice,
                    @Param("precost") float precost,
                    @Param("lastcost") float lastcost,
                    @Param("id") Integer id);

    WorkerDispatch findWorkerDispatchBySerialnumber(String serialnumber);

    WorkerDispatch findWorkerDispatchByid(Integer id);

    List<WorkerDispatch> findWorkerDispatchByParam(Map<String, Object> searchParam);

    Long count();

    Long countBySearchParam(Map<String, Object> searchParam);

    void updateState(WorkerDispatch workerDispatch);
}
