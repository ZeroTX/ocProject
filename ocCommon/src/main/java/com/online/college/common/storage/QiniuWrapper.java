package com.online.college.common.storage;


import com.online.college.common.util.PropertiesUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by tx on 2018/7/3.
 * 七牛SDK封装
 */
public class QiniuWrapper {
    private static final Logger logger = LoggerFactory.getLogger(QiniuWrapper.class);

    private static final String CONFIG_BUCKET = "qiniu.bucket";
    private static final String CONFIG_AK = "qiniu.accessKey";
    private static final String CONFIG_SK = "qiniu.secretKey";
    private static final String CONFIG_CDN = "qiniu.cdns";

    private static final Auth auth;
    private static final UploadManager uploadManager;
    private static final String bucketName;
    private static final List<String> cdns;

    static {
        Properties properties = PropertiesUtil.getDefaultProperties();
        properties.getProperty(CONFIG_BUCKET);
        auth = Auth.create(properties.getProperty(CONFIG_AK), properties.getProperty(CONFIG_SK));
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
        bucketName = properties.getProperty(CONFIG_BUCKET);
        String cdn = properties.getProperty(CONFIG_CDN);
        cdns = Arrays.asList(cdn.split(","));
    }

    /**
     * 获取文件上传token
     */
    public static String getUploadToken() {
        return auth.uploadToken(bucketName);
    }

    /**
     * 获取文件更新Token
     *
     * @param key
     * @return
     */
    public static String getUploadToken(String key) {
        return auth.uploadToken(bucketName, key);
    }

    /**
     * 上传文件
     *
     * @param data   二进制格式的文件内容
     * @param key    文件在七牛中的key
     * @param update 是否是更新
     * @return
     */
    public static String upload(byte[] data, String key, boolean update) {
        String token = update ? getUploadToken(key) : getUploadToken();
        try {
            Response response = uploadManager.put(data, getFullKey(data, key), token);
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
            return ret.key;
        } catch (QiniuException e) {
            logger.error("upload file to qiniu cloud storage failed", e);
        }
        return "";
    }

    /**
     * 批量获取key图片
     * @param keys 逗号隔开的多个key
     * @param model
     * @return
     */
    public static List<String> getUrls(String keys,String model){
        List<String> list = null;
        if (org.apache.commons.lang.StringUtils.isNotBlank(keys)){
            list = new ArrayList<>();
            for (String key:keys.split(",")) {
                list.add(getUrl(key,model,3600));
            }
        }
        return list;
    }
    public static String getUrl(String key){
        if(!StringUtils.isEmpty(key)){
            return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key);
        }
        return null;
    }
    /**
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public static String getUrl(String key,long expires){
        if(!StringUtils.isEmpty(key)){
            long time = System.currentTimeMillis()/1000+expires;
            return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key,time);
        }
        return null;
    }
    public static String getUrl(String key, String model, long expires) {
        if (StringUtils.hasText(model)) {
            return auth.privateDownloadUrl("http://" + getCDN() + "/@" + key + "?" + model, expires);
        } else {
            return auth.privateDownloadUrl("http://" + getCDN() + "/@" + key, expires);
        }
    }

    public static String getUrl(String key,String model){
        return getUrl(key, model, 3600);
    }
    private static String getFullKey(byte[] data, String key) {
        FileType type = FileTypeHelper.getType(data);
        if (type != null) {
            return key + "." + type.name().toLowerCase();
        } else return key;
    }

    /**
     * 从多条CDN路径中随机选择一条
     *
     * @return
     */
    private static String getCDN() {
        Random random = new Random();
        int num = random.nextInt(cdns.size());
        return cdns.get(num);
    }
}
