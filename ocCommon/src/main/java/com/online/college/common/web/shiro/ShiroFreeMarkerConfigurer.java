package com.online.college.common.web.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * Created by tx on 2018/8/30.
 * shiro freemarker 整合
 */
public class ShiroFreeMarkerConfigurer extends FreeMarkerConfigurer{

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
       super.afterPropertiesSet();
       this.getConfiguration().setSharedVariable("shiro",new ShiroTags());

    }

}
