package com.online.college.common.page;

import java.util.List;

/**
 * Created by tx on 2018/7/5.
 */
public interface Page<E> extends Iterable<E> {
    /**
     * 第一页
     * @return
     */
    int getFirstPageNum();

    /**
     * 最后一页
     * @return
     */
    int getLastPageNum();

    /**
     * 当前页
     * @return
     */
    int getPageNum();

    /**
     * 分页大小
     * @return
     */
    int getPageSize();

    /**
     * 分页数据
     * @return
     */
    List<E> getItems();

    /**
     * 总页数
     * @return
     */
    int getItemsTotalCount();

    /**
     * 是第一页
     * @return
     */
    boolean isFirstPage();

    /**
     * 是最后一页
     * @return
     */
    boolean isLastPage();

    /**
     * 是否为空内容
     * @return
     */
    boolean isEmpty();
}
