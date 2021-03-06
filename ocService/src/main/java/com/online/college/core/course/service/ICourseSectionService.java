package com.online.college.core.course.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseSection;

import java.util.List;


public interface ICourseSectionService {

	/**
	*根据id获取
	**/
	public CourseSection getById(Long id);

	/**
	*获取所有
	**/
	public List<CourseSection> queryAll(CourseSection queryEntity);
	
	/**
	 * 获取课程章最大的sort
	 */
	public Integer getMaxSort(Long courseId);
	
	/**
	*分页获取
	**/
	public TailPage<CourseSection> queryPage(CourseSection queryEntity, TailPage<CourseSection> page);

	/**
	*创建
	**/
	public void createSelectivity(CourseSection entity);
	
	/**
	*批量创建
	**/
	public void createList(List<CourseSection> entityList);

	/**
	*根据id更新
	**/
	public void update(CourseSection entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(CourseSection entity);

	/**
	*物理删除
	**/
	public void delete(CourseSection entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(CourseSection entity);



}

