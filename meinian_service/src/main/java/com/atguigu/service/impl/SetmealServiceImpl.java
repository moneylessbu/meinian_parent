package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.RedisConstant;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SetmealServiceImpl
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-28
 * @Description:
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal) {
        setmealDao.add(setmeal);
        setSetmealAndTravelgroup(setmeal.getId(),travelgroupIds);
        // 存入到redis里面
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> page =  setmealDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> getSetmeal() {
        return setmealDao.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    private void setSetmealAndTravelgroup(Integer id, Integer[] travelgroupIds) {
        for (Integer travelgroupId : travelgroupIds) {
            Map map = new HashMap<>();
            map.put("travelgroupId",travelgroupId);
            map.put("setmealId",id);
            setmealDao.setSetmealAndTravelgroup(map);
        }
    }
}