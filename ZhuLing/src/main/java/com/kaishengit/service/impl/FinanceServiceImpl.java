package com.kaishengit.service.impl;

import com.kaishengit.mapper.FinanceMapper;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import com.kaishengit.shiro.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/23.
 */
@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FinanceMapper financeMapper;
    @Override
    public List<Finance> findByQueryParam(Map<String, Object> queryParam) {
        return financeMapper.findByQueryParam(queryParam);
    }

    @Override
    public Long count() {
        return financeMapper.count();
    }

    @Override
    public Long countByQueryParam(Map<String, Object> queryParam) {
        return financeMapper.countByQueryParam(queryParam);
    }

    /**
     * 通过id确认财务流水
     * @param id
     */
    @Override
    public void confirmById(Integer id) {
        Finance finance = financeMapper.findByid(id);
        if(finance != null){
            finance.setState(Finance.STATE_YES);
            finance.setConfirmuser(ShiroUtil.getCurrentUserName());
            finance.setConfirmdate(DateTime.now().toString("yyyy-MM-dd"));
            financeMapper.updateState(finance);
        }
    }

    @Override
    public List<Finance> findByCreatedate(String date) {
        return financeMapper.findByCreatedate(date);
    }

    @Override
    public List<Map<String, Object>> findByQueryParam2(Map<String, Object> queryParam) {
        return financeMapper.findByQueryParam2(queryParam);
    }

    @Override
    public Long countByQueryParam2(Map<String, Object> queryParam) {
        return financeMapper.countByQueryParam2(queryParam);
    }

    @Override
    public List<Map<String, Object>> findPieDataByDay(String date, String type) {
        return financeMapper.findPie(date,type);
    }
}
