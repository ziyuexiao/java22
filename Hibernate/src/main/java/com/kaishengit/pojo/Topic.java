package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by lenovo on 2017/3/14.
 */

@Entity
@Table(name = "t_topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)//fetch = FetchType.LAZY开启延迟加载
    @JoinColumn(name = "contentid")
    private TopicContent topicContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicContent getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(TopicContent topicContent) {
        this.topicContent = topicContent;
    }
}
