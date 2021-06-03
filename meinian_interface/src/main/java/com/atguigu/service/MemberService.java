package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.List;

/**
 * @Author:步 Modified By:
 * @Date:Created in 20:03 2021/6/1
 * @Description：
 */
public interface MemberService {

    void add(Member member);

    //验证用户是否为会员
    Member findByTelephone(String telephone);

    List<Integer> findMemberCountByMonth(List<String> lists);
}
