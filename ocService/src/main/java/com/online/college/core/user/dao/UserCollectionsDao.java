package com.online.college.core.user.dao;

import java.util.List;
import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserCollections;


public interface UserCollectionsDao {

	/**
	*根据id获取
	**/
	public UserCollections getById(Long id);

	/**
	*获取�?�?
	**/
	public List<UserCollections> queryAll(UserCollections queryEntity);

	/**
	*获取总数�?
	**/
	public Integer getTotalItemsCount(UserCollections queryEntity);

	/**
	*分页获取
	**/
	public List<UserCollections> queryPage(UserCollections queryEntity, TailPage<UserCollections> page);

	/**
	*创建新记�?
	**/
	public void create(UserCollections entity);
	
	/**
	*创建新记�?
	**/
	public void createSelectivity(UserCollections entity);
	
	/**
	*根据id更新
	**/
	public void update(UserCollections entity);

	/**
	*根据id选择性更新自�?
	**/
	public void updateSelectivity(UserCollections entity);

	/**
	*物理删除
	**/
	public void delete(UserCollections entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserCollections entity);



}

