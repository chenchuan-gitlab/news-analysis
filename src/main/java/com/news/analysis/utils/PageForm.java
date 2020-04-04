package com.news.analysis.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PageForm
 */
public class PageForm {
    /**
     * 第几页
     */
    private int pageNum = 1;

    /**
     * 每页查询多少条
     */
    private int pageSize = 10;

    /**
     * 总条数
     */
    private long totalCount = 0;

    /**
     * 总页数
     */
    private int totalPage = 0;

    /**
     * 列表数据集合
     */
    private List rows = new ArrayList();

    /**
     * 扩展参数
     */
    private Map<String,Object> extend = null;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getRows() {
        return rows;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
