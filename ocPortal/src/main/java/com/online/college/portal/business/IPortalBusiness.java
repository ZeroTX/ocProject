package com.online.college.portal.business;

import com.online.college.portal.vo.ConstsClassifyVo;

import java.util.List;

/**
 * Created by tx on 2018/7/16.
 */
public interface IPortalBusiness {
    /**
     * 获取所有，包括一级，二级
     * @return
     */
    List<ConstsClassifyVo> queryAllClassify();

    /**
     * 为分类课程设置课程推荐
     * @param classifyVos
     */
    void prepareRecommendCourse(List<ConstsClassifyVo> classifyVos);

}
