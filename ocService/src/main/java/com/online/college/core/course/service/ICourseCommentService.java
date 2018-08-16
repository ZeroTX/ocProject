package com.online.college.core.course.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseComment;

import java.util.List;

/**
 * Created by tx on 2018/8/15.
 */
public interface ICourseCommentService {
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    CourseComment getById(Long id);

    /**
     * 获取所有
     *
     * @param courseComment
     * @return
     */
    List<CourseComment> queryAll(CourseComment courseComment);

    /**
     * 分页查询
     *
     * @param courseComment
     * @param page
     * @return
     */
    TailPage<CourseComment> queryPage(CourseComment courseComment, TailPage<CourseComment> page);

    /**
     * 根据id更新
     *
     * @param courseComment
     */
    void update(CourseComment courseComment);

    /**
     * 根据id 进行可选性更新
     **/
    void updateSelectivity(CourseComment entity);

    /**
     * 物理删除
     **/
    void delete(CourseComment entity);

    /**
     * 逻辑删除
     **/
    void deleteLogic(CourseComment entity);
}
