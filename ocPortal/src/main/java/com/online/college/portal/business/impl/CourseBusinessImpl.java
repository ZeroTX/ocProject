package com.online.college.portal.business.impl;

import com.online.college.core.course.CourseEnum;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseCommentService;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.portal.business.ICourseBusiness;
import com.online.college.portal.vo.CourseSectionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tx on 2018/8/15.
 */
@Service
public class CourseBusinessImpl implements ICourseBusiness {
    @Resource
    private ICourseSectionService courseSectionService;
    /**
     * 获取课程章节
     *
     * @param courseId
     * @return
     */
    @Override
    public List<CourseSectionVo> queryCourseSection(Long courseId) {
        List<CourseSectionVo> resultList = new ArrayList<>();
        CourseSection queryEntity = new CourseSection();
        queryEntity.setCourseId(courseId);
        queryEntity.setOnsale(CourseEnum.ONSALE.getValue());//上架
        List<CourseSection> courseSections = courseSectionService.queryAll(queryEntity);
        Map<Long,CourseSectionVo> voMap = new LinkedHashMap<>();
        courseSections.forEach(item -> {
            if (Long.valueOf(0).equals(item.getParentId())){
                CourseSectionVo vo = new CourseSectionVo();
                BeanUtils.copyProperties(item,vo);
                voMap.put(vo.getId(),vo);
            }else {
                voMap.get(item.getParentId()).getSections().add(item);//小节添加到大章中
            }
        });
        voMap.forEach((key,value)->resultList.add(value));
        return resultList;
    }
}
