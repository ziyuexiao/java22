package com.kaishengit.entity;

import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2016/12/20.
 */
public class Topic {
    private Integer id;
    private Timestamp createtime;
    private String title;
    private String content;
    private Integer clicknum;
    private Integer favnum;
    private Integer thankyounum;
    private Integer replynum;
    private Timestamp lastreplytime;
    private Integer t_user_id;
    private Integer t_note_id;

    private User user;
    private Node node;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public Integer getFavnum() {
        return favnum;
    }

    public void setFavnum(Integer favnum) {
        this.favnum = favnum;
    }

    public Integer getThankyounum() {
        return thankyounum;
    }

    public void setThankyounum(Integer thankyounum) {
        this.thankyounum = thankyounum;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public Timestamp getLastreplytime() {
        return lastreplytime;
    }

    public void setLastreplytime(Timestamp lastreplytime) {
        this.lastreplytime = lastreplytime;
    }

    public Integer getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(Integer t_user_id) {
        this.t_user_id = t_user_id;
    }

    public Integer getT_note_id() {
        return t_note_id;
    }

    public void setT_note_id(Integer t_note_id) {
        this.t_note_id = t_note_id;
    }

    public boolean isEdit(){
        DateTime dateTime = new DateTime(getCreatetime());
        if (dateTime.plusMinutes(500).isAfterNow()&&getReplynum()==0){
            return true;
        }else {
            return false;
        }
    }
}
