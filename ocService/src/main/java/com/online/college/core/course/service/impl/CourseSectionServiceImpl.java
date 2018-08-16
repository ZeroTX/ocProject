package com.online.college.core.course.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.dao.CourseSectionDao;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tx on 2018/8/15.
 */
@Service
public class CourseSectionServiceImpl implements ICourseSectionService {
    @Resource
    private CourseSectionDao courseSectionDao;
    /**
     * 根据id获取
     *
     * @param id
     */
    @Override
    public CourseSection getById(Long id) {
        return courseSectionDao.getById(id);
    }

    /**
     * 获取所有
     *
     * @param queryEntity
     */
    @Override
    public List<CourseSection> queryAll(CourseSection queryEntity) {
        return courseSectionDao.queryAll(queryEntity);
    }

    /**
     * 获取课程章最大的sort
     *
     * @param courseId
     */
    @Override
    public Integer getMaxSort(Long courseId) {
        return courseSectionDao.getMaxSort(courseId);
    }

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     */
    @Override
    public TailPage<CourseSection> queryPage(CourseSection queryEntity, TailPage<CourseSection> page) {
        Integer total = courseSectionDao.getTotalItemsCount(queryEntity);
        List<CourseSection> courseSections = courseSectionDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(total);
        page.setItems(courseSections);
        return page;
    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void createSelectivity(CourseSection entity) {

    }

    /**
     * 批量创建
     *
     * @param entityList
     */
    @Override
    public void createList(List<CourseSection> entityList) {

    }

    /**
     * 根据id更新
     *
     * @param entity
     */
    @Override
    public void update(CourseSection entity) {

    }

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    @Override
    public void updateSelectivity(CourseSection entity) {

    }

    /**
     * 物理删除
     *
     * @param entity
     */
    @Override
    public void delete(CourseSection entity) {

    }

    /**
     * 逻辑删除
     *
     * @param entity
     */
    @Override
    public void deleteLogic(CourseSection entity) {

    }
}
