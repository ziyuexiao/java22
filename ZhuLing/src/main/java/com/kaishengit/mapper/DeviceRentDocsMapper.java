package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDocs;

import java.util.List;

/**
 * Created by lenovo on 2017/2/17.
 */
public interface DeviceRentDocsMapper {
    void batchSave(List<DeviceRentDocs> rentDocsList);

    List<DeviceRentDocs> findDeviceRentDocsByRentid(Integer id);

    DeviceRentDocs findRentDocsByid(Integer docsid);
}
