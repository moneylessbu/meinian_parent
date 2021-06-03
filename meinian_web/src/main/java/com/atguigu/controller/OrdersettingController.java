package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrdersettingService;
import com.atguigu.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.RelationSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OrdersettingController
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-29
 * @Description:
 */
@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {

    @Reference
    private OrdersettingService ordersettingService;

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        ordersettingService.editNumberByDate(orderSetting);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }

    // this.leftobj = [
    //     { date: 1, number: 120, reservations: 1 },
    //     { date: 3, number: 120, reservations: 1 },
    //     { date: 4, number: 120, reservations: 120 },
    //     { date: 6, number: 120, reservations: 1 },
    //     { date: 8, number: 120, reservations: 1 }
    // ];
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String  date){
       List<Map> lists =  ordersettingService.getOrderSettingByMonth(date);
       return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,lists);
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            // 读取excel表格里面的数据
            List<String[]> lists = POIUtils.readExcel(excelFile);
            List<OrderSetting> Ordersettings = new ArrayList<>();
            //  迭代数据
            for (String[] list : lists) {
                // String[0]:日期 String[1]:可预约的数据
                OrderSetting orderSetting = new OrderSetting(
                        new Date(list[0]),Integer.parseInt(list[1]));
                Ordersettings.add(orderSetting);

            }

            ordersettingService.add(Ordersettings);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);



        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }
}