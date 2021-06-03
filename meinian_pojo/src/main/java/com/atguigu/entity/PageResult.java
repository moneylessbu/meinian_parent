package com.atguigu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * PageResult
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-05-25
 * @Description:
 */
public class PageResult implements Serializable {
    //  总记录数
    private Long total;
    // 每个页面需要展示的数据
    private List rows;

    public PageResult(Long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}