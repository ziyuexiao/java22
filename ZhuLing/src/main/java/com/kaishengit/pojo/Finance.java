package com.kaishengit.pojo;

import lombok.Data;

/**
 * Created by lenovo on 2017/2/23.
 */
@Data
public class Finance {

    public static final String TYPE_IN = "收入";
    public static final String TYPE_OUT = "支出";
    public static final String STATE_NO = "未确认";
    public static final String STATE_YES = "已确认";

    private Integer id;
    private String serialnumber;
    private String type;
    private float money;
    private String state;
    private String module;
    private String createdate;
    private String createuser;
    private String confirmdate;
    private String confirmuser;
    private String mark;
    private String moduleserialnumber;

}
