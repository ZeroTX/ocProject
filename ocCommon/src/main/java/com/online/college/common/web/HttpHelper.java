package com.online.college.common.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tx on 2018/8/31.
 */
public class HttpHelper extends WebUtils {

    //判断当前请求是否为Ajax
    public static boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        return !StringUtils.isEmpty(header) && "XMLHttpRequest".equals(header);
    }
}
