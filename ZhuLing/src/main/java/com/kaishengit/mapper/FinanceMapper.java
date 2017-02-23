package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/23.
 */
public interface FinanceMapper {
    List<Finance> findByQueryParam(Map<String, Object> queryParam);

    void save(Finance finance);

    Long count();

    Long countByQueryParam(Map<String, Object> queryParam);

    Finance findByid(Integer id);

    void updateState(Finance finance);

    List<Finance> findByCreatedate(String date);
}
