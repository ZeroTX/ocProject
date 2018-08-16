package com.online.college.portal.vo;

import com.online.college.core.course.domain.CourseSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2018/8/15.
 * 课程章节
 */
public class CourseSectionVo extends CourseSection {
    //小节
    private List<CourseSection> sections = new ArrayList<>();

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }
}
