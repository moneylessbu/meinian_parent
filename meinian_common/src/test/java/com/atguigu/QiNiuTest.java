package com.atguigu;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * QiNiuTest
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-05-28
 * @Description:
 */
public class QiNiuTest {

    @Test
    public void test02(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        String accessKey = "dzpXBDSa3musX6U7Nq8v0fzv921stt-NnRLGhACK";
        String secretKey = "AB90WSgUo32gY87jOyOW2zVH97fz9wT9JWCpKEm-";
        // 空间的名字
        String bucket = "java0223";
        String key = "苍老师.jpg";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (Exception ex) {
            //如果遇到异常，说明删除失败
            System.out.println(ex);
        }
    }

    @Test
    public void test01(){
//构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "dzpXBDSa3musX6U7Nq8v0fzv921stt-NnRLGhACK";
        String secretKey = "AB90WSgUo32gY87jOyOW2zVH97fz9wT9JWCpKEm-";
        // 空间的名字
        String bucket = "java0223";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\img\\aa.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
}