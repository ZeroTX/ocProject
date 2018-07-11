package com.online.college.core.consts.service.impl;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.consts.dao.ConstsSiteCarouselDao;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tx on 2018/7/11.
 */
@Service
public class ConstsSiteCarouselServiceImpl implements IConstsSiteCarouselService {
    @Resource
    private ConstsSiteCarouselDao constsSiteCarouselDao;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public ConstsSiteCarousel getById(Long id) {
        return null;
    }

    /**
     * 查询轮播
     *
     * @param count
     * @return
     */
    @Override
    public List<ConstsSiteCarousel> queryCarousels(Integer count) {
        List<ConstsSiteCarousel> constsSiteCarousels = constsSiteCarouselDao.queryCarousels(count);
        constsSiteCarousels.forEach(c -> {
            String picture = c.getPicture();
            c.setPicture(QiniuStorage.getUrl(picture));
        });
        return constsSiteCarousels;
    }
}
