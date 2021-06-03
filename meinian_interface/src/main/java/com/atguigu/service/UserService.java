package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @Author:步 Modified By:
 * @Date:Created in 10:40 2021/6/2
 * @Description：
 */
public interface UserService {
    User findUserByUsername(String username);
}
