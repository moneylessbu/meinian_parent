package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import com.atguigu.utils.RedisMessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * OrderMobileController
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-31
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderMobileController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        // 用户输入的手机号码
        String telephone = (String) map.get("telephone");
        // 用户输入的验证码
        String validateCode = (String) map.get("validateCode");

        String code =  jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);

        if (code==null || !code.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result =null;
        map.put("orderType", Order.ORDERTYPE_WEIXIN);
        result = orderService.add(map);
        return result;

    }
}