package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrdersettingDao;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderService;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OrderServiceImpl
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-31
 * @Description:
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersettingDao ordersettingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

//    1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
//
//2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
//
//3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
//
//4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
//
//5、预约成功，更新当日的已预约人数
    @Override
    public Result add(Map map) {
        try {
            // 获取用户预约日期，查看当天是否有旅游团
            String orderDate = (String) map.get("orderDate");
            // string类型转换成date类型
            Date date = DateUtils.parseString2Date(orderDate);
            // 根据日期进行查询
            OrderSetting orderSetting =  ordersettingDao.findByOrderDate(date);
            // 判断当前对象是否有值
            if (orderSetting == null){
                // 说明当天没有开团
                return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
            }else {
                // 当天可以出去玩
                // 旅游团的大小 100
                int number = orderSetting.getNumber();
                // 已经预约的人数 96
                int reservations = orderSetting.getReservations();
                // 判断
                if (reservations >= number){
                    //说明当天的旅游团已经满了
                    return new Result(false,MessageConstant.ORDER_FULL);
                }

            }

//3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
//
//4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
//
//5、预约成功，更新当日的已预约人数
        // 获取用户收入的手机号码
            String telephone = (String) map.get("telephone");
            // 根据手机号码查询当前用户是否是会员
            Member member = memberDao.findByTelephone(telephone);
            // 判断当前的会员是否是用户
            if (member!=null){
                // 是会员
                // 判断用户是否重复下单
                Integer memberId = member.getId();
                // 获取套餐id
                int setmealId = Integer.parseInt((String) map.get("setmealId"));
                Order order = new Order(memberId,date,null,null,setmealId);
                // 查询当前用户是否已经下单
                List<Order> lists =  orderDao.findByCondition(order);
                // 判断用户是否下单
                if (lists!= null && lists.size()>0){
                    return new Result(false,MessageConstant.HAS_ORDERED);
                }
            }else {
                // 不是会员
                member = new Member();
                // 如果不是会员，需要注册会员
                member.setName((String) map.get("name"));
                member.setSex((String) map.get("sex"));
                member.setIdCard((String) map.get("idCard"));
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberDao.add(member);
            }
//5、预约成功，更新当日的已预约人数
            // 获取预约人数，然后把预约人数 + 1
           orderSetting.setReservations(orderSetting.getReservations() + 1);
            // 持久化操作到数据库
            ordersettingDao.editReservationsByOrderDate(orderSetting);

            // 往订单表插入数据
            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(date);
            order.setOrderType((String) map.get("orderType"));
            order.setOrderStatus(Order.ORDERSTATUS_NO);
            order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
            orderDao.add(order);
            return new Result(true,MessageConstant.ORDER_SUCCESS,order);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    //查询预约订单数据
    @Override
    public Map findById(Integer id) throws Exception {
        //将查询结果封装到map中
       Map map = orderDao.findById(id);

       //获取所需要的setmealId，memberId，OrderType,OrderDate等信息
        if (map!=null){
            //处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate", DateUtils.parseDate2String(orderDate));
            return map;
        }
        return map;
    }
}