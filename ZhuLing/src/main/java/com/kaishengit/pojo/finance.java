package com.kaishengit.pojo;

import lombok.Data;

/**
 * Created by lenovo on 2017/2/23.
 */
@Data
public class finance {
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

}
