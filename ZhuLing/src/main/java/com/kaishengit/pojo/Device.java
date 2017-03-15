package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/1/13.
 */
@Data
public class Device implements Serializable {
    private Integer id;
    private String devicename;
    private String deviceunit;
    private Integer devicetotal;
    private Integer devicenum;
    private Float deviceprice;


}
