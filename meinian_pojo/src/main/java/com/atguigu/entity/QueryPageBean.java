package com.atguigu.entity;

import java.io.Serializable;

/**
 * QueryPageBean
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-05-25
 * @Description:
 *
 * 客户端请求服务器
 */
public class QueryPageBean implements Serializable {
    // 当前页面
    private Integer currentPage;
    // 页面需要展示几条数据
    private Integer pageSize;
    // 查询条件
    private String queryString;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}