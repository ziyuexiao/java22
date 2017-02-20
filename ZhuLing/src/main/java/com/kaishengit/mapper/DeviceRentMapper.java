package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/17.
 */
public interface DeviceRentMapper {

    void save(DeviceRent deviceRent);

    void updateCost(@Param("totalprice") float totalprice,
                    @Param("preCost")float preCost,
                    @Param("lastCost")float lastCost,
                    @Param("id")Integer id);

    DeviceRent findDeviceRentBySerialnumber(String serialnumber);

    DeviceRent findDeviceRentByid(Integer id);

    List<DeviceRent> findDeviceRentByParam(Map<String, Object> searchParam);

    Long count();

    void updateState(DeviceRent deviceRent);
}
