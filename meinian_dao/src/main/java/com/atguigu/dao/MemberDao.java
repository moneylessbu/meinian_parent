package com.atguigu.dao;

import com.atguigu.pojo.Member;

/**
 * MemberDao
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-31
 * @Description:
 */
public interface MemberDao {

    // 根据手机号查询会员信息（唯一）
    Member findByTelephone(String telephone);

    //添加会员
    void add(Member member);

    Integer findMemberCountBeforeDate(String regTime);
}

