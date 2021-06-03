package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * ClearImgJob
 *
 * @Author: 步鹏飞
 * @CreateTime: 2021-05-28
 * @Description:
 */
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        // sdiff:返回与第一个集合的差异，多的值需要放到前面
        Set<String> set = jedisPool.getResource().sdiff(
                RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES
        );
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String pic = iterator.next();
            System.out.println("需要删除的图片名称："+pic);
            // 删除七牛云的图片
            QiniuUtils.deleteFileFromQiniu(pic);
            // 删除redis图片
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,pic);
        }
    }
}