package com.atguigu.dao;

import com.atguigu.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Author:步 Modified By:
 * @Date:Created in 11:11 2021/6/2
 * @Description：
 */
@Repository
public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);

}
