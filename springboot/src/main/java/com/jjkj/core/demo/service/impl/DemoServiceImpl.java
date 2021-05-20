package com.jjkj.core.demo.service.impl;

import com.jjkj.core.demo.mapper.DemoMapper;
import com.jjkj.core.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jinxin on 2018/4/3.
 */
@Service(value = "demoServiceImpl")
public class DemoServiceImpl implements IDemoService {

    /**
     * 注入mapper
     */
    @Autowired
    private DemoMapper demoMapper;

    /**
     * 获取每日佳句信息列表
     * @return 每日佳句信息列表
     */
    public List<Map<String, Object>> getQuoteList(Map<String, Object> paramMap) {
        List<Map<String, Object>> quoteList = demoMapper.getQuoteList(paramMap);
        return quoteList;
    }

}
