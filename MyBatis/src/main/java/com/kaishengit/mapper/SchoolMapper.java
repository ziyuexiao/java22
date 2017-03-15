package com.kaishengit.mapper;

import com.kaishengit.pojo.School;

import java.util.List;

/**
 * Created by lenovo on 2017/1/4.
 */
public interface SchoolMapper {
    School findByid(Integer id);
    List<School> findAll();
}
