package com.online.college.portal.controller;

import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVo;
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
@Autowired
private IPortalBusiness portalBusinessImpl;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        //加载轮播
        List<ConstsSiteCarousel> constsSiteCarousels = constsSiteCarouselServiceImpl.queryCarousels(4);
        mv.addObject("carouselList",constsSiteCarousels);

        //设置课程分类
        List<ConstsClassifyVo> vos = portalBusinessImpl.queryAllClassify();
        //课程推荐
        portalBusinessImpl.prepareRecommendCourse(vos);
        mv.addObject("classifys",vos);

        return mv;
    }
}
