package com.online.college.portal.controller;

import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import com.online.college.core.course.CourseEnum;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/1.
 */
@Controller
@RequestMapping
public class PortalController {
    @Autowired
    private IConstsSiteCarouselService constsSiteCarouselServiceImpl;
    @Autowired
    private IPortalBusiness portalBusinessImpl;
    @Autowired
    private ICourseService courseService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        //加载轮播
        List<ConstsSiteCarousel> constsSiteCarousels = constsSiteCarouselServiceImpl.queryCarousels(4);
        mv.addObject("carouselList", constsSiteCarousels);

        //设置课程分类
        List<ConstsClassifyVo> vos = portalBusinessImpl.queryAllClassify();
        //课程推荐
        portalBusinessImpl.prepareRecommendCourse(vos);
        mv.addObject("classifys", vos);

        //实战课程推荐
        CourseQueryDto dto = new CourseQueryDto();
        dto.setFree(CourseEnum.FREE_NOT.getValue());
        dto.setCount(5);
        dto.descSortField("weight");
        List<Course> actualCourses = courseService.queryList(dto);
        mv.addObject("actualCourses", actualCourses);

        //获取5门免费课程推荐，根据权重（weight）排序
        dto.setFree(CourseEnum.FREE.getValue());
        List<Course> freeCourses = courseService.queryList(dto);
        mv.addObject("freeCourses", freeCourses);

        //获取7门java课程，根据权重（学习数量studyCount）进行排序
        dto.setFree(null);
        dto.descSortField("studyCount");
        dto.setSubClassify("java");
        dto.setCount(7);
        List<Course> javaCourses = courseService.queryList(dto);
        mv.addObject("javaCourses", javaCourses);

        return mv;
    }
}
