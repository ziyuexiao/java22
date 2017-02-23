package com.kaishengit.service;

import com.kaishengit.pojo.Disk;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */
public interface DiskService {
    void saveNewFile(Integer fid, MultipartFile file);

    List<Disk> findByFid(Integer path);

    void saveNewFolder(Disk disk);

    InputStream downloadFile(Integer id) throws FileNotFoundException;

    Disk findByid(Integer id);

    void delByid(Integer id);
}
