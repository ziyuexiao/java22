package com.kaishengit.mapper;

import com.kaishengit.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/14.
 */
public interface DeviceMapper {


    void saveNewDevice(Device device);

    List<Device> findAllDevice();

    List<Device> findByPage(@Param("start") String start,@Param("length") String length);

    Long count();


    List<Device> findBySeachParam(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String, Object> searchParam);

    void del(Integer id);

    Device findDeviceByid(Integer id);

    void updateCurrNum(Device device);
}
