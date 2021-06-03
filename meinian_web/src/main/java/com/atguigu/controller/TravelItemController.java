package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TravelItemController
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-25
 * @Description:
 */
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelItem> lists = travelItemService.findAll();
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,lists);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('TRAVELITEM_EDIT')")//权限校验

    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")//权限校验，使用TRAVELITEM_DELETE123测试

    public Result findById(Integer id){
       TravelItem travelItem =  travelItemService.findById(id);
       return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE')")//权限校验，使用TRAVELITEM_DELETE123测试

    public Result deleteById(Integer id){
        try {
            travelItemService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (RuntimeException e){
            // 运行时异常，表示自由行和跟团游的关联表中存在数据
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =  travelItemService.findPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")//权限校验

    public Result add(@RequestBody TravelItem travelItem){
        try {
            travelItemService.add(travelItem);
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }
}