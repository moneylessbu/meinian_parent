package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrdersettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrdersettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrdersettingServiceImpl
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-29
 * @Description:
 */
@Service(interfaceClass = OrdersettingService.class)
@Transactional
public class OrdersettingServiceImpl implements OrdersettingService {

    @Autowired
    private OrdersettingDao ordersettingDao;

    @Override
    public void add(List<OrderSetting> ordersettings) {
        //  判断预约表是否有数据，如果有数据，直接更新，如果没有数据在插入数据
        for (OrderSetting ordersetting : ordersettings) {
           long count =  ordersettingDao.findCountByOrderDate(ordersetting.getOrderDate());
           if (count > 0){
               // 更新数据
               ordersettingDao.editNumberByOrderDate(ordersetting);
           }else {
               ordersettingDao.add(ordersetting);
           }


        }

    }

    // this.leftobj = [
    //     { date: 1, number: 120, reservations: 1 },
    //     { date: 3, number: 120, reservations: 1 },
    //     { date: 4, number: 120, reservations: 120 },
    //     { date: 6, number: 120, reservations: 1 },
    //     { date: 8, number: 120, reservations: 1 }
    // ]; 2021-05-01 - 2021-05-31
    // 如果是mysql8.0以上，日期写死会报错
    // 5.7mysql
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String dateBegin =  date + "-01";
        String dateEnd =  date + "-31";
        Map map = new HashMap<>();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> lists =  ordersettingDao.getOrderSettingByMonth(map);
        List<Map> dataLists = new ArrayList<>();
        for (OrderSetting list : lists) {
            Map data = new HashMap<>();
            data.put("date",list.getOrderDate().getDate());
            data.put("number",list.getNumber());
            data.put("reservations",list.getReservations());
            dataLists.add(data);
        }

        return dataLists;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count =  ordersettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0){
            // 更新数据
            ordersettingDao.editNumberByOrderDate(orderSetting);
        }else {
            ordersettingDao.add(orderSetting);
        }
    }
}