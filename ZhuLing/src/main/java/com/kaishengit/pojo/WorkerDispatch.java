package com.kaishengit.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.zip.ZipOutputStream;

/**
 * Created by lenovo on 2017/2/22.
 */
@Data
public class WorkerDispatch {
    private Integer id;
    private String companyname;
    private String companytel;
    private String linkman;
    private String persontel;
    private String cardnum;
    private String address;
    private String startdate;
    private String enddate;
    private Integer totalday;
    private Float totalprice;
    private Float precost;
    private Float lastcost;
    private Timestamp createtime;
    private String createuser;
    private String serialnumber;
    private String state;



}
