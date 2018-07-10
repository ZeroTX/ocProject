package com.online.college.common.storage;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Base64;

/**
 * Created by tx on 2018/7/3.
 */
public class FileTypeHelper {
    /**
     * 判断文件格式 支持 gif，png，jpeg
     * @param buff
     * @return
     */
    public static FileType getType(byte[] buff){
        //spring断言的应用
        Assert.isTrue(buff!=null&&buff.length>28);
        byte[] bytes = Arrays.copyOfRange(buff,0,28);
        String magic = bytesToHex(bytes);
        for (FileType fileType: FileType.values()) {
            if (magic.startsWith(fileType.getValue())){
                return fileType;
            }
        }
        return null;
    }

    /**
     * byte转16进制字符串
     * @param src
     * @return
     */
    private static String bytesToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) return null;
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
