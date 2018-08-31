package com.online.college.web.auth;

import com.online.college.common.web.HttpHelper;
import com.online.college.common.web.JsonUtil;
import com.online.college.common.web.JsonView;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by tx on 2018/8/30.
 */
public class AuthFilter extends FormAuthenticationFilter {
    private static final Integer SHIRO_TIME_OUT = 1001;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取请求路径
        String login = httpServletRequest.getServletPath();
        //判断当前路径是否为登录页,如果是，放行
        if (login.equals("/index.html")) {
            return true;
        }

        //获取当前登录用户
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated()) {
            return true;
        }
        //判断是否为Ajax请求
        if (HttpHelper.isAjaxRequest(httpServletRequest)) {
            JsonView jv = new JsonView();
            jv.setMessage("SHIRO登录超时");
            jv.setCode(SHIRO_TIME_OUT);
            HttpServletResponse _response = (HttpServletResponse) response;
            PrintWriter writer = _response.getWriter();
            _response.setContentType("application/json");
            writer.write(JsonUtil.toJson(jv));
            writer.flush();
            writer.close();
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }
        //若果没有授权，则跳转到登录页面
        return false;
    }
}
