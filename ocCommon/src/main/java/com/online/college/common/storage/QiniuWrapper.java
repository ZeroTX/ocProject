package com.online.college.common.storage;


import com.online.college.common.util.PropertiesUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by tx on 2018/7/3.
 * 七牛SDK封装
 */
public class QiniuWrapper {
    private static final Logger logger =  LoggerFactory.getLogger(QiniuWrapper.class);

    private static final String CONFIG_BUCKET="qiniu.bucket";
    private static final String CONFIG_AK="qiniu.accessKey";
    private static final String CONFIG_SK="qiniu.secretKey";
    private static final String CONFIG_CDN="qiniu.cdns";

    private static final Auth auth;
    private static final UploadManager uploadManager;
    private static final String bucketName;
    private static final List<String> cdns;
    static {
        Properties properties = PropertiesUtil.getDefaultProperties();
        properties.getProperty(CONFIG_BUCKET);
        auth = Auth.create(properties.getProperty(CONFIG_AK),properties.getProperty(CONFIG_SK));
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
        bucketName = properties.getProperty(CONFIG_BUCKET);
        String cdn = properties.getProperty(CONFIG_CDN);
        cdns = Arrays.asList(cdn.split(","));
    }
    /**
     * 获取文件上传token
     */
    public static String getUploadToken(){
        return auth.uploadToken(bucketName);
    }

    /**
     * 获取文件更新Token
     * @param key
     * @return
     */
    public static String getUploadToken(String key){
        return auth.uploadToken(bucketName,key);
    }

    public static String upload(byte[] data,String key,boolean update){
        String token = update?getUploadToken(key):getUploadToken();
        try {
            uploadManager.put(data,getFullKey(data,key),token);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String getFullKey(byte[] data,String key){
        FileType type = FileTypeHelper.getType(data);
        if (type!=null){
            return key+"."+type.name().toLowerCase();
        }else return key;
    }
}
