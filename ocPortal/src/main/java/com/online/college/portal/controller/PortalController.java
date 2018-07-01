package com.online.college.portal.controller;

import com.online.college.common.App;
import com.online.college.common.CommonDemo;
import com.online.college.common.oc.OCDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2018/7/1.
 *
 */
@Controller
@RequestMapping
public class PortalController {
    @Autowired
    CommonDemo commonDemo;
    @Autowired
    OCDemo ocDemo;

    @RequestMapping("/index")
    public ModelAndView index(){
        System.out.println(ocDemo.getString3());
        System.out.println(commonDemo.getString2());
        System.out.println(App.getString());
        System.out.println("进来了");
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("info","网校欢迎你!");
        return mv;
    }
}
