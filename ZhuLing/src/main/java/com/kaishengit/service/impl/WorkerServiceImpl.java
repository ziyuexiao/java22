package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.WorkerDispatchDto;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.*;
import com.kaishengit.pojo.*;
import com.kaishengit.service.WorkerService;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lenovo on 2017/2/18.
 */
@Service
public class WorkerServiceImpl implements WorkerService{
    private Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private WorkerDispatchMapper workerDispatchMapper;
    @Autowired
    private WorkerDispatchDetailMapper detailMapper;
    @Autowired
    private WorkerDispatchDocsMapper docsMapper;
    @Autowired
    private FinanceMapper financeMapper;
    @Value("${upload.path}")
    private String fileSavePath;
    @Override
    public List<Worker> findAllWorkers() {
        return workerMapper.findAllWorkers();
    }

    @Override
    public Worker findWorkerByid(Integer id) {
        return workerMapper.findWorkerById(id);
    }

    //保存劳务派遣合同
    @Override
    @Transactional
    public String saveNew(WorkerDispatchDto workerDispatchDto) {
        //1,保存合同
        WorkerDispatch workerDispatch = new WorkerDispatch();

        workerDispatch.setCompanyname(workerDispatchDto.getCompanyName());
        workerDispatch.setCompanytel(workerDispatchDto.getCompanyTel());
        workerDispatch.setAddress(workerDispatchDto.getAddress());
        workerDispatch.setLinkman(workerDispatchDto.getLinkMan());
        workerDispatch.setPersontel(workerDispatchDto.getPersonTel());
        workerDispatch.setCardnum(workerDispatchDto.getCardNum());
        workerDispatch.setStartdate(workerDispatchDto.getStartDate());
        workerDispatch.setEnddate(workerDispatchDto.getEndDate());
        workerDispatch.setTotalday(workerDispatchDto.getTotalDays());
        workerDispatch.setSerialnumber(SerialNumberUtil.getSerialNumber());
        workerDispatch.setCreateuser(ShiroUtil.getCurrentUserName());
            //给价格设置初始值
        workerDispatch.setTotalprice(0F);
        workerDispatch.setPrecost(0F);
        workerDispatch.setLastcost(0f);

        workerDispatchMapper.save(workerDispatch);
        //2,保存合同详情页
        List<WorkerDispatchDto.WorkerArrayBean> workerArrayBeanList = workerDispatchDto.getWorkerArray();
        List<WorkerDispatchDetail> workerDispatchDetailList = Lists.newArrayList();
        float totalprice = 0F;
        for (WorkerDispatchDto.WorkerArrayBean bean : workerArrayBeanList){
            //查询当前工种数
            Worker worker = workerMapper.findWorkerById(bean.getId());
            if(worker.getWorkercurrnum()<bean.getWorkernum()){
                throw new ServiceException(worker.getWorkername()+"数量不足");
            }else {
                worker.setWorkercurrnum(worker.getWorkercurrnum()-bean.getWorkernum());
                workerMapper.updateCurrnum(worker);
            }

            WorkerDispatchDetail workerDispatchDetail = new WorkerDispatchDetail();

            workerDispatchDetail.setWoname(bean.getWorkername());
            workerDispatchDetail.setWoprice(bean.getWorkerprice());
            workerDispatchDetail.setWonum(bean.getWorkernum());
            workerDispatchDetail.setTotalprice(bean.getTotalprice());
            workerDispatchDetail.setDispatchid(workerDispatch.getId());

            workerDispatchDetailList.add(workerDispatchDetail);

            totalprice+=bean.getTotalprice();
        }
            if(!workerDispatchDetailList.isEmpty()){
                detailMapper.batchSave(workerDispatchDetailList);//批量添加

            }
            //计算价钱
            totalprice = totalprice*workerDispatch.getTotalday();
            float precost = totalprice*0.3F;
            float lastcost = totalprice-precost;

            workerDispatchMapper.updateCost(totalprice,precost,lastcost,workerDispatch.getId());
        //3,保存上传的合同扫描件
        List<WorkerDispatchDto.FileArrayBean>  docsBean = workerDispatchDto.getFileArray();
        List<WorkerDispatchDocs> dispatchDocsList = Lists.newArrayList();
        for(WorkerDispatchDto.FileArrayBean docs : docsBean){
            WorkerDispatchDocs dispatchDocs = new WorkerDispatchDocs();
            dispatchDocs.setSourcename(docs.getSourcename());
            dispatchDocs.setNewname(docs.getNewname());
            dispatchDocs.setDispatchid(workerDispatch.getId());

            dispatchDocsList.add(dispatchDocs);
        }
        if(!dispatchDocsList.isEmpty()){
            docsMapper.batchSave(dispatchDocsList);
        }
        //4,写入流水
        Finance finance = new Finance();
        finance.setCreateuser(ShiroUtil.getCurrentUserName());
        finance.setType(Finance.TYPE_IN);
        finance.setCreatedate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setModule("劳务派遣");
        finance.setMoney(precost);
        finance.setSerialnumber(SerialNumberUtil.getSerialNumber());
        finance.setState(Finance.STATE_NO);
        finance.setMark("预付款");
        finance.setModuleserialnumber(workerDispatch.getSerialnumber());

        financeMapper.save(finance);
        return workerDispatch.getSerialnumber();

    }

