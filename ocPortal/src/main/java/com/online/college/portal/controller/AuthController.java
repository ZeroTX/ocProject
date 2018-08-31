package com.online.college.portal.controller;

import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
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
    IAuthUserService authUserServiceImpl;

    @RequestMapping("/register")
    public ModelAndView register(){
        if (SessionContext.isLogin()){
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/register");
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(AuthUser user, String identityCode, HttpServletRequest request){
        //判断验证码
        if (identityCode !=null && !identityCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            return JsonView.render(2);
        }
        AuthUser authUser = authUserServiceImpl.getByUsername(user.getUsername());
        if (authUser!=null){
            return JsonView.render(JsonView.ERROR_CODE);
        }else {
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            authUserServiceImpl.createSelectivity(user);
            return JsonView.render(JsonView.SUCCESS_CODE);
        }
    }
}
