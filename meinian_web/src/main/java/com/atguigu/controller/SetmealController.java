package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * SetmealController
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-28
 * @Description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
      PageResult pageResult =   setmealService.findPage(queryPageBean);
        return pageResult;
    }

    // @RequestBody:表示json数据和javabean进行互转
    @RequestMapping("/add")
    public Result add(Integer[] travelgroupIds, @RequestBody Setmeal setmeal){
        setmealService.add(travelgroupIds,setmeal);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }


    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        try {
            // 获取原始图片名称
            String originalFilename = imgFile.getOriginalFilename();
            // 修改名称 abc.jpg
            // 获取图片的点
            int lastIndexOf = originalFilename.lastIndexOf(".");
            // 获取图片的后缀
            String substring = originalFilename.substring(lastIndexOf);
            // 使用UUID生成随机数
            String fileName =  UUID.randomUUID().toString() + substring;
            // 往七牛云存图片
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);

            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}