package com.online.college.portal.controller;

import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tx on 2018/7/6.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthUserService authUserServiceImpl;

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register() {
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/register");
    }

    /**
     * 实现注册
     *
     * @param user
     * @param identityCode
     * @param request
     * @return
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(AuthUser user, String identityCode, HttpServletRequest request) {
        //判断验证码
        if (identityCode != null && !identityCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2);
        }
        AuthUser authUser = authUserServiceImpl.getByUsername(user.getUsername());
        if (authUser != null) {
            return JsonView.render(JsonView.ERROR_CODE);
        } else {
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            authUserServiceImpl.createSelectivity(user);
            return JsonView.render(JsonView.SUCCESS_CODE);
        }
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/login");
    }

    /**
     * 实现登录
     *
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(AuthUser user, String identityCode, HttpServletRequest request) {
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/user/home.html");
        }

        if (identityCode != null && !identityCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 1);
            return mv;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            return new ModelAndView("redirect:/user/home.html");
        } catch (AuthenticationException e) {
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 2);
            return mv;
        }

    }

    /**
     * ajax登录
     * @param user
     * @param identityCode
     * @param rememberMe
     * @param request
     * @return
     */
    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public String ajaxLogin(AuthUser user,String identityCode,Integer rememberMe,HttpServletRequest request){
        if (identityCode!=null && !identityCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            return JsonView.render(2,"验证码不正确");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            if (rememberMe !=null && rememberMe ==1){
                token.setRememberMe(true);
            }
            currentUser.login(token);
            return new JsonView().toString();
        } catch (AuthenticationException e) {
            return JsonView.render(1,"用户名或密码不正确");
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView loginOut(HttpServletRequest request) {
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/index.html");
    }
}
