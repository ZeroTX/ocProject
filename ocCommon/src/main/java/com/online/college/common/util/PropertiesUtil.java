package com.online.college.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by tx on 2018/7/3.
 */
public class PropertiesUtil {

    private static Map<String, Properties> propMap = new HashMap<>();
    /*默认的properties文件*/
    public static final String DEFAULT_PROPERTIES_FILE = "application.properties";

    /**
     * 根据文件路径读取配置文件到map
     *
     * @param file
     * @return
     */
    public static Properties getProperties(String file) {
        try {
            if (propMap.get(file) == null) {
                Properties properties = new Properties();
                properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(file));
                propMap.put(file, properties);
                return properties;
            } else {
                return propMap.get(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从默认配置文件中获取properties
     * @return
     */
    public static Properties getDefaultProperties(){
        return getProperties(DEFAULT_PROPERTIES_FILE);
    }

    /**
     * 从配置文件中读取信息
     * @param file
     * @param key
     * @return
     */
    public static Object getProperty(String file,String key){
        Properties prop = getProperties(file);
        if (prop!=null&&prop.get(key)!=null){
            return prop.get(key);
        }else{
            return null;
        }
    }
}
