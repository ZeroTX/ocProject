package com.online.college.common.storage;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by tx on 2018/7/4.
 */
public class QiniuImg implements Serializable {
    private static final long serialVersionUID = -68217983332179128L;

    private String key;//七牛返回的key
    private String url;//原图片的url

    private String url16;//
    private String url32;//
    private String url48;//
    private String url64;//
    private String url128;//
    private String url256;//
    private String url512;//


    //七牛上传key，然后七牛会返回一个key，将返回的key保存到数据库
    public String getUploadKey(){
        return QiniuKeyGenerator.generateKey();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        if (StringUtils.isEmpty(key)){
            return null;
        }
        if (this.url==null){
            this.url = QiniuWrapper.getUrl(key);
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl16() {
        return url16;
    }

    public void setUrl16(String url16) {
        this.url16 = url16;
    }

    public String getUrl32() {
        return url32;
    }

    public void setUrl32(String url32) {
        this.url32 = url32;
    }

    public String getUrl48() {
        return url48;
    }

    public void setUrl48(String url48) {
        this.url48 = url48;
    }

    public String getUrl64() {
        return url64;
    }

    public void setUrl64(String url64) {
        this.url64 = url64;
    }

    public String getUrl128() {
        return url128;
    }

    public void setUrl128(String url128) {
        this.url128 = url128;
    }

    public String getUrl256() {
        return url256;
    }

    public void setUrl256(String url256) {
        this.url256 = url256;
    }

    public String getUrl512() {
        return url512;
    }

    public void setUrl512(String url512) {
        this.url512 = url512;
    }
}
