package com.atguigu.controller;

import com.alibaba.dubbo.common.store.support.SimpleDataStore;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:步 Modified By:
 * @Date:Created in 18:59 2021/6/2
 * @Description：
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
   private MemberService memberService;
    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        //根据当前时间，获取前12个月的日历(当前日历2021-06，12个月前，日历时间2020-06)
        //第二个参数，日历字段
        //第二个参数，要添加到字段中的日期或时间
        calendar.add(Calendar.MONTH, -12);
        List<String>lists = new ArrayList<>();
        for (int i = 0; i <12 ;i++) {
            //第一个参数是月份 2021-6
            //第二个参数是月份+1个月
            calendar.add(Calendar.MONTH, 1);
            lists.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
        }

        Map<String,Object>map = new HashMap<String,Object>();
        // 把过去12个月的日期存储到map里面
        map.put("month",lists);
        //查询所有会员
        List<Integer>memberCount = memberService.findMemberCountByMonth(lists);
        map.put("memberCount",memberCount);

        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);

    }
}
