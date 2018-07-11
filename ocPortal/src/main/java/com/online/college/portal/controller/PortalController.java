package com.online.college.portal.controller;

import com.online.college.common.App;
import com.online.college.common.CommonDemo;
import com.online.college.common.oc.OCDemo;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/1.
 *
 */
@Controller
@RequestMapping
public class PortalController {
@Autowired
private IConstsSiteCarouselService constsSiteCarouselServiceImpl;
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        //加载轮播
        List<ConstsSiteCarousel> constsSiteCarousels = constsSiteCarouselServiceImpl.queryCarousels(4);
        mv.addObject("carouselList",constsSiteCarousels);
        System.out.println(constsSiteCarousels);
        return mv;
    }
}
