package com.online.college.common.storage;

import com.online.college.common.util.CommonUtil;

import java.text.MessageFormat;

/**
 * Created by tx on 2018/7/4.
 * 生成七牛key
 */
public class QiniuKeyGenerator {
    public static final String KEY = "/{0}/{1}/{2}/{3}";// 多图片可以按照：/表名/字段名/业务值(refId)/时间戳 处理

    public static String generateKey(){
        return MessageFormat.format(KEY,"default","all","0", CommonUtil.getUID());
    }
}
