package com.online.college.portal;

import com.online.college.common.App;
import com.online.college.common.CommonDemo;
import com.online.college.common.oc.OCDemo;

import com.online.college.test.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Administrator on 2018/6/30.
 */
@Controller
@RequestMapping()
public class TestController {
    @Autowired
    CommonDemo commonDemo;
    @Autowired
    OCDemo ocDemo;
    @Autowired
    TestDao testDao;
    @RequestMapping("/indexs")
    public String test(Model model){
        System.out.println(ocDemo.getString3());
        System.out.println(commonDemo.getString2());
        System.out.println(App.getString());
        System.out.println("进来了");
        model.addAttribute("info","开始了");
       Map<String,Object> map = testDao.testQuery();
        System.out.println(map.toString());
        return "/index";
    }
}