    /**
     * 根据序列号找对应的劳务派遣
     * @param serialnumber
     * @return
     */
    @Override
    public WorkerDispatch findWorkerDispatchBySerialnumber(String serialnumber) {
        return workerDispatchMapper.findWorkerDispatchBySerialnumber(serialnumber);
    }

    /**
     *找派遣的详情
     * @param dispatchid
     * @return
     */
    @Override
    public List<WorkerDispatchDetail> findWorkerDispatchDetailByDispatchid(Integer dispatchid) {
        return detailMapper.findWorkerDispatchDetailByDispatchid(dispatchid);
    }

    /**
     * 找上传的合同附件
     * @param dispatchid
     * @return
     */
    @Override
    public List<WorkerDispatchDocs> findWorkerDispatchDocsByDispatchid(Integer dispatchid) {
        return docsMapper.findWorkerDispatchDocsByDispatchid(dispatchid);
    }

    /**
     * 下载合同
     * @param id
     * @return
     */
    @Override
    public InputStream downDocs(Integer id) throws IOException{
        WorkerDispatchDocs workerDispatchDocs = docsMapper.findDispatchDocsByid(id);
        if (workerDispatchDocs == null){
            return null;
        }else {
            File file = new File(new File(fileSavePath),workerDispatchDocs.getNewname());
            if (file.exists()){
                return new FileInputStream(file);
            }else {
                return null;
            }
        }
    }

    @Override
    public WorkerDispatchDocs findWorkerDispatchDocsByid(Integer id) {
        return docsMapper.findDispatchDocsByid(id);
    }

    @Override
    public WorkerDispatch findWorkerDispatchByid(Integer id) {
        return workerDispatchMapper.findWorkerDispatchByid(id);
    }

    /**
     * 打包下载
     * @param workerDispatch
     * @param zipOutputStream
     * @throws IOException
     */
    @Override
    public void downZipFile(WorkerDispatch workerDispatch, ZipOutputStream zipOutputStream) throws IOException {
        //查找合同有多少个合同附件
        List<WorkerDispatchDocs> docsList = docsMapper.findWorkerDispatchDocsByDispatchid(workerDispatch.getId());
        for (WorkerDispatchDocs docs:docsList){
            //压缩包中有很多项（文件夹），每一项都是一个zipentyr
            ZipEntry entry = new ZipEntry(docs.getSourcename());
            //将压缩包的文件夹放入到压缩包中
            zipOutputStream.putNextEntry(entry);


            InputStream inputStream = downDocs(docs.getId());

            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    @Override
    public List<WorkerDispatch> findWorkerDispatchBySearchParam(Map<String, Object> searchParam) {
        return workerDispatchMapper.findWorkerDispatchByParam(searchParam);
    }

    @Override
    public Long count() {
        return workerDispatchMapper.count();
    }

    @Override
    public Long countBySearchParam(Map<String, Object> searchParam) {
        return workerDispatchMapper.countBySearchParam(searchParam);
    }

    @Override
    public void changeRentState(Integer id) {
        //1,将合同修改为已完成
        WorkerDispatch workerDispatch = findWorkerDispatchByid(id);
        workerDispatch.setState("已完成");
        workerDispatchMapper.updateState(workerDispatch);
        //2,向财务模块添加尾款记录
        Finance finance = new Finance();
        finance.setCreateuser(ShiroUtil.getCurrentUserName());
        finance.setType(Finance.TYPE_IN);
        finance.setCreatedate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setModule("劳务派遣");
        finance.setMoney(workerDispatch.getLastcost());
        finance.setSerialnumber(SerialNumberUtil.getSerialNumber());
        finance.setState(Finance.STATE_NO);
        finance.setMark("合同尾款");
        finance.setModuleserialnumber(workerDispatch.getSerialnumber());

        financeMapper.save(finance);
    }
}
