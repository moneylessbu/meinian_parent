package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelgroupService;
import org.apache.poi.hpsf.ReadingNotSupportedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TravelgroupController
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-26
 * @Description:
 */
@RestController
@RequestMapping("/travelgroup")
public class TravelgroupController {

    @Reference
    private TravelgroupService travelgroupService;

    @RequestMapping("/findAll")
    public Result findAll(){
       List<TravelGroup> lists =  travelgroupService.findAll();
       return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,lists);
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup){
        travelgroupService.edit(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }

    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id){
       List<Integer> lists =  travelgroupService.findTravelItemIdByTravelgroupId(id);
       return lists;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
       TravelGroup travelGroup = travelgroupService.findById(id);
       return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =  travelgroupService.findPage(queryPageBean);
        return  pageResult;
    }

    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds , @RequestBody TravelGroup travelGroup){
        travelgroupService.add(travelItemIds,travelGroup);
        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }
}