package com.kaishengit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by lenovo on 2017/2/20.
 */
@Data
@AllArgsConstructor//产生所有参数的构造方法
public class DataTablesResult {
    private String draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;
}

