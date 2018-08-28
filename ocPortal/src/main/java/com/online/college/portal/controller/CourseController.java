package com.online.college.portal.controller;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.core.course.service.ICourseService;
import com.online.college.core.user.domain.UserCourseSection;
import com.online.college.core.user.service.IUserCourseSectionService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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
    @Autowired
    private ICourseSectionService courseSectionService;
    @Autowired
    private IUserCourseSectionService userCourseSectionService;

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

    @RequestMapping("/video/{sectionId}")
    public ModelAndView video(@PathVariable Long sectionId){
        if (null == sectionId){
            return new ModelAndView("error/404");
        }
        CourseSection courseSection = courseSectionService.getById(sectionId);
        if (null == courseSection){
            return new ModelAndView("error/404");
        }

        //课程章节
        ModelAndView mv = new ModelAndView("video");
        List<CourseSectionVo> chaptSection = courseBusiness.queryCourseSection(courseSection.getCourseId());
        mv.addObject("chaptSections",chaptSection);
        mv.addObject("courseSection",courseSection);

        //学习记录
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(1L);
        userCourseSection.setCourseId(courseSection.getCourseId());
        userCourseSection.setSectionId(courseSection.getId());
        UserCourseSection result = userCourseSectionService.queryLatest(userCourseSection);

        if (null == result){
            userCourseSection.setCreateTime(new Date());
            userCourseSection.setCreateUser("wangyangming");
            userCourseSection.setUpdateTime(new Date());
            userCourseSection.setUpdateUser("wangyangming");

            userCourseSectionService.createSelectivity(userCourseSection);
        }else {
            result.setUpdateTime(new Date());
            userCourseSectionService.update(result);
        }
        return mv;
    }
}
