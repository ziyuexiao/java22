package com.kaishengit.dto;

import lombok.Data;

/**
 * Created by lenovo on 2017/2/16.
 */
@Data
public class JsonResult {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String status;
    private String message;
    private Object data;

    public JsonResult() {

    }
    public JsonResult(String status,String message) {
        this.status = status;
        this.message = message;
    }

    public JsonResult(Object data) {
        this.status = SUCCESS;
        this.data = data;
    }
}
