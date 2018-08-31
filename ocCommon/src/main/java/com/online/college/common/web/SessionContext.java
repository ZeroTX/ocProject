package com.online.college.common.web;

import com.online.college.common.web.auth.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tx on 2018/8/29.
 * session 工具类
 */
public class SessionContext {
    public static final String IDENTITY_CODE_KEY ="_consts_identify_code_key_";



    public static String getUsername(){
        return "";
    }

    public static Long getUserId(){
        return 1L;
    }

    /**
     * 判断用户是否登录
     * @return
     */
    public static boolean isLogin(){
        Subject subject = SecurityUtils.getSubject();
        if (null!=subject && null != subject.getPrincipal()){
            return true;
        }
        return false;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static SessionUser getAuthUser(){
        return (SessionUser) SecurityUtils.getSubject().getPrincipal();
    }
    /**
     * 获取验证码
     * @param request
     * @return
     */
    public static String getIdentifyCode(HttpServletRequest request){
        if (request.getSession().getAttribute(IDENTITY_CODE_KEY)!=null){
            return request.getSession().getAttribute(IDENTITY_CODE_KEY).toString();
        }else {
            return null;
        }
    }

    /**
     * 获取属性
     * @param request
     * @param key
     * @return
     */
    public static Object getAttribute(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }

    /**
     * 设置属性
     * @param request
     * @param key
     * @param value
     */
    public static void  setAttribute(HttpServletRequest request,String key,String value){
        request.getSession().setAttribute(key,value);
    }

    /**
     * 删除属性
     * @param request
     * @param key
     */
    public static void removeAttribute(HttpServletRequest request,String key){
        request.getSession().removeAttribute(key);
    }
}
