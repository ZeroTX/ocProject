package com.online.college.common.web;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by tx on 2018/8/31.
 */
public class JsonUtil {
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }

    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
}
