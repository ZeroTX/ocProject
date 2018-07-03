package com.online.college.common.storage;

/**
 * Created by tx on 2018/7/3.
 */
public enum FileType {
    JPEG("E"),
    PNG("S"),
    GIF("2");

    private String value = "";

    private FileType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
