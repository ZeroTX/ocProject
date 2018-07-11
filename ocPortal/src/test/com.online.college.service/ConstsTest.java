package com.online.college.service;

import com.online.college.common.web.SpringBeanFactory;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by tx on 2018/7/11.
 */
public class ConstsTest extends TestCase {

    public void testCarousels() {
        IConstsSiteCarouselService constsSiteCarouselService = (IConstsSiteCarouselService) SpringBeanFactory.getBean("constsSiteCarouselServiceImpl");
        List<ConstsSiteCarousel> constsSiteCarousels = constsSiteCarouselService.queryCarousels(4);
        System.out.println(constsSiteCarousels);
    }
}
