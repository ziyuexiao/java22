package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.*;
import com.kaishengit.pojo.*;
import com.kaishengit.service.DeviceService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.util.db.SerialNumberUtil;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lenovo on 2017/1/14.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    private Logger logger = LoggerFactory.getLogger( DeviceServiceImpl.class);
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceRentMapper rentMapper;
    @Autowired
    private DeviceRentDetailMapper rentDetailMapper;
    @Autowired
    private DeviceRentDocsMapper rentDocsMapper;
    @Autowired
    private FinanceMapper financeMapper;
    @Value("${upload.path}")
    private String fileSavePath;

    @Override
    public void savaDevice(Device device) {
        //让当前库存数量和总数量相同
        device.setDevicenum(device.getDevicetotal());
        deviceMapper.saveNewDevice(device);

        logger.info("{}添加了新设备{}", ShiroUtil.getCurrentUserName(),device.getDevicename());
    }

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAllDevice();
    }

    @Override
    public List<Device> findByPage(String start, String length) {
        return deviceMapper.findByPage(start,length);
    }

    @Override
    public Long count() {
        return deviceMapper.count();
    }

    @Override
    public List<Device> findDeviceBySearchParam(Map<String, Object> searchParam) {
        return deviceMapper.findBySeachParam(searchParam);
    }

    @Override
    public Long countBySearchParam(Map<String, Object> searchParam) {
        return deviceMapper.countBySearchParam(searchParam);
    }

    @Override
    public void delDevice(Integer id) {
        deviceMapper.del(id);
    }

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findDeviceByid(id);
    }

    @Override
    @Transactional//添加事务管理
    public String saveContract(DeviceRentDto deviceRentDto) {
        //1,保存合同
        DeviceRent deviceRent = new DeviceRent();
        deviceRent.setCompanyname(deviceRentDto.getCompanyName());
        deviceRent.setAddress(deviceRentDto.getAddress());
        deviceRent.setLinkman(deviceRentDto.getLinkMan());
        deviceRent.setCardnum(deviceRentDto.getCardNum());
        deviceRent.setTelnum(deviceRentDto.getTel());
        deviceRent.setBackdate(deviceRentDto.getBackDate());
        deviceRent.setRentdate(deviceRentDto.getRentDate());
        deviceRent.setFax(deviceRentDto.getFax());
        deviceRent.setCreateuser(ShiroUtil.getCurrentUserName());
        deviceRent.setTotalday(deviceRentDto.getTotalDate());
        deviceRent.setTotalprice(0F);//设置初始值
        deviceRent.setPrecost(0F);
        deviceRent.setLastcost(0F);
        deviceRent.setSerialnumber(SerialNumberUtil.getSerialNumber());

       rentMapper.save(deviceRent);
       //2,保存租赁设备合同详情页
        List<DeviceRentDto.DeviceArrayBean> deviceArrayBeanList = deviceRentDto.getDeviceArray();
        List<DeviceRentDetail> detailList = Lists.newArrayList();//来自于google的集合
        float totalprice = 0F;
        for (DeviceRentDto.DeviceArrayBean bean : deviceArrayBeanList){
            //查询当前设备数量是否足够
            Device device = deviceMapper.findDeviceByid(bean.getId());
            if (device.getDevicenum()<bean.getDevicenum()){
                throw new ServiceException(device.getDevicename()+"数量不足");
            }else {
                device.setDevicenum(device.getDevicenum()-bean.getDevicenum());
                deviceMapper.updateCurrNum(device);
            }


            DeviceRentDetail deviceRentDetail = new DeviceRentDetail();
            deviceRentDetail.setDename(bean.getDevicename());
            deviceRentDetail.setDeprice(bean.getDeviceprice());
            deviceRentDetail.setDeunit(bean.getDeviceunit());
            deviceRentDetail.setDenum(bean.getDevicenum());
            deviceRentDetail.setTotalprice(bean.getTotalprice());
            deviceRentDetail.setRentid(deviceRent.getId());

            detailList.add(deviceRentDetail);

            totalprice +=bean.getTotalprice();
        }
        if(!detailList.isEmpty()){
            rentDetailMapper.batchSave(detailList);
        }
        //计算价钱
        totalprice = totalprice*deviceRent.getTotalday();
        float preCost = totalprice*0.3F;
        float lastCost = totalprice-preCost;
        rentMapper.updateCost(totalprice,preCost,lastCost,deviceRent.getId());
        //3，保存上传的文件
        List<DeviceRentDto.DocsBean> docsBeans = deviceRentDto.getFileArray();
        List<DeviceRentDocs> rentDocsList = Lists.newArrayList();
        for(DeviceRentDto.DocsBean docs : docsBeans){
            System.out.println(docs);
            DeviceRentDocs rentDocs = new DeviceRentDocs();

            //rentDocs.setId(deviceRent.getId());
            rentDocs.setSourcename(docs.getSourcename());
            rentDocs.setNewname(docs.getNewname());
            rentDocs.setRentid(deviceRent.getId());

            rentDocsList.add(rentDocs);
        }
        if(!rentDocsList.isEmpty()){
            rentDocsMapper.batchSave(rentDocsList);
        }
        //4,写入流水号
        Finance finance = new Finance();
        finance.setCreateuser(ShiroUtil.getCurrentUserName());
        finance.setType(Finance.TYPE_IN);
        finance.setCreatedate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setModule("设备租赁");
        finance.setMoney(preCost);
        finance.setSerialnumber(SerialNumberUtil.getSerialNumber());
        finance.setState(Finance.STATE_NO);
        finance.setMark("预付款");
        finance.setModuleserialnumber(deviceRent.getSerialnumber());

        financeMapper.save(finance);


        return deviceRent.getSerialnumber();


    }

    /*根据流水号查租赁合同*/
    @Override
    public DeviceRent findDeviceRentBySerialnumber(String serialnumber) {
        return rentMapper.findDeviceRentBySerialnumber(serialnumber);
    }

    /*根据租赁合同id查询合同详情*/
    @Override
    public List<DeviceRentDetail> findDeviceRentDetailByRentid(Integer id) {
        return rentDetailMapper.findDeviceRentDetailByRentid(id);
    }

    /*根据租赁合同id查询上传的合同文件*/
    @Override
    public List<DeviceRentDocs> findDeviceRentDocsByRentid(Integer id) {
        return rentDocsMapper.findDeviceRentDocsByRentid(id);
    }

    /*根据id下载文件*/
    @Override
    public InputStream downloadFile(Integer docsid) throws IOException {
        DeviceRentDocs deviceRentDocs = rentDocsMapper.findRentDocsByid(docsid);
        if (deviceRentDocs == null){
            return null;
        }else {
            File file = new File(new File(fileSavePath),deviceRentDocs.getNewname());
            if (file.exists()){
                return new FileInputStream(file);
            }else {
                return null;
            }
        }

    }

    @Override
    public DeviceRentDocs findDeviceRentDocsByid(Integer id) {
        return rentDocsMapper.findRentDocsByid(id);
    }

    @Override
    public DeviceRent findDeviceRentByid(Integer id) {
        return rentMapper.findDeviceRentByid(id);
    }

    @Override
    public void downloadZipFile(DeviceRent deviceRent, ZipOutputStream zipOutputStream) throws IOException {
        //查找合同有多少个合同附件
        List<DeviceRentDocs> deviceRentDocsList = rentDocsMapper.findDeviceRentDocsByRentid(deviceRent.getId());
        for (DeviceRentDocs docs:deviceRentDocsList){
            //压缩包中有很多项（文件夹），每一项都是一个zipentyr
            ZipEntry entry = new ZipEntry(docs.getSourcename());
            //将压缩包的文件夹放入到压缩包中
            zipOutputStream.putNextEntry(entry);


            InputStream inputStream = downloadFile(docs.getId());

            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    @Override
    public List<DeviceRent> findDeviceRentByParam(Map<String, Object> searchParam) {
        return rentMapper.findDeviceRentByParam(searchParam);
    }

    @Override
    public Long countOfDeviceRent() {
        return rentMapper.count();
    }

    @Override
    public void changeRentState(Integer id) {
        //1,将合同修改为已完成
        DeviceRent deviceRent = findDeviceRentByid(id);
        deviceRent.setState("已完成");
        rentMapper.updateState(deviceRent);
        //2,向财务模块添加尾款记录
        Finance finance = new Finance();
        finance.setCreateuser(ShiroUtil.getCurrentUserName());
        finance.setType(Finance.TYPE_IN);
        finance.setCreatedate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setModule("设备租赁");
        finance.setMoney(deviceRent.getLastcost());
        finance.setSerialnumber(SerialNumberUtil.getSerialNumber());
        finance.setState(Finance.STATE_NO);
        finance.setMark("合同尾款");
        finance.setModuleserialnumber(deviceRent.getSerialnumber());

        financeMapper.save(finance);
    }


}
