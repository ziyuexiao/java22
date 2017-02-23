package com.kaishengit.pojo;

import lombok.Data;

/**
 * Created by lenovo on 2017/2/22.
 */
@Data
public class WorkerDispatchDetail {
    private Integer id;
    private String woname;
    private String wounit;
    private Integer wonum;
    private Float woprice;
    private Integer totaldays;
    private Float totalprice;
    private Integer dispatchid;
}
