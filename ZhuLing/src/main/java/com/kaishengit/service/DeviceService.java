package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDocs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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

    Device findDeviceById(Integer id);

    String saveContract(DeviceRentDto deviceRentDto);

    DeviceRent findDeviceRentBySerialnumber(String serialnumber);

    List<DeviceRentDetail> findDeviceRentDetailByRentid(Integer id);

    List<DeviceRentDocs> findDeviceRentDocsByRentid(Integer id);

    InputStream downloadFile(Integer id) throws IOException, IOException;

    DeviceRentDocs findDeviceRentDocsByid(Integer id);

    DeviceRent findDeviceRentByid(Integer id);

    void downloadZipFile(DeviceRent deviceRent, ZipOutputStream zipOutputStream) throws IOException;

    List<DeviceRent> findDeviceRentByParam(Map<String, Object> searchParam);

    Long countOfDeviceRent();

    void changeRentState(Integer id);
}
