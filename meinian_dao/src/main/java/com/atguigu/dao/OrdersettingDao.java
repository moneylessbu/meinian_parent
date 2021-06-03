package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OrdersettingDao
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-29
 * @Description:
 */
public interface OrdersettingDao {
    void add(OrderSetting ordersetting);

    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting ordersetting);

    List<OrderSetting> getOrderSettingByMonth(Map map);

    OrderSetting findByOrderDate(Date date);

    void editReservationsByOrderDate(OrderSetting orderSetting);

}

