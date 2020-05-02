package com.cyl.flashsalestock.util;

import java.util.HashMap;
import java.util.Map;

/***
 * map结果类封装
 */
public  class MyResults {

    private static Map<String,Object> map =null;
    static {
        map = new HashMap<>();
    }

    public static Map<String,Object> getresult (boolean result ,String msg , Object data) {

        map.put("result",result);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }
}
