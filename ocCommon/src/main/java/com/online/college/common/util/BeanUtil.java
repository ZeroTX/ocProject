package com.online.college.common.util;


import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by tx on 2018/7/5.
 */
public class BeanUtil {
    private static Logger log = Logger.getLogger(BeanUtil.class);

    /**
     * 将map list转为bean list
     *
     * @param clazz
     * @param mapList
     * @param <T>
     * @return
     */
    public static <T> List<T> mapListToBeanList(Class<T> clazz, List<Map<String, Object>> mapList) {
        List<T> rsList = new ArrayList<>();
        mapList.forEach((m) -> rsList.add(mapToBean(clazz, m)));
        return rsList;
    }


    public static <T> T mapToBean(Class<T> clazz, Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        T instance = null;
        try {
            instance = clazz.newInstance();
            if (instance == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("类型实例化对象失败,类型:" + clazz);
            return null;
        }
        Map<String, Object> newMap = new HashMap<>();
        map.entrySet().forEach(m -> newMap.put(m.getKey().toLowerCase(), m.getValue()));
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String mname = method.getName();
            if (mname.startsWith("set")) {
                Class[] ptypes = method.getParameterTypes();
                Object v = newMap.get(mname);
                if (v != null && ptypes.length == 1) {
                    try {
                        method.invoke(instance, v);
                    } catch (Exception e) {
                        log.error("属性值装入失败,装入方法：" + ptypes + "."
                                + method.getName() + ".参数类型" + ptypes[0]
                                + ";装入值的类型:" + v.getClass());
                    }
                }
            }
        }
        return instance;
    }

    /**
     * 转换有下划线的String，第一个字母大写，下划线的后一个字母大写
     *
     * @param str
     * @return
     */
    public static String columnToField2(String str) {
        String[] arr = str.split("_");
        if (arr != null && arr.length > 1) {
            String rstStr = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1);
            for (int i = 1; i < arr.length; i++) {
                rstStr += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
            }
            return rstStr;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 属性转列名转换将第一个字母大写变成小写，并在前面加下划线
     * @param str
     * @return
     */
    public static String fieldToColumn(String str){
        char[] chars = str.toCharArray();
        String rstStr = "";
        for (int i=0;i<chars.length;i++){
            if (chars[i]>64&&chars[i]<94){
                rstStr+=("_"+chars[i]).toLowerCase();
            }else {
                rstStr+=chars[i];
            }
        }
        return rstStr;
    }
}
