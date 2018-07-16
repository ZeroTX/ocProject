package com.online.college.portal.vo;

import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.course.domain.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2018/7/16.
 */
public class ConstsClassifyVo extends ConstsClassify {
    private static final long serialVersionUID = -6898939223836635781L;

    //子分类列表
    private List<ConstsClassify> subClassifyList = new ArrayList<>();
    //子分类推荐课程
    private List<Course> recommendCourseList;


    public List<ConstsClassify> getSubClassifyList() {
        return subClassifyList;
    }

    public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
        this.subClassifyList = subClassifyList;
    }

    public List<Course> getRecommendCourseList() {
        return recommendCourseList;
    }

    public void setRecommendCourseList(List<Course> recommendCourseList) {
        this.recommendCourseList = recommendCourseList;
    }
}
