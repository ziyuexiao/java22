package com.kaishengit.mapper;

import com.kaishengit.pojo.Disk;

import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */
public interface DiskMapper {
    void save(Disk disk);

    List<Disk> findByFid(Integer fid);

    Disk findByid(Integer id);

    void delete(Integer id);

    void batchDel(List<Integer> delIdList);

    List<Disk> findAll();
}
