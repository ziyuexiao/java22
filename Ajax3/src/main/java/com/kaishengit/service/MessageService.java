package com.kaishengit.service;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;

import java.util.List;

/**
 * Created by lenovo on 2016/12/9.
 */
public class MessageService {

    private MessageDao messageDao = new MessageDao();

    public List<Message> findAll(){
        return messageDao.findAll();
    }
    public List<Message> findByMaxId(int maxId) {
        return messageDao.findByMaxId(maxId);
    }
}
