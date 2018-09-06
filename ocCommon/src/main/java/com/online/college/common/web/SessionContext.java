package com.online.college.common.web;

import com.online.college.common.web.auth.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by tx on 2018/8/29.
 * session 工具类
 */
public class SessionContext {
    public static final String IDENTITY_CODE_KEY ="_consts_identify_code_key_";



    public static String getUsername(){
        if (getAuthUser()!=null){
            return getAuthUser().getUsername();
        }
        return null;
    }

    public static Long getUserId(){
        if (getAuthUser() !=null){
            return getAuthUser().getUserId();
        }
        return null;
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
        if (null!= SecurityUtils.getSubject().getPrincipal()){
            return (SessionUser) SecurityUtils.getSubject().getPrincipal();
        }
        return null;
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

    /**
     * 退出登录
     */
    public static void shiroLogout(){
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
