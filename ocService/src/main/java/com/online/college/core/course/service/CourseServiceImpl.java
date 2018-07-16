package com.online.college.core.course.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.CourseEnum;
import com.online.college.core.course.dao.CourseDao;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tx on 2018/7/16.
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 根据id获取
     *
     * @param id
     */
    @Override
    public Course getById(Long id) {
        return null;
    }

    /**
     * 获取所有
     *
     * @param queryEntity
     */
    @Override
    public List<Course> queryList(CourseQueryDto queryEntity) {
        if (queryEntity.getOnsale() == null) {
            queryEntity.setOnsale(CourseEnum.ONSALE.getValue());
        }
        return courseDao.queryList(queryEntity);
    }

    /**
     * 分页获取
     *
     * @param queryEntity
     * @param page
     */
    @Override
    public TailPage<Course> queryPage(Course queryEntity, TailPage<Course> page) {
        return null;
    }

    /**
     * 创建
     *
     * @param entity
     */
    @Override
    public void createSelectivity(Course entity) {

    }

    /**
     * 根据id 进行可选性更新
     *
     * @param entity
     */
    @Override
    public void updateSelectivity(Course entity) {

    }

    /**
     * 物理删除
     *
     * @param entity
     */
    @Override
    public void delete(Course entity) {

    }

    /**
     * 逻辑删除
     *
     * @param entity
     */
    @Override
    public void deleteLogic(Course entity) {

    }
}
