package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.utils.RedisMessageConstant;
import com.atguigu.utils.ValidateCodeUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * ValidateCodeController
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-31
 * @Description:验证码
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
     /*   // 生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("发送短信验证码:" + code);
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,
                5*60,code+"");
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);*/


        //生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);

        System.out.println("发送短信验证码:" + code);

        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_ORDER,5*60,code.toString());

        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);

    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        System.out.println("发送短信验证码:" + code);
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN, 5*60, code+"");
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}