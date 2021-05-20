package com.jjkj.core.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jinxin on 2018/4/3.
 */
@Mapper
@Component(value = "demoMapper")
public interface DemoMapper {

    /**
     * 获取每日佳句信息列表
     * @return 每日佳句信息列表
     */
    public List<Map<String, Object>> getQuoteList(Map<String, Object> paramMap);

}
