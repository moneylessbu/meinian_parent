package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author:步 Modified By:
 * @Date:Created in 18:14 2021/6/1
 * @Description：
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Map map = null;

        try {
            //查询成功
            map = orderService.findById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,map);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);

        }

    }
}
