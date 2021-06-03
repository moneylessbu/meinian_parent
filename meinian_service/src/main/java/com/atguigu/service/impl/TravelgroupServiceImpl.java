package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelgroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelgroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TravelgroupServiceImpl
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-26
 * @Description:
 */
@Service(interfaceClass = TravelgroupService.class)
@Transactional
public class TravelgroupServiceImpl implements TravelgroupService {

    @Autowired
    private TravelgroupDao travelgroupDao;

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelgroupDao.add(travelGroup);
        // 往中间表插入数据
        setTravelgroupAndTravelitem(travelItemIds,travelGroup.getId());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<TravelGroup> page = travelgroupDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public TravelGroup findById(Integer id) {

        return travelgroupDao.findById(id);
    }

    @Override
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id) {
        return travelgroupDao.findTravelItemIdByTravelgroupId(id);
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelgroupDao.edit(travelGroup);
        // 先删除，在添加
        travelgroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(travelGroup.getId());
        // 添加
        setTravelgroupAndTravelitem(travelItemIds,travelGroup.getId());
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelgroupDao.findAll();
    }

    private void setTravelgroupAndTravelitem(Integer[] travelItemIds, Integer id) {
        for (Integer travelItemId : travelItemIds) {
            Map map = new HashMap<>();
            map.put("travelItemIds",travelItemId);
            map.put("travelGroupIds",id);
            travelgroupDao.setTravelgroupAndTravelitem(map);
        }

    }
}