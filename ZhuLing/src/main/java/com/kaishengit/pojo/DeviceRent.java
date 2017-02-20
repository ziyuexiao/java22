package com.kaishengit.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2017/2/17.
 */
@Data
public class DeviceRent {
    private Integer id;
    private String companyname;
    private String linkman;
    private String cardnum;
    private String telnum;
    private String address;
    private String fax;
    private String rentdate;
    private String backdate;
    private Integer totalday;
    private Float totalprice;
    private Float precost;
    private Float lastcost;
    private Timestamp createtime;
    private String createuser;
    private String serialnumber;
    private String state;
}
