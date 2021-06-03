package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @Author:步 Modified By:
 * @Date:Created in 11:11 2021/6/2
 * @Description：
 */
public interface UserDao {
    User findUserByUsername(String username);
}
