package com.atguigu.entity;

import java.io.Serializable;

/**
 * Result
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-05-25
 * @Description:
 * 服务器返回给客户端的数据
 */
public class Result implements Serializable {
    // 服务器返回成功还是失败
    private boolean flag;
    // 服务器返回的消息
    private String message;

    private Object data;
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}