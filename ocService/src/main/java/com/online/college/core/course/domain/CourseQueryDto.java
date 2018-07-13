package com.online.college.core.course.domain;

import com.online.college.common.util.BeanUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by tx on 2018/7/13.
 */
public class CourseQueryDto extends Course {
    private static final long serialVersionUID = 6928526481007198051L;

    private String sortField;

    private String sortDirection = "DESC";

    private Integer start = 0;//limit开始

    private Integer end;//limit结束

    private Integer count;//数量


    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        if (null != this.count){
            if (null == start){
                start = 0;
            }
            this.end = this.start + this.count;
        }
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 按照sortField升序
     *
     * @param sortField
     */
    public void ascSortField(String sortField) {
        if (StringUtils.isNotEmpty(sortField)) {
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = "ASC";
        }
    }

    /**
     * 按照sortField降序
     *
     * @param sortField
     */
    public void descSortField(String sortField) {
        if (StringUtils.isNotEmpty(sortField)) {
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = "DESC";
        }
    }

}
