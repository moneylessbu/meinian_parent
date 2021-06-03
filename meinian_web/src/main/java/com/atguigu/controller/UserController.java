package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.SecurityConstants;

/**
 * @Author:步 Modified By:
 * @Date:Created in 16:08 2021/6/2
 * @Description：
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;

    //获取当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername(){
        try {
           User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userName = user.getUsername();
            //根据用户名查询用户信息
            com.atguigu.pojo.User user1 = userService.findUserByUsername(userName);
           return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user1);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);

        }

    }

}
