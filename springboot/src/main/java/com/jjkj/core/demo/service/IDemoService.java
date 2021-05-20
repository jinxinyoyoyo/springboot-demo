package com.jjkj.core.demo.service;

import java.util.List;
import java.util.Map;

/**
 * Created by jinxin on 2018/4/3.
 */
public interface IDemoService {

    /**
     * 获取每日佳句信息列表
     * @return 每日佳句信息列表
     */
    public List<Map<String, Object>> getQuoteList(Map<String, Object> paramMap);

}
