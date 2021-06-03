package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;

import java.util.List;

/**
 * SetmealService
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-28
 * @Description:
 */
public interface SetmealService {
    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}

