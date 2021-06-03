package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

/**
 * TravelgroupService
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-26
 * @Description:
 */
public interface TravelgroupService {
    void add(Integer[] travelItemIds, TravelGroup travelGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    List<TravelGroup> findAll();


}

