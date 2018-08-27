package com.online.college.core.course;

/**
 * Created by tx on 2018/7/16.
 * 课程使用的枚举
 */
public enum CourseEnum {
    FREE(1),//免费
    FREE_NOT(0),//付费

    ONSALE(1),//上架
    ONSALE_NOT(0),//下架

    COLLECTION_CLASSIFY_COURSE(1);//课程收藏
    private Integer value;

    CourseEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
