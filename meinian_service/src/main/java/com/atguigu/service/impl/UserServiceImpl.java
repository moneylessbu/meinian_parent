package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;

/**
 * @Author:步 Modified By:
 * @Date:Created in 11:10 2021/6/2
 * @Description：
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByUsername(String username) {

        User user = userDao.findUserByUsername(username);
        return user;
    }
}
