package com.online.college.portal.controller;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tx on 2018/8/15.
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseBusiness courseBusiness;
    @Autowired
    private IAuthUserService authUserService;
    @Autowired
    private ICourseService courseService;

    @RequestMapping("/learn/{courseId}")
    public ModelAndView learn(@PathVariable Long courseId){
        if (null==courseId){
            return new ModelAndView("/error/404");
        }
        Course course = courseService.getById(courseId);
        if (null == course){
            return new ModelAndView("/error/404");
        }

        //获取课程章节
        ModelAndView mv = new ModelAndView("learn");
        List<CourseSectionVo> chaptSection = courseBusiness.queryCourseSection(courseId);
        mv.addObject("chaptSections",chaptSection);
        mv.addObject("course",course);

        //获取课程讲师
        AuthUser courseTeacher = authUserService.getByUsername(course.getUsername());
        if (StringUtils.isNotEmpty(courseTeacher.getHeader())){
            courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
        }
        mv.addObject("courseTeacher",courseTeacher);

        //获取课程推荐
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.descSortField("weight");
        queryEntity.setCount(5);
        queryEntity.setSubClassify(course.getSubClassify());
        List<Course> recomdCourseList = courseService.queryList(queryEntity);
        mv.addObject("recomdCourseList",recomdCourseList);

        return mv;
    }
}
