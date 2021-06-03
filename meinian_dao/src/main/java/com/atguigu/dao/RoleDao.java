package com.atguigu.dao;

import com.atguigu.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author:步 Modified By:
 * @Date:Created in 11:11 2021/6/2
 * @Description：
 */
@Repository  //用于将接口bean交给spring管理
public interface RoleDao {

    Set<Role> findRolesByUserId(Integer userId);
}
