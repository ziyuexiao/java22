package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by lenovo on 2017/3/14.
 */
@Entity
@Table(name = "t_topic_content")
public class TopicContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "topicid")
    private Topic topic;

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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
