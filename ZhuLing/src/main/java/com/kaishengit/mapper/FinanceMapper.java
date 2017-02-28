package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;
import org.apache.ibatis.annotations.Param;

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

    List<Map<String, Object>> findByQueryParam2(Map<String, Object> queryParam);

    Long countByQueryParam2(Map<String, Object> queryParam);

    List<Map<String,Object>> findPie(@Param("date") String date,
                                     @Param("type") String type);
}
