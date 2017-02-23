package com.kaishengit.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2017/2/21.
 */
@Data
public class Disk {

    public static final String FILE_TYPE = "file";
    public static final String DIRECTORY_TYPE = "directory";


    private Integer id;
    private String name;
    private String sourcename;
    private Integer fid;
    private String size;
    private Timestamp createtime;
    private String createuser;
    private String type;
}
