package com.kaishengit.service;

import com.kaishengit.pojo.Device;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/14.
 */
public interface DeviceService {
    void savaDevice(Device device);

    List<Device> findAllDevice();

    List<Device> findByPage(String start, String length);

    Long count();

    List<Device> findDeviceBySearchParam(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String, Object> searchParam);

    void delDevice(Integer id);
}
