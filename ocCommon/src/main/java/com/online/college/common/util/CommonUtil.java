package com.online.college.common.util;

import java.util.UUID;

/**
 * Created by tx on 2018/7/4.
 */
public class CommonUtil {

    public static String getUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
