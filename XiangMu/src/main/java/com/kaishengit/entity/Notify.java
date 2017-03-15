package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/12/26.
 */
public class Notify {

    //消息状态：未读
    public static final Integer STATE_UNREAD=0;
    //消息状态：已读
    public static final Integer STATE_READED=1;

    private Integer id;
    private String content;
    private Integer userid;
    private Timestamp createtime;
    private Timestamp readtime;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getReadtime() {
        return readtime;
    }

    public void setReadtime(Timestamp readtime) {
        this.readtime = readtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
