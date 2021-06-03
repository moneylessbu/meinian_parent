package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import com.atguigu.utils.DateUtils;
import com.atguigu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:步 Modified By:
 * @Date:Created in 20:04 2021/6/1
 * @Description：
 */

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public void add(Member member) {
        if (member.getPassword() !=null){
            member.setPassword(MD5Utils.md5(member.getPassword()));

        }
    }

    @Override
    public Member findByTelephone(String telephone) {

        return memberDao.findByTelephone(telephone);
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> lists) {
        List<Integer> memeberCountList = new ArrayList<>();
        if ( lists!= null && lists.size()>0){
            //表示查询到数据
            for (String list : lists) {
             // 获取指定月份的最后一天
                String  regTime = DateUtils.getLastDayOfMonth(list);
             //  迭代过去12个月，每个月注册会员的数量，根据注册日期查询
                Integer memeberCount =   memberDao.findMemberCountBeforeDate(regTime);
                memeberCountList.add(memeberCount);
            }
        }
        return memeberCountList;
    }
}
