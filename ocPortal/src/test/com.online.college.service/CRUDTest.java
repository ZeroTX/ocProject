package com.online.college.service;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.SpringBeanFactory;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import junit.framework.TestCase;

/**
 * Created by tx on 2018/7/6.
 */
public class CRUDTest extends TestCase {

    public void testCreate() {
        IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
        AuthUser user = new AuthUser();
        user.setUsername("test2");
        user.setPassword("test2");
        user.setRealname("用户2");
        authUserService.createSelectivity(user);
        System.out.println(user.getId());
    }

    public void testQueryById() {
        IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
        AuthUser user = authUserService.getById(30L);
        System.out.println(user.getUsername());
    }

    public void testPage(){
        IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
        TailPage<AuthUser> page = new TailPage<>();
        page.setPageNum(2);
        page = authUserService.queryPage(new AuthUser(),page);
        page.getItems().forEach(p-> System.out.println(p.getUsername()));
    }

    public void testDelete(){
        IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
        AuthUser user = authUserService.getById(7L);
        if (user!=null){
            authUserService.delete(user);
        }

    }

}
