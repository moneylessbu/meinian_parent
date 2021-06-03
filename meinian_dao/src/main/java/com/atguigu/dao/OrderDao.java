package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * OrderDao
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-31
 * @Description:
 */
public interface OrderDao {

    Map findById(Integer id);

    List<Order> findByCondition(Order order);


    void add(Order order);
}

