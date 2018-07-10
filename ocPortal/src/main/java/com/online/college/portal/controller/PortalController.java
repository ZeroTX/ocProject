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

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("info","网校欢迎你!");
        return mv;
    }
}
