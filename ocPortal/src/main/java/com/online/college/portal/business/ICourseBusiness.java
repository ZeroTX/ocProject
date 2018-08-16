package com.online.college.portal.business;

import com.online.college.portal.vo.CourseSectionVo;

import java.util.List;

/**
 * Created by tx on 2018/8/15.
 */
public interface ICourseBusiness {
    /**
     * 获取课程章节
     * @param courseId
     * @return
     */
    List<CourseSectionVo> queryCourseSection(Long courseId);
}
