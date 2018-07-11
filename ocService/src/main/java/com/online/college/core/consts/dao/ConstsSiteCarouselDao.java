package com.online.college.core.consts.dao;

import com.online.college.common.page.Page;
import com.online.college.common.page.TailPage;
import com.online.college.core.consts.domain.ConstsSiteCarousel;

import java.util.List;

/**
 * Created by tx on 2018/7/11.
 */
public interface ConstsSiteCarouselDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    ConstsSiteCarousel getById(Long id);

    /**
     * 获取轮播
     * @param count 轮播图片数
     * @return
     */
    List<ConstsSiteCarousel> queryCarousels(Integer count);

    /**
     * 查询所有
     * @return
     */
    List<ConstsSiteCarousel> queryAll();

    /**
     * 分页总量
     * @param queryEntity
     * @return
     */
    Integer getTotalItemsCount(ConstsSiteCarousel queryEntity);

    /**
     * 分页查询
     * @param queryEntity
     * @param page
     * @return
     */
    List<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page);

    /**
     * 创建新纪录
     * @param entity
     */
    void create(ConstsSiteCarousel entity);

    /**
     * 创建新纪录
     * @param entity
     */
    void createSelectivity(ConstsSiteCarousel entity);

    /**
     * 通过id更新
     * @param entity
     */
    void update(ConstsSiteCarousel entity);

    /**
     * 选择更新
     * @param entity
     */
    void updateSelectivity(ConstsSiteCarousel entity);

    /**
     * 物理删除
     * @param entity
     */
    void delete(ConstsSiteCarousel entity);

    /**
     * 逻辑删除
     * @param entity
     */
    void deleteLogic(ConstsSiteCarousel entity);

}
