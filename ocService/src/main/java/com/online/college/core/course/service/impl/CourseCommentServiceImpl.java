package com.online.college.core.course.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.dao.CourseCommentDao;
import com.online.college.core.course.domain.CourseComment;
import com.online.college.core.course.service.ICourseCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tx on 2018/8/15.
 */
@Service
public class CourseCommentServiceImpl implements ICourseCommentService {
    @Resource
    private CourseCommentDao courseCommentDao;
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public CourseComment getById(Long id) {
        return courseCommentDao.getById(id);
    }

    /**
     * 获取所有
     *
     * @param courseComment
     * @return
     */
    @Override
    public List<CourseComment> queryAll(CourseComment courseComment) {
        return courseCommentDao.queryAll(courseComment);
    }

    /**
     * 分页查询
     *
     * @param courseComment
     * @param page
     * @return
     */
    @Override
    public TailPage<CourseComment> queryPage(CourseComment courseComment, TailPage<CourseComment> page) {
        Integer total = courseCommentDao.getTotalItemsCount(courseComment);
        List<CourseComment> courseComments = courseCommentDao.queryPage(courseComment,page);
        page.setItemsTotalCount(total);
        page.setItems(courseComments);
        return page;
    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void create(CourseComment entity) {
        courseCommentDao.create(entity);
    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void createSelectivity(CourseComment entity) {
        courseCommentDao.createSelectivity(entity);
    }

    /**
     * 根据id更新
     *
     * @param courseComment
     */
    @Override
    public void update(CourseComment courseComment) {
        courseCommentDao.update(courseComment);
    }

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    @Override
    public void updateSelectivity(CourseComment entity) {
        courseCommentDao.updateSelectivity(entity);
    }

    /**
     * 物理删除
     *
     * @param entity
     */
    @Override
    public void delete(CourseComment entity) {
        courseCommentDao.delete(entity);
    }

    /**
     * 逻辑删除
     *
     * @param entity
     */
    @Override
    public void deleteLogic(CourseComment entity) {
        courseCommentDao.deleteLogic(entity);
    }
}
