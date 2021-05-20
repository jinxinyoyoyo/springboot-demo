package com.jjkj.core.demo.controller;

import com.jjkj.core.demo.service.IDemoService;
import com.jjkj.core.util.ResultUtil;
import com.jjkj.core.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinxin on 2018/4/3.
 */
@Controller
@RequestMapping("/demoController")
public class DemoController {

    /**
     * 注入service
     * mysql
     */
    @Autowired
    private IDemoService demoServiceImpl;

    @RequestMapping(value = "/toDemo", method = RequestMethod.GET)
    public String toDemo() {
        return "core/demo/demo";
    }

    /**
     * 获取每日佳句列表
     * @return 每日佳句列表
     */
    @ResponseBody
    @RequestMapping(value="/getQuoteList", method=RequestMethod.GET)
    public Object getQuoteList(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String id = request.getParameter("id");
        String uuid = UUIDUtil.getUUID();
        System.out.println("uuid-------------------------"+uuid);
        System.out.println("id-------------------------"+id);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        List<Map<String, Object>> quoteList = demoServiceImpl.getQuoteList(paramMap);
        Map<String, Object> quoteMap = quoteList.get(0);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setResultCode("200");
        resultUtil.setResultMessage("成功");
        resultUtil.setResultContent(quoteMap);
        return resultUtil;
    }

}
