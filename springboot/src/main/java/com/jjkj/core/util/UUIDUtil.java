package com.jjkj.core.util;

import java.util.UUID;

/**
 * Created by jinxin on 2018/4/3.
 */
public class UUIDUtil {

    public static String getUUID() {
        String var0 = UUID.randomUUID().toString();
        return (new StringBuilder()).insert(0, var0.substring(0, 8)).append(var0.substring(9, 13)).append(var0.substring(14, 18)).append(var0.substring(19, 23)).append(var0.substring(24)).toString();
    }

}
