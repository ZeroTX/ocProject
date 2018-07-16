package com.online.college.core.course.dao;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;

import java.util.List;

/**
 * Created by tx on 2018/7/16.
 */
public interface CourseDao {
    /**
     * 根据id获取
     * @param id
     * @return
     */
    Course getById(Long id);

    /**
     * 根据条件获取所有
     * @param queryEntity 查询条件
     * @return
     */
    List<Course> queryList(CourseQueryDto queryEntity);

    /**
     * 获取总数量
     * @param queryEntity
     * @return
     */
    Integer getTotalItemsCount(Course queryEntity);

    /**
     * 分页查询
     * @param queryEntity
     * @param page
     * @return
     */
    List<Course> queryPage(Course queryEntity, TailPage<Course> page);

    void create(Course entity);

    void createSelectivity(Course entity);

    void update(Course entity);

    void updateSelectivity(Course entity);

    void delete(Course entity);

    void deleteLogic(Course entity);
}
