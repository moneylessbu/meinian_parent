package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * TravelgroupDao
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-26
 * @Description:
 */
public interface TravelgroupDao {
    void add(TravelGroup travelGroup);

    void setTravelgroupAndTravelitem(Map map);

    Page<TravelGroup> findPage(String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupListById(Integer id);

}

