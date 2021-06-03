package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

/**
 * OrderService
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-31
 * @Description:
 */
public interface OrderService {

    Result add(Map map);

    Map findById(Integer id) throws Exception;
}

