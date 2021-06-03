package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * OrdersettingService
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-29
 * @Description:
 */
public interface OrdersettingService {
    void add(List<OrderSetting> ordersettings);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);

}

