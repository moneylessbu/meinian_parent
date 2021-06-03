package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * SetmealDao
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-28
 * @Description:
 */
public interface SetmealDao {
    void add(Setmeal setmeal);

    void setSetmealAndTravelgroup(Map map);

    Page<Setmeal> findPage(String queryString);

    List<Setmeal> getSetmeal();


    Setmeal findById(Integer id);

}

