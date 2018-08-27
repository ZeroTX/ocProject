package com.online.college.core.user.service;

import java.util.List;
import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserFollows;


public interface IUserFollowsService {

	/**
	*根据id获取
	**/
	public UserFollows getById(Long id);

	/**
	*获取�?�?
	**/
	public List<UserFollows> queryAll(UserFollows queryEntity);

	/**
	*分页获取
	**/
	public TailPage<UserFollows> queryPage(UserFollows queryEntity, TailPage<UserFollows> page);

	/**
	*创建
	**/
	public void createSelectivity(UserFollows entity);

	/**
	*根据id更新
	**/
	public void update(UserFollows entity);

	/**
	*根据id 进行可�?��?�更�?
	**/
	public void updateSelectivity(UserFollows entity);

	/**
	*物理删除
	**/
	public void delete(UserFollows entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserFollows entity);



}

