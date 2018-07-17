package com.online.college.portal.controller;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tx on 2018/7/17.
 */
@Controller
@RequestMapping("/course")
public class CourseListController {
    @Autowired
    private IConstsClassifyService constsClassifyService;
    @Autowired
    private IPortalBusiness portalBusiness;

    @RequestMapping("/list")
    public ModelAndView list(String code, String sort, TailPage<Course> page) {
        ModelAndView mv = new ModelAndView("list");
        String curCode = "-1";//当前方向code
        String curSubCode = "-2";//当前分类code
        //查询所有课程分类（一级分类）
        List<ConstsClassifyVo> classifyVos = portalBusiness.queryAllClassify();

        mv.addObject("classifys", classifyVos);

        //查询当前分类
        ConstsClassify curClassify = constsClassifyService.getByCode(code);

        if (curClassify == null) {//没有此分类，加载有所二级分类
            List<ConstsClassify> subClassifys = new ArrayList<>();
            classifyVos.forEach(c -> subClassifys.addAll(c.getSubClassifyList()));
            mv.addObject("subClassifys", subClassifys);
        } else {
            if (!"0".equals(curClassify.getParentCode())) {//当前分类是二级分类
                List<ConstsClassifyVo> vos = classifyVos.stream()
                        .filter(c -> c.getCode().equals(curClassify.getParentCode()))
                        .collect(Collectors.toList());
                curSubCode = curClassify.getCode();
                curCode = curClassify.getParentCode();
                mv.addObject("subClassifys", vos.get(0).getSubClassifyList());
            } else {//当前是一级分类
                List<ConstsClassifyVo> vos = classifyVos.stream()
                        .filter(c -> c.getCode().equals(curClassify.getCode()))
                        .collect(Collectors.toList());
                curCode = curClassify.getCode();
                mv.addObject("subClassifys",vos.get(0).getSubClassifyList());
            }
        }
        mv.addObject("curCode",curCode);
        mv.addObject("curSubCode",curSubCode);

        return mv;
    }
}
