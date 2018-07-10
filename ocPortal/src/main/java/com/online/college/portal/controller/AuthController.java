package com.online.college.portal.controller;

import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tx on 2018/7/6.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    IAuthUserService authUserService;

    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("auth/register");
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(AuthUser user){
        AuthUser authUser = authUserService.getByUsername(user.getUsername());
        if (authUser!=null){
            return JsonView.render(JsonView.ERROR_CODE);
        }else {
            authUserService.createSelectivity(user);
            return JsonView.render(JsonView.SUCCESS_CODE);
        }
    }
}
