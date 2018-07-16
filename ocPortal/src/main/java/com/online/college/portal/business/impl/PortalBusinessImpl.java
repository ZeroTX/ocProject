package com.online.college.portal.business.impl;

import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tx on 2018/7/16.
 */
@Service
public class PortalBusinessImpl implements IPortalBusiness {
    @Resource
    private IConstsClassifyService constsClassifyService;
    @Resource
    private ICourseService courseService;

    /**
     * 获取所有，包括一级，二级
     *
     * @return
     */
    @Override
    public List<ConstsClassifyVo> queryAllClassify() {
        List<ConstsClassifyVo> classifyVos = new ArrayList<>();
        List<ConstsClassify> classifies = constsClassifyService.queryAll();
        Map<String, ConstsClassifyVo> resultMap = new LinkedHashMap<>();

        classifies.forEach(classify -> {
            if ("0".equals(classify.getParentCode())) {//一级分类
                ConstsClassifyVo vo = new ConstsClassifyVo();
                BeanUtils.copyProperties(classify, vo);
                resultMap.put(classify.getCode(), vo);
            } else {//二级分类
                if (resultMap.get(classify.getParentCode()) != null) {
                    resultMap.get(classify.getParentCode()).getSubClassifyList().add(classify);
                }
            }
        });

        resultMap.forEach((key, value) -> classifyVos.add(value));
        return classifyVos;
    }

    /**
     * 为分类课程设置课程推荐
     *
     * @param classifyVos
     */
    @Override
    public void prepareRecommendCourse(List<ConstsClassifyVo> classifyVos) {
        if (CollectionUtils.isNotEmpty(classifyVos)) {
            classifyVos.forEach(classifyVo -> {
                CourseQueryDto dto = new CourseQueryDto();
                dto.setCount(5);
                dto.setClassify(classifyVo.getCode());//分类code
                dto.descSortField("weight");

                List<Course> courses = courseService.queryList(dto);
                if (CollectionUtils.isNotEmpty(courses)) {
                    classifyVo.setRecommendCourseList(courses);
                }
            });
        }
    }
}
