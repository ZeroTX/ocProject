package com.online.college.service;

import com.online.college.common.web.SpringBeanFactory;
import com.online.college.test.dao.TestDao;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import java.util.Map;


/**
 * Created by tx on 2018/7/3.
 */
public class AppTest extends TestCase {
    Logger log = Logger.getLogger(AppTest.class);

    public void testApp(){
        TestDao testDao = (TestDao) SpringBeanFactory.getBean("testDao");
        Map<String,Object> map = testDao.testQuery();
        System.out.println(map.toString());
    }
}
