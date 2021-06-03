package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.service.LoginService;
import com.atguigu.service.MemberService;
import com.atguigu.utils.RedisMessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @Author:步 Modified By:
 * @Date:Created in 19:32 2021/6/1
 * @Description：
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/check")
    //HttpServletResponse response 用于会话cookie从服务器传到客户端
    public Result check(HttpServletResponse response, @RequestBody Map map){

        //从客户端传过来的map中得到telephone 和 验证码
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");

        //验证验证码是否正确 与redis中的验证码进行对比
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (codeInRedis ==null|| !codeInRedis.equals(validateCode)){
            //验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }else {
            //验证码正确，进一步判断用户是否为会员
            Member member = memberService.findByTelephone(telephone);
            if (member==null){
                //当前还不是会员，进行注册
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            //登录成功
            //写入cookie，跟踪用户
            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setPath("/");//路径
            cookie.setMaxAge(60*60*24*30);//有效期30天
            //将Cookie返回客户端
            response.addCookie(cookie);
            return new Result(true, MessageConstant.LOGIN_SUCCESS);

        }

    }
}
