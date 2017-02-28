package com.kaishengit.service;

import com.kaishengit.pojo.Finance;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/23.
 */
public interface FinanceService {
    List<Finance> findByQueryParam(Map<String, Object> queryParam);

    Long count();

    Long countByQueryParam(Map<String, Object> queryParam);

    void confirmById(Integer id);

    List<Finance> findByCreatedate(String date);

    List<Map<String, Object>> findByQueryParam2(Map<String, Object> queryParam);

    Long countByQueryParam2(Map<String, Object> queryParam);

    List<Map<String,Object>> findPieDataByDay(String date, String type);
}
