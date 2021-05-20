package com.jjkj.core.util;

import java.util.Map;

/**
 * Created by jinxin on 2018/4/13.
 */
public class ResultUtil {

    private String resultCode;

    private String resultMessage;

    private Map<String, Object> resultContent;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Map<String, Object> getResultContent() {
        return resultContent;
    }

    public void setResultContent(Map<String, Object> resultContent) {
        this.resultContent = resultContent;
    }

}
