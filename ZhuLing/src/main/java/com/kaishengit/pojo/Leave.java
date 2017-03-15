package com.kaishengit.pojo;


import com.kaishengit.dto.Process;
import lombok.Data;

import java.io.Serializable;

@Data
public class Leave extends Process implements Serializable {

    private static final long serialVersionUID = 1L;
    private String startTime;
    private String endTime;
    private String realityStartTime;
    private String realityEndTime;
    private String leaveType;
    private String reason;



}