package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDetail;

import java.util.List;

/**
 * Created by lenovo on 2017/2/17.
 */
public interface DeviceRentDetailMapper {
    void batchSave(List<DeviceRentDetail> detailList);

    List<DeviceRentDetail> findDeviceRentDetailByRentid(Integer rentid);
}
