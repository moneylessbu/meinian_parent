package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * TravelItemDao
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-25
 * @Description:
 */
public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    void deleteById(Integer id);

    Long findCountByTravelItemItemId(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    List<TravelItem> findTravelItemListById(Integer id);

}

