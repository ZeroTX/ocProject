package com.online.college.core.consts.service;

import com.online.college.core.consts.domain.ConstsSiteCarousel;

import java.util.List;

/**
 * Created by tx on 2018/7/11.
 */
public interface IConstsSiteCarouselService {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    ConstsSiteCarousel getById(Long id);

    /**
     * 查询轮播
     * @param count
     * @return
     */
    List<ConstsSiteCarousel> queryCarousels(Integer count);
}
