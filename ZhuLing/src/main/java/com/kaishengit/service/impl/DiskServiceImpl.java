package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.DiskMapper;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.shiro.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by lenovo on 2017/2/21.
 */
@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    private DiskMapper diskMapper;
    @Value("${upload.path}")
    private String loadPath;

    /**
     * 保存文件
     * @param fid
     * @param file
     */
    @Override
    @Transactional
    public void saveNewFile(Integer fid, MultipartFile file) {
        //将文件存到磁盘
        String sourceName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString();
        //获取文件大小
        Long size = file.getSize();
        if (sourceName.lastIndexOf(".")!=-1){
            newName+=sourceName.substring(sourceName.lastIndexOf("."));
        }

        try {
            File saveFile = new File(new File(loadPath),newName);
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = new FileOutputStream(saveFile);
            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (IOException e){
            throw new ServiceException("保存文件异常",e);
        }


        //记录到数据库
        Disk disk = new Disk();
        disk.setName(newName);
        disk.setSourcename(sourceName);
        disk.setFid(fid);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));//将大小转换为人们易读的，如xxKB,xxMB,xxGB
        disk.setCreateuser(ShiroUtil.getCurrentUserName());
        disk.setType(Disk.FILE_TYPE);


        diskMapper.save(disk);



    }

    @Override
    public List<Disk> findByFid(Integer fid) {
        return diskMapper.findByFid(fid);
    }

    @Override
    public void saveNewFolder(Disk disk) {

        disk.setType(Disk.DIRECTORY_TYPE);
        disk.setCreateuser(ShiroUtil.getCurrentUserName());
        diskMapper.save(disk);
    }

    /**
     * 下载
     * @param id
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public InputStream downloadFile(Integer id) throws FileNotFoundException {
        Disk disk = diskMapper.findByid(id);
        if(disk == null || Disk.DIRECTORY_TYPE.equals(disk.getType())){
            return null;
        }else {
           File file = new File(new File(loadPath),disk.getName());
           return new FileInputStream(file);
        }

    }

    @Override
    public Disk findByid(Integer id) {
        return diskMapper.findByid(id);
    }

    /**
     * 递归删除
     * @param id
     */
    @Override
    @Transactional
    public void delByid(Integer id) {
        Disk disk = findByid(id);
        if(disk != null){
            if(Disk.FILE_TYPE.equals(disk.getType())){
                //删除文件,删除磁盘上的
                File file = new File(loadPath,disk.getName());
                file.delete();
                //删除数据库中的记录
                diskMapper.delete(id);
            }else {
                List<Disk> diskList = diskMapper.findAll();//所有记录
                List<Integer> delIdList = Lists.newArrayList();//要被删的id
                findDelId(diskList,delIdList,id);
                delIdList.add(id);
                //批量删除
                diskMapper.batchDel(delIdList);

            }
        }
    }

    private void findDelId(List<Disk> diskList,
                           List<Integer> delIdList, Integer id) {
        for (Disk disk : diskList){
            if(disk.getFid().equals(id)){
                delIdList.add(disk.getId());
                if(disk.getType().equals(Disk.DIRECTORY_TYPE)){
                    findDelId(diskList,delIdList,disk.getId());
                }else {
                    //删除文件
                    File file = new File(loadPath,disk.getName());
                    file.delete();
                }
            }
        }

    }
}
