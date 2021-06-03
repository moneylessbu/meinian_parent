package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;

import java.util.List;

/**
 * TravelItemService
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-25
 * @Description:
 */
public interface TravelItemService {
    void add(TravelItem travelItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

}

