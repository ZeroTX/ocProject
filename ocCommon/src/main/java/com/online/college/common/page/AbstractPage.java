package com.online.college.common.page;

import com.online.college.common.util.BeanUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by tx on 2018/7/5.
 */
public abstract class AbstractPage<E> implements Page<E> {
    public static final int DEFAULT_FIRST_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected int pageNum = DEFAULT_FIRST_PAGE_NUM;

    protected int itemsTotalCount = 0;//总记录数
    protected int pageTotalCount = 0;//总页数
    protected List<E> items;
    protected boolean fitstPage;
    protected boolean lastPage;
    protected int startIndex;

    private String sortField = "update_time";
    private String sortDirection = "DESC";//排序方向

    @Override
    public int getFirstPageNum() {
        return DEFAULT_FIRST_PAGE_NUM;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum < DEFAULT_FIRST_PAGE_NUM) {
            pageNum = DEFAULT_FIRST_PAGE_NUM;
        }
        this.pageNum = pageNum;
    }

    @Override
    public int getItemsTotalCount() {
        return itemsTotalCount;
    }

    public void setItemsTotalCount(int itemsTotalCount) {
        this.itemsTotalCount = itemsTotalCount;
        if (itemsTotalCount%this.pageSize==0){
            this.pageTotalCount = itemsTotalCount/this.pageSize;
        }else {
            this.pageTotalCount =itemsTotalCount/this.pageSize+1;
        }
        if (this.pageNum>this.pageTotalCount){
            this.pageNum = DEFAULT_FIRST_PAGE_NUM;
        }
        if (this.itemsTotalCount<=this.pageSize){
            this.fitstPage = true;
            this.lastPage = true;
        }
    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    @Override
    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        if (items == null) {
            items = Collections.emptyList();
        }
        this.items = new ArrayList<>(items);
        this.fitstPage = this.pageNum == this.DEFAULT_FIRST_PAGE_NUM;
        this.lastPage = this.pageNum == this.pageTotalCount;
    }

    public boolean isFitstPage() {
        fitstPage = (getPageNum()<=getFirstPageNum());
        return fitstPage;
    }

    public void setFitstPage(boolean fitstPage) {
        this.fitstPage = fitstPage;
    }

    @Override
    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getStartIndex() {
        this.startIndex = (this.pageNum-1)*this.pageSize;
        if (this.startIndex<=0){
            this.startIndex = 0;
        }
        return startIndex;
    }
    /**
     * 按照sortField升序
     * @param sortField：指java bean中的属性
     */
    public void ascSortField(String sortField){
        if (StringUtils.isNotEmpty(sortField)){
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = "ASC";
        }
    }
    /**
     * 按照sortField降序
     * @param sortField ：指java bean中的属性
     */
    public void descSortField(String sortField) {
        if(StringUtils.isNotEmpty(sortField)){
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = " DESC ";
        }
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
